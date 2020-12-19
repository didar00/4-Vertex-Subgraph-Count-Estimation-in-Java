public class Edge {
    private final int u;
    private final int v;

    Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }

    /**
     * Returns either endpoint of this edge.
     *
     * @return either endpoint of this edge
     */
    public int either() {
        return u;
    }

    /**
     * Returns the endpoint of this edge that is different from the given vertex.
     *
     * @param vertex one endpoint of this edge
     * @return the other endpoint of this edge
     * 
     */
    public int other(int vertex) {
        if (vertex == u)
            return v;
        else if (vertex == v)
            return u;
        else
            return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Edge edge = (Edge) o;
        return u == edge.u && v == edge.v;
    }

    public String toString() {
        return String.format("%d-%d", u, v);
    }

}
