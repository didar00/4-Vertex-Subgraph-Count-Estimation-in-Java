import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class UndirectedGraph {

	private final int V;
    private int E;
	private int W;
	private ArrayList<ArrayList<Edge>> adj;
	private ArrayList<ArrayList<Object>> edgeTau;

	public UndirectedGraph(int size) {
		if (size < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = size;
        this.E = 0;
		adj = new ArrayList<ArrayList<Edge>>(V);
		// initializes the adjacency list
		for (int i = 0; i < V; i++) 
			adj.add(new ArrayList<Edge>()); 
			

		/**
         * 
         * reads text file with edge information in it,
         * adds each edge to the graph
         * 
         */
		try {
			BufferedReader br = new BufferedReader(new FileReader("mediumG.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				String[] vertices = line.split(" ");
				addEdge(Integer.parseInt(vertices[0]), Integer.parseInt(vertices[1]));
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		edgeTau = new  ArrayList<ArrayList<Object>>();
		
		W = W();

	}

	/**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
	}
	

	public void addEdge(int u, int v) {
        E++;
		Edge e = new Edge(u, v);
		adj.get(u).add(e);
		adj.get(v).add(e);
	}

	public void print() {
		for (int i= 0; i < edgeTau.size(); i++) {
			System.out.println(edgeTau.get(i).get(0).toString() + " " + edgeTau.get(i).get(1));
		}
	}
	
	/** sampler algorithm to obtain a set of edges that compose a 3-path */
	public Edge[] sampler() {
		Edge[] setOfEdges = new Edge[3];
		Edge middleEdge = null;
		Edge uPrime = null;
		Edge vPrime = null;

		print();
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

	private int W() {
		int W = 0;
		int offset = 0;
		int index = 0;

		for (int u = 0; u < V; u++) {
			int degree1 = getDegree(u);
			
			for (Edge e : adj.get(u)) {
				// the other vertex of the edge
				int v = e.other(u);
				int degree2 = getDegree(v);
				int tau = (degree1-1)*(degree2-1);
				W += tau;
				//System.out.println("z value before if vprime " + z);
				if (!isDuplicateEdge(e)) {
					edgeTau.add(new ArrayList<Object>());
					offset += tau;
					// adds as edge-tau value pairs
					edgeTau.get(index).add(e);
					edgeTau.get(index).add(offset);
					index++;
				}
			}
		}
		return W/2;
	}

	private boolean isDuplicateEdge(Edge edge) {
		for (int i = 0; i < edgeTau.size(); i++) {
			Edge e = (Edge) edgeTau.get(i).get(0);
			if (edge.equals(e)) {
				return true;
			}
		}
		return false;
	}

	private int getDegree(int v) {
		int degree = 0;
		degree += adj.get(v).size();
	
		return degree;
	}

}
