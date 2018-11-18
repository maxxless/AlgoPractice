package graph_realization;

import java.util.*;


public class Graph {
    private final List<Vertex> vertexes;
    private final List<Edge> edges;

    Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}