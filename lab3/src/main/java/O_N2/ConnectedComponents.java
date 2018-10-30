package O_N2;

import java.util.*;


public class ConnectedComponents {

    private static List<DirectedGraphNode> readGraph() {
        int vertexFrom, vertexTo, couplesNumber;

        List<DirectedGraphNode> tribesList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of couples: ");
        couplesNumber = sc.nextInt();

        System.out.println("Enter the edges: <from> <to>");

        List<List<Integer>> pairs = new ArrayList<>();

        for (int i = 0; i < couplesNumber; i++) {
            vertexFrom = sc.nextInt();
            vertexTo = sc.nextInt();

            List<Integer> pair = new ArrayList<>();
            pair.add(vertexFrom);
            pair.add(vertexTo);
            pairs.add(pair);
        }
        sc.close();

        DirectedGraphNode firstDirectedGraphNodeFrom = new DirectedGraphNode(pairs.get(0).get(0));
        DirectedGraphNode firstDirectedGraphNodeTo = new DirectedGraphNode(pairs.get(0).get(1));
        firstDirectedGraphNodeFrom.getNeighbors().add(firstDirectedGraphNodeTo);
        tribesList.add(firstDirectedGraphNodeFrom);

        for (int i = 1; i < pairs.size(); i++) {
            boolean isElementAdded = false;
            int tribeListSizeTemporary = tribesList.size();
            for (int j = 0; j < tribeListSizeTemporary; j++) {
                if (tribesList.get(j).getVertexValue() == pairs.get(i).get(0)) {
                    DirectedGraphNode directedGraphNodeTo = new DirectedGraphNode(pairs.get(i).get(1));

                    tribesList.get(j).getNeighbors().add(directedGraphNodeTo);
                    isElementAdded = true;
                    break;
                } else if (tribesList.get(j).getVertexValue() == pairs.get(i).get(1)) {
                    DirectedGraphNode directedGraphNodeFrom = new DirectedGraphNode(pairs.get(i).get(0));

                    tribesList.get(j).getNeighbors().add(directedGraphNodeFrom);
                    isElementAdded = true;
                    break;
                }
            }
            if (!isElementAdded) {
                DirectedGraphNode directedGraphNodeFrom = new DirectedGraphNode(pairs.get(i).get(0));
                DirectedGraphNode directedGraphNodeTo = new DirectedGraphNode(pairs.get(i).get(1));

                directedGraphNodeFrom.getNeighbors().add(directedGraphNodeTo);

                tribesList.add(directedGraphNodeFrom);
            }
        }

//        for (int k = 0; k < tribesList.size(); k++) {
//            if (vertexFrom == tribesList.get(k).getVertexValue()) {
//                DirectedGraphNode directedGraphNodeTo = new DirectedGraphNode(vertexTo);
//
//                tribesList.get(k).getNeighbors().add(directedGraphNodeTo);
//            } else if (vertexTo == tribesList.get(k).getVertexValue()) {
//                DirectedGraphNode directedGraphNodeFrom = new DirectedGraphNode(vertexTo);
//
//                tribesList.get(k).getNeighbors().add(directedGraphNodeFrom);
//            } else {
//                DirectedGraphNode directedGraphNodeFrom = new DirectedGraphNode(vertexFrom);
//                DirectedGraphNode directedGraphNodeTo = new DirectedGraphNode(vertexTo);
//
//                directedGraphNodeFrom.getNeighbors().add(directedGraphNodeTo);
//
//                tribesList.add(directedGraphNodeFrom);
//                tribesList.add(directedGraphNodeTo);
//            }
//        }

        return tribesList;
    }


    private static List<List<DirectedGraphNode>> connectedComponents(List<DirectedGraphNode> nodes) {
        if (nodes == null || nodes.size() == 0) {
            throw new IllegalArgumentException("List node is empty");
        }

        // Maintain array with name for each element
        Integer[] values = new Integer[nodes.size()];
        // Initially, set the values of each element to itself
        // Use HashMap to memorize the index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            values[i] = nodes.get(i).getVertexValue();
            map.put(nodes.get(i).getVertexValue(), i);
        }

        for (DirectedGraphNode node : nodes) {
            if (node.getNeighbors() == null || node.getNeighbors().isEmpty()) {
                continue;
            }

            int changerIdx = map.get(node.getVertexValue());
            for (DirectedGraphNode nbr : node.getNeighbors()) {
                int changeIndex = map.get(nbr.getVertexValue());
                Integer symbol = values[changeIndex];
                for (int i = 0; i < values.length; i++) {
                    if (values[i].equals(symbol)) {
                        values[i] = values[changerIdx];
                    }
                }
            }
        }
        return createTribeList(values, nodes);
    }

    private static List<List<DirectedGraphNode>> createTribeList(Integer[] values, List<DirectedGraphNode> nodes) {
        List<List<DirectedGraphNode>> res = new ArrayList<>();
        if (values == null || values.length == 0) {
            return res;
        }

        Map<Integer, List<DirectedGraphNode>> map = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            if (!map.containsKey(values[i])) {
                List<DirectedGraphNode> tribe = new ArrayList<>();
                tribe.add(nodes.get(i));
                map.put(values[i], tribe);
            } else {
                map.get(values[i]).add(nodes.get(i));
            }
        }

        for (Integer key : map.keySet()) {
            res.add(map.get(key));
        }

        return res;
    }


    public static void main(String[] args) {
        int counter = 1;
        int couplesNumber = 0;

        List<DirectedGraphNode> tribesList;
        tribesList = readGraph();


//        Set<Integer> unique = new HashSet<>();
//        List<Integer> indexesToDelete = new ArrayList<>();
//
//        for (int i = tribesList.size() - 1; i >= 0; i--) {
//            if (!unique.contains(tribesList.get(i).getVertexValue())) {
//                unique.add(tribesList.get(i).getVertexValue());
//            } else if (tribesList.get(i).getNeighbors().isEmpty()) {
//                indexesToDelete.add(i);
//            }
//        }
//
//        for (int i = indexesToDelete.size() - 1; i >= 0; i--) {
//            int remove_index = indexesToDelete.get(i);
//            tribesList.remove(remove_index);
//        }

        List<List<DirectedGraphNode>> superList;
        superList = connectedComponents(tribesList);

        for (List<DirectedGraphNode> elements : superList) {
            System.out.println("Tribe " + counter);
            for (DirectedGraphNode element : elements) {
                String outputElement = (element == elements.get(elements.size() - 1)) ? Integer.toString(element.getVertexValue()) : (Integer.toString(element.getVertexValue()) + " -> ");
                System.out.print(outputElement);
            }
            counter++;
            System.out.println();
        }

        for (List<DirectedGraphNode> tribe : superList) {
            for (DirectedGraphNode woman : tribe) {
                for (List<DirectedGraphNode> anotherTribe : superList) {
                    if (tribe != anotherTribe) {
                        for (DirectedGraphNode man : anotherTribe) {
                            if ((woman.getVertexValue() % 2 == 0) && (man.getVertexValue() % 2 == 1)) {
                                couplesNumber++;
                                System.out.printf("Couple %d: %d/%d \n", couplesNumber, woman.getVertexValue(), man.getVertexValue());
                            }
                        }
                    }
                }
            }
        }
        System.out.printf("\nFinal amount of couples: %d", couplesNumber);

    }
}

