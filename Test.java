

public class Test {

    public static void main(String[] args) {

        long startTime = System.nanoTime();
        UndirectedGraph g = new UndirectedGraph(410236, "input_files/Amazon0505.txt");
        long endTime = System.nanoTime();

        long duration = (endTime - startTime); 
        System.out.println("File Reading Time : " + duration);
    
        //System.out.println("Edgegs " + g.E());
		
		FourVertexSubgraphCounter counter = new FourVertexSubgraphCounter( g.E(), 
            g.W, g.offset, g.adj, g.edgeTau);
    
            

/*

# Directed graph (each unordered pair of nodes is saved once): Amazon0505.txt 
# Amazon product co-purchaisng network from May 05 2003
# Nodes: 410236 Edges: 3356824
# FromNodeId	ToNodeId
        int[][] e = counter.sampler();
        System.out.println("((" + e[0][0] + "," + e[0][1] + "), (" 
            + e[1][0]  + "," + e[1][1] + "), (" + e[2][0] + "," + e[2][1] + "))");

*/

        startTime = System.nanoTime();
        counter.threePathSampler(200000);
        endTime = System.nanoTime();
        duration = (endTime - startTime); 
        System.out.println("3-Path-Sampler Time : " + duration);
        counter.print();

        // 250, 1273 for medium txt

        // Directed graph (each unordered pair of nodes is saved once): Amazon0312.txt 
        // Amazon product co-purchaisng network from March 12 2003
        // Nodes: 400727, 3200440
        // FromNodeId	ToNodeId

	}
}
