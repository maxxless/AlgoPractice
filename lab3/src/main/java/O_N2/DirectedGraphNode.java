package O_N2;

import java.util.ArrayList;

public class DirectedGraphNode {
    private int vertexValue;
    private ArrayList<DirectedGraphNode> neighbors;

    DirectedGraphNode(int x) {
        vertexValue = x;
        neighbors = new ArrayList<>();
    }

    public int getVertexValue() {
        return vertexValue;
    }

    public ArrayList<DirectedGraphNode> getNeighbors() {
        return neighbors;
    }
}