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
	public ArrayList<ArrayList<Edge>> adj;
	public ArrayList<ArrayList<Object>> edgeTau;

	public UndirectedGraph(int size, String file) {
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
			BufferedReader br = new BufferedReader(new FileReader(file));
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

	public int W() {
		return W;
	}
	

	public void addEdge(int u, int v) {
        E++;
		Edge e = new Edge(u, v);
		adj.get(u).add(e);
		adj.get(v).add(e);
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
