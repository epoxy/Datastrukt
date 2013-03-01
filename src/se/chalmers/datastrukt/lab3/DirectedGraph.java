package se.chalmers.datastrukt.lab3;

import java.util.*;

/**
 * A class representing a directed graph especially used to represent 
 * busstops and the lines between them, as vertices and edges. 
 * The two most important methods are concerning paths between busstops. 
 * These mehods are called shortestPath and minimumSpanningTree,
 * and are explained in the javadoc below.
 * 
 * @author Tomas Sellden and Anton Palmqvist group 36
 * 
 * @param <E>
 */
public class DirectedGraph<E extends Edge> {

	private List<E>[] graph;
	private int noOfNodes;
	private PriorityQueue<ComparableDijkstraPath<E>> prioQueue;
	private List<E> theShortestPath;
	private PriorityQueue<CompKruskalEdge<E>> mstprioQueue;
	private List<E>[] cc;

	public DirectedGraph(int noOfNodes) {
		mstprioQueue = new PriorityQueue<CompKruskalEdge<E>>();
		this.noOfNodes = noOfNodes;
		this.prioQueue = new PriorityQueue<ComparableDijkstraPath<E>>();
		graph = new List[noOfNodes];
		for (int i = 0; i < noOfNodes; i++) {
			graph[i] = new LinkedList<E>();
		}
	}

	public void addEdge(E e) {
		graph[e.getSource()].add(e);
	}

