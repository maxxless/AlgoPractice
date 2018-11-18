package graph_realization;

public class Vertex {
    final private int value;

    Vertex(int value) {
        this.value = value;
    }

    private int getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == -1) ? 0 : getValue());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (value == -1) {
            return other.value == 0;
        } else return value == other.value;
    }

    @Override
    public String toString() {
        return "value = " + getValue();
    }
}