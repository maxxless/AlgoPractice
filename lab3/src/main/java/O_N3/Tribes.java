package O_N3;

import java.util.*;

public class Tribes {
    private static List<Boolean> discovered = new ArrayList<>();
    private static List<List<Integer>> peopleList = new ArrayList<>();
    private static List<List<Integer>> tribesList = new ArrayList<>();

    private static void bfs(Graph g, int start) {
        Queue<Integer> queue = new LinkedList<>();
        int i, v;
        queue.offer(start);
        discovered.set(start, true);
        while (!queue.isEmpty()) {
            v = queue.remove();
            peopleList.get(start - 1).add(v);
            System.out.printf(" %d", v);
            for (i = g.degree.get(v) - 1; i >= 0; i--) {
                if (!discovered.get(g.edges[v][i])) {
                    queue.offer(g.edges[v][i]);
                    discovered.set(g.edges[v][i], true);
                }
            }
        }
    }

    private static void initializeSearch() {
        for (int i = 0; i < 1000; i++) {
            discovered.add(false);
            peopleList.add(new ArrayList<>());
        }
    }

    private static void connectedComponents(Graph graph) {
        initializeSearch();
        int c = 0;
        for (int i = 1; i <= graph.verticesNumber; i++) {
            if (!discovered.get(i)) {
                c++;
                System.out.printf("Tribe %d:", c);
                bfs(graph, i);
                System.out.print("\n");
            }
        }
    }

    static public void main(String[] args) {
        int couplesNumber = 0;
        Graph graph = new Graph();
        graph.readGraph();

        connectedComponents(graph);

        for (List<Integer> peopleSubList : peopleList) {
            if (!peopleSubList.isEmpty()) {
                tribesList.add(peopleSubList);
            }
        }

        for (List<Integer> tribe : tribesList) {
            for (Integer woman : tribe) {
                for (List<Integer> anotherTribe : tribesList) {
                    if (tribe != anotherTribe) {
                        for (Integer man : anotherTribe) {
                            if ((woman % 2 == 0) && (man % 2 == 1)) {
                                couplesNumber++;
                                System.out.println("Couple: " + woman + "/" + man);
                            }
                        }
                    }
                }
            }
        }
        System.out.printf("\nFinal amount of couples: %d", couplesNumber);
    }
}