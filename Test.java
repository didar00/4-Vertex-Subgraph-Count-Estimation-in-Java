

public class Test {

    public static void main(String[] args) {
     
        UndirectedGraph g = new UndirectedGraph(400727, "input_files/Amazon0312.txt");
        
        FourVertexSubgraphCounter counter = new FourVertexSubgraphCounter(g.E(), g.W, g.adj, g.degree, g.edgeTau);
        Edge[] e = counter.sampler();
        System.out.println("((" + e[0].toString() + "), (" 
            + e[1].toString() + "), (" + e[2].toString() + "))");
            

        // 250 - 1273 for medium txt

        // Directed graph (each unordered pair of nodes is saved once): Amazon0312.txt 
        // Amazon product co-purchaisng network from March 12 2003
        // Nodes: 400727 Edges: 3200440
        // FromNodeId	ToNodeId

	}
}