	/**
	 * Method calculating the shortest path from a chosen busstop(start-node)
	 * to another chosen busstop(end-node).
	 * This is done with help of the Dijkstra-algorithm. Different paths are
	 * being created by adding adjecent unvisited edges to them. The paths
	 * are then being compared in weight by being put in- and polled from a 
	 * priority-queue. When finally a path that ends in the end-node is polled
	 * the path is complete and is being returned.
	 *  
	 * @param from The start-node aka the starting-point of the journey
	 * @param to The end-node aka the destination of the journey
	 * @return the shortest path from the startingpoint to the destination
	 */
	public Iterator<E> shortestPath(int from, int to) {
		boolean[] visitedNode = new boolean[noOfNodes];
		//The start-node is added to the prio-queue
		prioQueue.add(new ComparableDijkstraPath<E>(from));
		while (prioQueue.size() != 0) {
			/*The Dijkstra-path with the best priority (lowest weight) 
			is polled*/
			ComparableDijkstraPath<E> dE = prioQueue.poll();
			if (!visitedNode[dE.last]) {
				//If the goal(end-node) is reached the path is returned
				if (dE.last == to) {
					return dE.getPath().iterator();
				} else {
					//Marking the node as visited
					visitedNode[dE.last] = true;
					/*Looping through all edges adjacent to the 
					Dijkstra-path (dE)*/
					for (E edge : this.graph[dE.last]) {
						if (!visitedNode[edge.to]) {
							/*Each edge (with unvisited to-nodes) 
							are appended with the path and then added to 
							the priorityqueue */
							prioQueue.add(new ComparableDijkstraPath<E>(dE,
									edge));
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Finds the minimumspanningtree for a connected weighted graph which forms
	 * as a tree. So it finds a subset of all the edges in the graph, which
	 * include every vertex, where the total weight is minimized.
	 * 
	 * @return Iterator<E> that contain a list which store the
	 *         minimumspanningtree. return null if the graph is unconnected
	 *         (since if the graph is not connected a fully spanningtree of 
	 *         all verticescan not be given).
	 */
	public Iterator<E> minimumSpanningTree() {
		cc = new List[noOfNodes];
		for (int i = 0; i < noOfNodes; i++) {
			//All nodes are getting one list each where their edges will be put
			cc[i] = new LinkedList<E>();
		}
		for (int i = 0; i < graph.length; i++) {
			for (E edge : this.graph[i]) {
				//All edges are added to a prio queue
				mstprioQueue.add(new CompKruskalEdge<E>(edge));
			}
		}
		List<E> tempLongest = null; //edges of node with most edges
		List<E> tempShortest = null; //edges of node with the least edges
		while (mstprioQueue.size() != 0) {
			//The edge with the least weight is polled
			CompKruskalEdge<E> cKe = mstprioQueue.poll();
			if (cc[cKe.edge.from] != cc[cKe.edge.to]) {
				//if the edgelists does not already contain the edge
				if (!(cc[cKe.edge.from].contains(cKe.edge) && cc[cKe.edge.to]
						.contains(cKe.edge))) {
					int ishort = 0;
					//The longest edgelist is determined
					if (cc[cKe.edge.from].size() >= cc[cKe.edge.to].size()) {
						tempLongest = cc[cKe.edge.from];
						tempShortest = cc[cKe.edge.to];
						ishort = cKe.edge.to;
					} else {
						tempLongest = cc[cKe.edge.to];
						tempShortest = cc[cKe.edge.from];
						ishort = cKe.edge.from;
					}
					for (E edge : tempShortest) {
						/*The edges of the shorter edgelist is added to the 
						 * longer*/
						tempLongest.add(edge); 
						//The pointer is switched to the longest edgelist
						cc[edge.to] = tempLongest;
						cc[edge.from] = tempLongest;
					}
					/*The comparable Kruskal edge is added to the longest 
					 * edgelist*/
					tempLongest.add(cKe.edge);
					//The pointer is switched to the longest edgelist
					cc[ishort] = tempLongest;
				}
			}
		}
		/*If the tree is not a complete minimumspanningtree(does not include 
		 the entire graph) null is returned. This could be due to that 
		 the investigated graph is not fully connected.*/
		if (noOfNodes - 1 != tempLongest.size()) {
			return null;
		}
		return tempLongest.iterator();
	}

	/**
	 * An inner class representing a Kruskal edge that can be compared 
	 * by other Kruskal Edges.
	 *
	 * @param <E>
	 */
	private class CompKruskalEdge<E extends Edge> implements
	Comparable<CompKruskalEdge<E>> {
		public E edge;

		/**
		 * Constructor creating a comparable Kruskal edge of an edge
		 * @param edge the edge to make into a comparable Kruskal edge
		 */
		public CompKruskalEdge(E edge) {
			this.edge = edge;
		}

		@Override
		public int compareTo(CompKruskalEdge<E> e) {
			return Double.compare(edge.getWeight(), e.edge.getWeight());
		}
	}

	/**
	 * An inner class representing a dijkstra-path that can be compared by 
	 * other dijkstra-paths. It is containing a last node, a path of edges 
	 * from the start-node to the last node and the weight of the path.
	 *
	 * @param <E>
	 */
	private class ComparableDijkstraPath<E extends Edge> implements
	Comparable<ComparableDijkstraPath<E>> {
		public int last;
		public double weight;
		public List<E> theShortestPath = new LinkedList<E>();

		/**
		 * Constructor of making a dijkstra-path of the start-node
		 * @param node the node that will be set as the last node of 
		 * the path
		 */
		public ComparableDijkstraPath(int node) {
			this.last = node;
			this.weight = 0;
			this.theShortestPath = new LinkedList<E>();
		}

		/**
		 * Constructor to add a new edge to an existing path and making it 
		 * into a new dijkstra-path.
		 * @param path the dijkstra-path that should be extended
		 * @param edge the edge that is to extend the dijkstra-path
		 */
		public ComparableDijkstraPath(ComparableDijkstraPath<E> path, E edge) {
			this.weight = path.weight;
			this.theShortestPath = new LinkedList<E>(path.theShortestPath);
			addEdgeToShortestPath(edge);
		}

		/**
		 * Method adding an edge to a path, increasing the weight of the path 
		 * with the weight of the edge.
		 * @param edge the edge that is to extend the path
		 */
		public void addEdgeToShortestPath(E edge) {
			theShortestPath.add(edge);
			this.weight += edge.getWeight();
			this.last = edge.to;
		}

		public List<E> getPath() {
			List<E> temp = new LinkedList<E>();
			for (E path : theShortestPath) {
				temp.add(path);
			}
			return temp;
		}

		public int compareTo(ComparableDijkstraPath<E> other) {
			return Double.compare(weight, other.weight);
		}
	}
}
