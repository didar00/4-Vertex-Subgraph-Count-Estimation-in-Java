

public class Test {

    public static void main(String[] args) {
     
        UndirectedGraph g = new UndirectedGraph(250, "input_files/mediumG.txt");
        FourVertexSubgraphCounter counter = new FourVertexSubgraphCounter();
        Edge[] e = counter.sampler(g.E(), g.W(), g.adj, g.edgeTau);
        System.out.println("((" + e[0].toString() + "), (" 
            + e[1].toString() + "), (" + e[2].toString() + "))");


	}
}
