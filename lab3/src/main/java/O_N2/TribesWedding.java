package O_N2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TribesWedding {

    private static List<List<Integer>> readGraph() {
        int vertexFrom, vertexTo, couplesNumber;

        List<List<Integer>> tribesList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of couples: ");
        couplesNumber = sc.nextInt();

        System.out.println("Enter the edges: <from> <to>");

        for (int i = 0; i < couplesNumber; i++) {
            List<Integer> tempList = new ArrayList<>();

            vertexFrom = sc.nextInt();
            vertexTo = sc.nextInt();


            tempList.add(vertexFrom);
            tempList.add(vertexTo);

            tribesList.add(tempList);
        }
        sc.close();

        return tribesList;
    }

    private static List<Vertex> allTribesVertices = new ArrayList<>();
    private static List<Vertex> uniqueVertices = new ArrayList<>();

    private static void makeUnvisited(List<Vertex> list) {
        for (Vertex vertex : list) {
            vertex.visited = false;
        }
    }

    private static boolean isInGraph(Vertex rootVertex, int processVertexValue) {
        makeUnvisited(uniqueVertices);
        return checkIfGraphContainsVertex(rootVertex, processVertexValue);
    }

    private static boolean checkIfGraphContainsVertex(Vertex rootVertex, int processVertexValue) {
        if (rootVertex.value == processVertexValue) {
            return true;
        }

        List<Vertex> neighbours = rootVertex.getNeighbours();
        rootVertex.visited = true;
        for (Vertex n : neighbours) {
            if (n != null && !n.visited) {
                if (checkIfGraphContainsVertex(n, processVertexValue)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Vertex getVertexByValueFromGraph(Vertex rootVertex, int processVertexValue) {
        makeUnvisited(uniqueVertices);
        return processVertexNeighbours(rootVertex, processVertexValue);

    }

    private static Vertex processVertexNeighbours(Vertex rootVertex, int processVertexValue) {
        if (rootVertex.value == processVertexValue) {
            return rootVertex;
        }

        List<Vertex> neighbours = rootVertex.getNeighbours();
        rootVertex.visited = true;
        for (Vertex vertex : neighbours) {
            if (vertex != null && !vertex.visited) {
                Vertex foundVertex = processVertexNeighbours(vertex, processVertexValue);
                if (foundVertex != null) return foundVertex;
            }
        }
        return null;
    }

    private static void printGraphTree(Vertex rootVertex) {
        makeUnvisited(uniqueVertices);
        printRootVertexAndItsNeighbours(rootVertex);
    }

    private static void printRootVertexAndItsNeighbours(Vertex rootVertex) {
        System.out.print(rootVertex.value + " ");
        List<Vertex> neighbours = rootVertex.getNeighbours();
        rootVertex.visited = true;
        for (Vertex vertex : neighbours) {
            if (vertex != null && !vertex.visited) {
                printRootVertexAndItsNeighbours(vertex);
            }
        }
    }

    private static List<Integer> returnGraphAsList(Vertex rootVertex) {
        makeUnvisited(uniqueVertices);
        List<Integer> result = new ArrayList<>();
        return getGraphList(rootVertex, result);
    }

    private static List<Integer> getGraphList(Vertex rootVertex, List<Integer> result) {
        result.add(rootVertex.value);
        List<Vertex> neighbours = rootVertex.getNeighbours();
        rootVertex.visited = true;
        for (Vertex vertex : neighbours) {
            if (vertex != null && !vertex.visited) {
                result = getGraphList(vertex, result);
            }
        }
        return result;
    }

    private static void fromArrayToGraphs(List<List<Integer>> graphList) {
        for (List<Integer> graphCouple : graphList) {
            int vertex1 = graphCouple.get(0);
            int vertex2 = graphCouple.get(1);
            boolean wasAdded = false;
            int addedIndex = -1;

            outer:
            for (int j = 0; j < allTribesVertices.size(); j++) {
                Vertex currentVertex = allTribesVertices.get(j);

                if (isInGraph(currentVertex, vertex1)) {
                    if (wasAdded) {
                        Vertex tempVertex = getVertexByValueFromGraph(allTribesVertices.get(addedIndex), vertex1);
                        tempVertex.addNeighbour(tempVertex.getNeighbours().get(0));
                        allTribesVertices.remove(j);
                        break;
                    }

                    for (int k = j + 1; k < allTribesVertices.size(); k++) {
                        if (isInGraph(allTribesVertices.get(k), vertex2)) {
                            currentVertex.addNeighbour(allTribesVertices.get(k));
                            allTribesVertices.remove(k);
                            wasAdded = true;
                            break outer;
                        }
                    }

                    Vertex vertexTo = new Vertex(vertex2);
                    getVertexByValueFromGraph(currentVertex, vertex1).addNeighbour(vertexTo);


                    addedIndex = j;
                    wasAdded = true;
                } else if (isInGraph(currentVertex, vertex2)) {
                    if (wasAdded) {
                        Vertex vertex = getVertexByValueFromGraph(allTribesVertices.get(addedIndex), vertex2);
                        vertex.addNeighbour(vertex.getNeighbours().get(0));
                        allTribesVertices.remove(j);
                        break;
                    }

                    for (int k = j + 1; k < allTribesVertices.size(); k++) {
                        if (isInGraph(allTribesVertices.get(k), vertex1)) {
                            currentVertex.addNeighbour(allTribesVertices.get(k));
                            wasAdded = true;
                            allTribesVertices.remove(k);
                            break outer;
                        }
                    }

                    Vertex vertexFrom = new Vertex(vertex1);
                    getVertexByValueFromGraph(currentVertex, vertex2).addNeighbour(vertexFrom);


                    wasAdded = true;
                    addedIndex = j;
                }
            }

            if (!wasAdded) {
                Vertex vertexFrom = new Vertex(vertex1);
                Vertex vertexTo = new Vertex(vertex2);
                vertexFrom.addNeighbour(vertexTo);

                allTribesVertices.add(vertexFrom);
            }
        }
    }

    private static List<List<Integer>> countMenAndWomen(List<Vertex> vertexList) {
        List<List<Integer>> result = new ArrayList<>();

        for (Vertex nodesList : vertexList) {
            List<Integer> tribe = returnGraphAsList(nodesList);

            List<Integer> tribePeople = new ArrayList<>();
            tribePeople.add(0);
            tribePeople.add(0);
            for (Integer elem : tribe) {
                if (elem % 2 == 0) {
                    int manNumber = tribePeople.get(0) + 1;
                    tribePeople.set(0, manNumber);
                } else {
                    int womenNumber = tribePeople.get(1) + 1;
                    tribePeople.set(1, womenNumber);
                }
            }
            result.add(tribePeople);
        }

        return result;
    }

    private static Integer countPairs(List<List<Integer>> listOfMenAndWomen) {
        int pairsNumber = 0;

        for (int i = 0; i < listOfMenAndWomen.size(); i++) {
            for (int j = i + 1; j < listOfMenAndWomen.size(); j++) {
                pairsNumber += listOfMenAndWomen.get(i).get(0) * listOfMenAndWomen.get(j).get(1);
                pairsNumber += listOfMenAndWomen.get(i).get(1) * listOfMenAndWomen.get(j).get(0);
            }
        }

        return pairsNumber;
    }

    public static void main(String[] args) {
        fromArrayToGraphs(readGraph());

        int counter = 1;

        for (Vertex tribesHead : allTribesVertices) {
            System.out.println("Tribe " + counter + ": ");
            printGraphTree(tribesHead);
            counter++;
            System.out.println();
        }

        List<List<Integer>> menAndWomen = countMenAndWomen(allTribesVertices);

        System.out.format("\nAll possible pairs: %d ", countPairs(menAndWomen));
    }

    static class Vertex {
        int value;
        boolean visited;
        List<Vertex> neighbours;

        Vertex(int value) {
            this.value = value;
            this.neighbours = new ArrayList<>();

            uniqueVertices.add(this);
        }

        void addNeighbour(Vertex neighbourVertex) {
            this.neighbours.add(neighbourVertex);
        }

        List<Vertex> getNeighbours() {
            return neighbours;
        }
    }
}

