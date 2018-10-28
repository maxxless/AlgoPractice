package O_N2;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;


public class ConnectedComponents {

    private static List<DirectedGraphNode> readGraph() {
        int vertexFrom, vertexTo, couplesNumber;

        List<DirectedGraphNode> tribesList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of couples: ");
        couplesNumber = sc.nextInt();

        System.out.println("Enter the edges: <from> <to>");

        for (int i = 0; i < couplesNumber; i++) {
            vertexFrom = sc.nextInt();
            vertexTo = sc.nextInt();

//            int finalVertexFrom = vertexFrom;
//            int finalVertexTo = vertexTo;

//            List<Object> newList = tribesList.stream()
//                    .map(graph -> graph.vertexValue == (finalVertexFrom) ? graph.neighbors.add(new O_N2.DirectedGraphNode(finalVertexTo)) : graph)
//                    .collect(Collectors.toList());

            DirectedGraphNode directedGraphNodeFrom = new DirectedGraphNode(vertexFrom);
            DirectedGraphNode directedGraphNodeTo = new DirectedGraphNode(vertexTo);

            directedGraphNodeFrom.getNeighbors().add(directedGraphNodeTo);

            tribesList.add(directedGraphNodeFrom);
            tribesList.add(directedGraphNodeTo);
        }
        sc.close();

        return tribesList;
    }


    private static List<List<DirectedGraphNode>> connectedComponents(List<DirectedGraphNode> nodes) {
        if (nodes == null || nodes.size() == 0) {
            throw new IllegalArgumentException("List node is empty");
        }

        // Maintain array with name for each element
        Integer[] labels = new Integer[nodes.size()];
        // Initially, set the labels of each element to itself
        // Use HashMap to memorize the index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < labels.length; i++) {
            labels[i] = nodes.get(i).getVertexValue();
            map.put(nodes.get(i).getVertexValue(), i);
        }

        for (DirectedGraphNode node : nodes) {
            if (node.getNeighbors() == null || node.getNeighbors().isEmpty()) {
                continue;
            }

            int changerIdx = map.get(node.getVertexValue());
            for (DirectedGraphNode nbr : node.getNeighbors()) {
                int changeIndex = map.get(nbr.getVertexValue());
                Integer symbol = labels[changeIndex];
                for (int i = 0; i < labels.length; i++) {
                    if (labels[i].equals(symbol)) {
                        labels[i] = labels[changerIdx];
                    }
                }
            }
        }
        return createTribeList(labels, nodes);
    }

    private static List<List<DirectedGraphNode>> createTribeList(Integer[] labels, List<DirectedGraphNode> nodes) {
        List<List<DirectedGraphNode>> res = new ArrayList<>();
        if (labels == null || labels.length == 0) {
            return res;
        }

        Map<Integer, List<DirectedGraphNode>> map = new HashMap<>();
        for (int i = 0; i < labels.length; i++) {
            if (!map.containsKey(labels[i])) {
                List<DirectedGraphNode> tribe = new ArrayList<>();
                tribe.add(nodes.get(i));
                map.put(labels[i], tribe);
            } else {
                map.get(labels[i]).add(nodes.get(i));
            }
        }

        for (Integer key : map.keySet()) {
            res.add(map.get(key));
        }

        return res;
    }


    public static void main(String[] args) {
        int counter = 1;

        List<DirectedGraphNode> tribes;

        tribes = readGraph();

        List<DirectedGraphNode> unique = tribes.stream().sorted(Comparator.comparingInt(directedGraphNode -> directedGraphNode.getNeighbors().size()))
                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(DirectedGraphNode::getVertexValue))),
                        ArrayList::new));

        for (List<DirectedGraphNode> elements : connectedComponents(unique)) {
            System.out.println("Tribe " + counter);
            for (DirectedGraphNode element : elements) {
                String outputElement = (element == elements.get(elements.size() - 1)) ? Integer.toString(element.getVertexValue()) : (Integer.toString(element.getVertexValue()) + " -> ");
                System.out.print(outputElement);
            }
            counter++;
            System.out.println();
        }

    }
}

