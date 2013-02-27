package se.chalmers.datastrukt.lab3;

import java.util.*;

import sun.awt.windows.ThemeReader;

public class DirectedGraph<E extends Edge> {

	private List<E>[] edges;
	private CompKruskalEdge cKE = new CompKruskalEdge();
	private int noOfNodes;
	private PriorityQueue<ComparableDijkstraPath> prioQueue;
	private List<E> theShortestPath;

	public DirectedGraph(int noOfNodes) {
		theShortestPath = new LinkedList<E>();
		this.noOfNodes = noOfNodes;
		this.prioQueue = new PriorityQueue<ComparableDijkstraPath>();
		edges = new List[noOfNodes];
		for (int i = 0; i < noOfNodes; i++) {
			edges[i] = new LinkedList<E>();
		}
	}

	public void addEdge(E e) {
		edges[e.getSource()].add(e);

	}

	public Iterator<E> shortestPath(int from, int to) {
		boolean[] visitedNode = new boolean[noOfNodes];
		ComparableDijkstraPath<E> dE = new ComparableDijkstraPath<E>(from, 0, theShortestPath);
		prioQueue.add(dE);
		while (prioQueue.size() != 0) {
			 dE = prioQueue.poll();
			if (!visitedNode[dE.to]) {
				if (dE.to == to) {
					return dE.getPath().iterator();
				} else {
					visitedNode[dE.to] = true;
					for (E edge : this.edges[dE.to]) {
						if (!visitedNode[edge.to]) {
							dE = new ComparableDijkstraPath<E>(edge.to, dE.weight + edge.getWeight(), theShortestPath);
							
							prioQueue.add(dE);
							
						}
					}
				}
			}
		}
		return null;

	}

	public Iterator<E> minimumSpanningTree() {
		return null;
	}

	private class CompKruskalEdge {

	}

	private class ComparableDijkstraPath<E extends Edge> implements
			Comparable<ComparableDijkstraPath> {
		// private public kmr åt dem i klassen eftersom dem är publika men ej
		// utanför klassen eftersom dem är i en inre klass!
		public int to;
		public double weight;
		public List<E> theShortestPath = new LinkedList<E>();;

		/*public ComparableDijkstraPath(int from) {
			this.to = from;
			this.weight = 0;
			this.theShortestPath = new LinkedList<E>();

		}

		public ComparableDijkstraPath(ComparableDijkstraPath<E> path, E edge) {
			this.to = path.to; // to?
			this.weight = path.weight;// hmmm
			this.theShortestPath = new LinkedList<E>(path.theShortestPath);
			addpathentToShortestPath(theShortestPath, edge);

		}*/
		public ComparableDijkstraPath(int to, double weight, List<E> theShortestPath) {
			this.to = to;
			this.weight += weight; // plusa på hela vikten så att hela shortestPathvikten finns med
			theShortestPath = theShortestPath;
		}
		
			
		public void addpathentToShortestPath(List<E> shPath, E edge) {
			//theShortestPath.add(edge);
			this.weight += edge.getWeight();
			this.to = edge.to;
		}

		public List<E> getPath() {
			List<E> temp = new LinkedList<E>();

			for (E path : theShortestPath) {
				temp.add(path);
			}

			return temp;
		}

		public int compareTo(ComparableDijkstraPath other) {
			return Double.compare(weight, other.weight);
		}
	}
}
