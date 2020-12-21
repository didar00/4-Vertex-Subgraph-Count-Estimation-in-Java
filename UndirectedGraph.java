import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class UndirectedGraph {

	private final int V;
    private int E;
	public int W;
	public ArrayList<ArrayList<Integer>> adj;
	public int[][] edgeTau;
	public ArrayList<Integer> degree;


	public UndirectedGraph(int V, int E, String file) {
		if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
		this.E = E;
		
		adj = new ArrayList<ArrayList<Integer>>(V);
		// initializes the adjacency list
		for (int i = 0; i < V; i++) 
			adj.add(new ArrayList<Integer>()); 


		/**
         * 
         * reads text file with edge information in it,
         * adds each edge to the graph
         * 
         */
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			int u;
			int v;
			while ((line = br.readLine()) != null) {
				String[] vertices = line.split("\t");
				u = Integer.parseInt(vertices[0]);
				v = Integer.parseInt(vertices[1]);
				if(!isDuplicate(u, v)) {
					addEdge(u, v);
					System.out.println(vertices[0] + " " + vertices[1]);
				}
				
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		degree = initializeDegree();
		edgeTau = new int[2*E][3];
		W = W();

	}

	private boolean isDuplicate(int u, int v) {
		for (int e : adj.get(v)) {
			if (e == u) {
				return true;
			}
		}
		return false;
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
		adj.get(u).add(v);
		adj.get(v).add(u);
	}


	private ArrayList<Integer> initializeDegree() {
		ArrayList<Integer> degree = new ArrayList<Integer>();
		for (int i = 0; i < V; i++) {
			degree.add(getDegree(i));
		}
		return degree;
	}
	

	private int W() {
		int W = 0;
		int offset = 0;
		int index = 0;

		for (int u = 0; u < V; u++) {
			int degree1 = degree.get(u);
			// for the other vertices that compose an edge with u
			for (int v : adj.get(u)) {
				int degree2 = degree.get(v);
				int tau = (degree1-1)*(degree2-1);
				W += tau;
				offset += tau;
				// adds as edge-tau value pairs
				edgeTau[index][0] = u;
				edgeTau[index][1] = v;
				edgeTau[index][2] = offset;
				index++;
				//System.out.println("z value before if vprime " + z);
				/*
				if (!duplicateEdgeTauPair(u, v)) {
					edgeTau.add(new ArrayList<Integer>());
					offset += tau;
					// adds as edge-tau value pairs
					edgeTau.get(index).add(u);
					edgeTau.get(index).add(v);
					edgeTau.get(index).add(offset);
					index++;
				}
				*/
			}
		}
		return W/2;
	}

/*
	private boolean duplicateEdgeTauPair(int u, int v) {
		for (int i = 0; i < edgeTau.size(); i++) {
			Edge e = (Edge) edgeTau.get(i).get(0);
			if (edge.equals(e)) {
				return true;
			}
		}
		return false;
	}
*/

	public int getDegree(int v) {
		int degree = 0;
		degree += adj.get(v).size();
	
		return degree;
	}

}
