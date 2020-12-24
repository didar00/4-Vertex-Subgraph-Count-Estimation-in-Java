import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class UndirectedGraph {

	private final int V;
    private int E;
	public long W;
	public ArrayList<ArrayList<Integer>> adj;
	public long[][] edgeTau;
	public long offset;



	public UndirectedGraph(int V, int E, String file) {
		if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
		this.E = E;
		offset = 0;
		
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
				}
				
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		edgeTau = new long[2*E][3];
		W = W();
	}

	private boolean isDuplicate(int u, int v) {
		for (int e : adj.get(u)) {
			if (e == v) {
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
		adj.get(u).add(v);
		adj.get(v).add(u);
	}


	private long W() {
		long W = 0;
		int index = 0;

		for (int u = 0; u < V; u++) {
			int degree1 = adj.get(u).size();
			// for the other vertices that compose an edge with u
			for (int v : adj.get(u)) {
				int degree2 = adj.get(v).size();
				int tau = (degree1-1)*(degree2-1);
				W += tau;
				offset += tau;
				// adds as edge-tau value pairs
				edgeTau[index][0] = u;
				edgeTau[index][1] = v;
				edgeTau[index][2] = offset;
				index++;
			}
		}

		return W/2;
	}

}
