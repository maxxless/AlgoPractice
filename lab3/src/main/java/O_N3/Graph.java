package O_N3;

import java.util.*;


public class Graph {
    private static final int MAXV = 100;
    private static final int MAXDEGREE = 50;
    public int verticesNumber;
    public int[][] edges = new int[MAXV + 1][MAXDEGREE];
    public List<Integer> degree = new ArrayList<>();


    Graph() {
        verticesNumber = 0;
        for (int i = 0; i < MAXDEGREE; i++) {
            degree.add(0);
        }
    }

    void readGraph() {
        int x, y;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of couples: ");
        verticesNumber = sc.nextInt();

        System.out.println("Enter the edges: <from> <to>");
        for (int i = 0; i < verticesNumber; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            insert_edge(x, y, false);
        }
        sc.close();
    }

    private void insert_edge(int x, int y, boolean directed) {
        edges[x][degree.get(x)] = y;
        degree.set(x, degree.get(x) + 1);
        if (!directed)
            insert_edge(x, y, true);
    }

}