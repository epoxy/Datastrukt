package se.chalmers.datastrukt.lab3;

import java.util.*;

public class DirectedGraph<E extends Edge> {

	private List<E>[] edges; 
	private CompKruskalEdge cKE = new CompKruskalEdge();

	public DirectedGraph(int noOfNodes) {
		edges = new List[noOfNodes];
		for (int i = 0; i < noOfNodes; i++) {
			edges[i] = new LinkedList<E>();
		}
	}

	public void addEdge(E e) {
		edges[e.getSource()].add(e); // får den med vikten nu?
	}

	public Iterator<E> shortestPath(int from, int to) {
		PriorityQueue<ComparableDijkstraPath> queue = new PriorityQueue<ComparableDijkstraPath>();
		for(int i =0;i<edges.length;i++){	
			for (int j = 0; j < edges[i].size(); j++) {
				queue.add(new ComparableDijkstraPath(edges[i].get(j)));
			}
		}
		return null;
	}

	public Iterator<E> minimumSpanningTree() {
		return null;
	}
	private class CompKruskalEdge {
		
	}
	private class ComparableDijkstraPath implements Comparable<E> {
		private E edge;
		public ComparableDijkstraPath(E e) {
			this.edge=e;
		}
//		
//		public int getFrom() {
//			return edge.getSource();
//		}
//		public int getDest() {
//			return edge.getDest();
//		}

		@Override
		public int compareTo(E e) {
			return Double.compare(e.getWeight(), edge.getWeight());
		}
	}
}
