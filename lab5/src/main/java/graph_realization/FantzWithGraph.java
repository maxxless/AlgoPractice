/*
  1)Обраховуємо всі степені 5, довжина яких у двійковій системі є менша або рівна довжині даної стрінги

  2)Всі ті стрічки зберігаємо у масиві(степінь буде індексом)

  3)Якщо при поділі нашої вхідної стрічки ми отримуємо стрічку, яка співпадає з певною стрічкою з масиву,
  ми зберігаємо її параметри обрізки(початковий і кінцевий індекс) у масиві з ребрами(пара точок)

  4)Тепер наше завдання - знайти найкоротший шлях від 0 індексу до індексу довжини вхідної стрічки (з масиву з ребрами).
  Ми припускаємо, що кожне ребро має однакову вагу(нехай 1), тому шлях можна знайти за допомогою алгоритму Дейкстри

  5)Кількість ребер, які складають найкоротший шлях, - мінімальна кількість наших кусочків
 */
package graph_realization;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.pow;

public class FantzWithGraph {
    private final static String BINARY_STRING = "101101101";
    private final static int MAX_ELEMENT_NUMBER = BINARY_STRING.length();
    private final static int BASE_VALUE = 5;
    private static List<Vertex> vertices = new ArrayList<>();
    private static List<Edge> edges = new ArrayList<>();
    private static int lastIndex = 0;

    private static List<String> allPossiblePowers() {
        List<String> powersList = new ArrayList<>();
        for (int i = 0; i < MAX_ELEMENT_NUMBER; i++) {
            String stringFromNumber = Integer.toBinaryString((int) pow(BASE_VALUE, i));
            if (stringFromNumber.length() <= MAX_ELEMENT_NUMBER) {
                powersList.add(stringFromNumber);
            } else {
                break;
            }
        }
        return powersList;
    }

    private static void findMinimalAmountOfPieces(List<String> powersList) {
        for (int charIndex = 1; charIndex < MAX_ELEMENT_NUMBER; charIndex++) {
            for (int subStringIndex = 1; subStringIndex <= charIndex; subStringIndex++) {
                if (BINARY_STRING.charAt(subStringIndex - 1) == '0') {
                    continue;
                }
                String tempBinaryString = BINARY_STRING.substring(subStringIndex - 1, charIndex);
                if (powersList.contains(tempBinaryString)) {
                    Vertex vertexFrom = new Vertex(subStringIndex - 1);
                    Vertex vertexTo = new Vertex(charIndex);

                    vertices.add(vertexFrom);
                    vertices.add(vertexTo);

                    addEdge(subStringIndex - 1, charIndex);
                    lastIndex = charIndex;
                }
            }
        }
    }

    private static void addEdge(int sourceLocNo, int destLocNo) {
        Edge edge = new Edge(vertices.get(sourceLocNo), vertices.get(destLocNo), 1);
        edges.add(edge);
    }


    public static void main(String[] args) {
        Graph graph = new Graph(vertices, edges);

        List<String> powersList = allPossiblePowers();

        findMinimalAmountOfPieces(powersList);

        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(vertices.get(0));
        LinkedList<Vertex> path = dijkstra.getPath(vertices.get(lastIndex));

        int counter = 0;
        for (Vertex vertex : path) {
            counter++;
            System.out.println(vertex);
        }

        System.out.println(counter);
    }
}
