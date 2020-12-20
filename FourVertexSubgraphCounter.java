import java.util.ArrayList;
import java.util.Random;

public class FourVertexSubgraphCounter {
	/**
	 * motif counts
	 */
	public int c1; // 3-star
	public int c2; // 3-path
	public int c3; // tailed-triangle
	public int c4; // 4-cycle
	public int c5; // chordal-4-cycle
	public int c6; // 4-clique

	public FourVertexSubgraphCounter() {
		// initialize motif counts to zero
		c1 = c2 = c3 = c4 = c5 = c6 = 0;

	}

    /** sampler algorithm to obtain a set of edges that compose a 3-path */
	public Edge[] sampler(int E, int W, ArrayList<ArrayList<Edge>> adj, ArrayList<ArrayList<Object>> edgeTau) {
		Edge[] setOfEdges = new Edge[3];
		Edge middleEdge = null;
		Edge uPrime = null;
		Edge vPrime = null;

		print(edgeTau);
		// pick middle edge e = (u,v) with probability p_e = T_e/W
		Random rand = new Random();
		int x = rand.nextInt(W);

		for (int i = 0; i < E; i++) {
			if (x < (int) edgeTau.get(i).get(1)) {
				if (i == 0) {
					middleEdge = (Edge) edgeTau.get(i).get(0);
				}else {
					if (x >= (int) edgeTau.get(i-1).get(1)) {
						middleEdge = (Edge) edgeTau.get(i).get(0);
					}
				}
			}
		}

		// if the middle edge selected is not valid
		if (middleEdge == null)
			throw new NullPointerException("Middle edge couldn't be selected");

		System.out.println("middle edge " + middleEdge.toString());
		/** selects the neighbors of the middle edge vertex */
		int u = middleEdge.either();
		int v = middleEdge.other(u);
		// select a random neighbor uPrime different than u
		int y = rand.nextInt(adj.get(u).size());
		System.out.println("size of adj.get(u).size() " + adj.get(u).size());
		System.out.println("y value before if uprime " + y);
		if (middleEdge.equals(adj.get(u).get(y))) {
			if (y == adj.get(u).size() -1) {
				y = 0;
			}else {
				y++;
			}
		}
		System.out.println("y value after if uprime " + y);
		uPrime = adj.get(u).get(y);



		// select a random neighbor vPrime different than v
		int z = rand.nextInt(adj.get(v).size());
		System.out.println("size of adj.get(v).size() " + adj.get(v).size());
		System.out.println("z value before if vprime " + z);
		if (middleEdge.equals(adj.get(v).get(z))) {
			if (z == adj.get(v).size() -1) {
				z = 0;
			}else {
				z++;
			}
		}
		System.out.println("z value after if vprime " + z);
		vPrime = adj.get(v).get(z);

		System.out.println("uprime " + uPrime);
		System.out.println("vprime " + vPrime);
		/*
		// checks if the selected neighbor vertices are valid
		if (uPrime == null || vPrime == null)
			throw new NullPointerException("Edges cannot be null");
			*/

		setOfEdges[0] = uPrime; setOfEdges[1] = middleEdge; setOfEdges[2] = vPrime;
		
		return setOfEdges;
	}

	public void print(ArrayList<ArrayList<Object>> edgeTau) {
		for (int i= 0; i < edgeTau.size(); i++) {
			System.out.println(edgeTau.get(i).get(0).toString() + " " + edgeTau.get(i).get(1));
		}
	}


	public void threePathSampler() {

	}

	
}