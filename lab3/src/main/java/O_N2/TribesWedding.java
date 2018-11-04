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


    private static List<Graph> tribesValues = new ArrayList<>();
    private static List<Graph> uniqueGraphs = new ArrayList<>();


    private static void makeUnvisited(List<Graph> list) {
        for (Graph aList : list) {
            aList.visited = false;
        }
    }


    // returns true element with this value exits in graph
    private static boolean mDfs(Graph graph, int value) {
        makeUnvisited(uniqueGraphs);
        return dfs(graph, value);
    }

    private static boolean dfs(Graph graph, int value) {
        if (graph.node == value) {
            return true;
        }

        List<Graph> neighbours = graph.getNeighbours();
        graph.visited = true;
        for (Graph n : neighbours) {
            if (n != null && !n.visited) {
                if (dfs(n, value)) {
                    return true;
                }
            }
        }
        return false;
    }


    // returns an Graph element with this value from graph
    private static Graph mDfsByValue(Graph graph, int value) {
        makeUnvisited(uniqueGraphs);
        return dfsByValue(graph, value);

    }

    private static Graph dfsByValue(Graph graph, int value) {
        if (graph.node == value) {
            return graph;
        }

        List<Graph> neighbours = graph.getNeighbours();
        graph.visited = true;
        for (Graph n : neighbours) {
            if (n != null && !n.visited) {
                Graph foundGraph = dfsByValue(n, value);
                if (foundGraph != null) return foundGraph;
            }
        }
        return null;
    }


    // just prints all the graph elements using dfs
    private static void mDfsPrint(Graph graph) {
        makeUnvisited(uniqueGraphs);
        dfsPrint(graph);
    }

    private static void dfsPrint(Graph graph) {
        System.out.print(graph.node + " ");
        List<Graph> neighbours = graph.getNeighbours();
        graph.visited = true;
        for (Graph n : neighbours) {
            if (n != null && !n.visited) {
                dfsPrint(n);
            }
        }
    }


    //returns graph as list
    private static List<Integer> mDfsGetList(Graph graph) {
        makeUnvisited(uniqueGraphs);
        List<Integer> result = new ArrayList<>();
        return dfsGetList(graph, result);
    }

    private static List<Integer> dfsGetList(Graph graph, List<Integer> result) {
        result.add(graph.node);
        List<Graph> neighbours = graph.getNeighbours();
        graph.visited = true;
        for (Graph n : neighbours) {
            if (n != null && !n.visited) {
                result = dfsGetList(n, result);
            }
        }
        return result;
    }


    private static void fromArrayToGraphs(List<List<Integer>> array) {
        for (List<Integer> anArray : array) {
            int node1 = anArray.get(0);
            int node2 = anArray.get(1);
            boolean wasAdded = false;
            int addedIndex = -1;

            outer:
            for (int j = 0; j < tribesValues.size(); j++) {
                Graph head = tribesValues.get(j);

                if (mDfs(head, node1)) {
                    if (wasAdded) {
                        Graph graph = mDfsByValue(tribesValues.get(addedIndex), node1);
                        graph.addNeighbour(head.getNeighbours().get(0));
                        tribesValues.remove(j);
                        break;
                    }

                    for (int k = j + 1; k < tribesValues.size(); k++) {
                        if (mDfs(tribesValues.get(k), node2)) {
                            head.addNeighbour(tribesValues.get(k));
                            tribesValues.remove(k);
                            wasAdded = true;
                            break outer;
                        }
                    }

                    Graph n = new Graph(node2);
                    mDfsByValue(head, node1).addNeighbour(n);


                    addedIndex = j;
                    wasAdded = true;
                } else if (mDfs(head, node2)) {
                    if (wasAdded) {
                        Graph graph = mDfsByValue(tribesValues.get(addedIndex), node2);
                        graph.addNeighbour(head.getNeighbours().get(0));
                        tribesValues.remove(j);
                        break;
                    }

                    for (int k = j + 1; k < tribesValues.size(); k++) {
                        if (mDfs(tribesValues.get(k), node1)) {
                            head.addNeighbour(tribesValues.get(k));
                            wasAdded = true;
                            tribesValues.remove(k);
                            break outer;
                        }
                    }

                    Graph n = new Graph(node1);
                    mDfsByValue(head, node2).addNeighbour(n);


                    wasAdded = true;
                    addedIndex = j;
                }
            }

            if (!wasAdded) {
                Graph n1 = new Graph(node1);
                Graph n2 = new Graph(node2);
                n1.addNeighbour(n2);

                tribesValues.add(n1);
            }
        }
    }


    private static List<List<Integer>> countMenAndWomen(List<Graph> graphList) {
        List<List<Integer>> result = new ArrayList<>();

        for (Graph nodesList : graphList) {
            List<Integer> tribe = mDfsGetList(nodesList);

            List<Integer> tribePeople = new ArrayList<>();
            tribePeople.add(0);
            tribePeople.add(0);
            for (Integer elem : tribe) {
                if (elem % 2 == 0) {
                    int man = tribePeople.get(0) + 1;
                    tribePeople.set(0, man);
                } else {
                    int woman = tribePeople.get(1) + 1;
                    tribePeople.set(1, woman);
                }
            }
            result.add(tribePeople);
        }

        return result;
    }

    private static Integer countPairs(List<List<Integer>> listOfMenAndWomen) {
        int pairs = 0;

        for (int i = 0; i < listOfMenAndWomen.size(); i++) {
            for (int j = i + 1; j < listOfMenAndWomen.size(); j++) {
                pairs += listOfMenAndWomen.get(i).get(0) * listOfMenAndWomen.get(j).get(1);
                pairs += listOfMenAndWomen.get(i).get(1) * listOfMenAndWomen.get(j).get(0);
            }
        }

        return pairs;
    }

    public static void main(String[] args) {
        fromArrayToGraphs(readGraph());

        int counter = 1;

        for (Graph tribesHead : tribesValues) {
            System.out.println("Tribe " + counter + ": ");
            mDfsPrint(tribesHead);
            counter++;
            System.out.println();
        }

        List<List<Integer>> menAndWomen = countMenAndWomen(tribesValues);

        System.out.format("\nAll possible pairs: %d ", countPairs(menAndWomen));
    }

    static class Graph {
        int node;
        boolean visited;
        List<Graph> neighbours;

        Graph(int node) {
            this.node = node;
            this.neighbours = new ArrayList<>();

            uniqueGraphs.add(this);
        }

        void addNeighbour(Graph neighbourGraph) {
            this.neighbours.add(neighbourGraph);
        }

        List<Graph> getNeighbours() {
            return neighbours;
        }
    }
}

