package se.chalmers.datastrukt.lab3;

import java.util.*;

public class DirectedGraph<E extends Edge> {

	private List<E>[] graph;
	private int noOfNodes;
	private PriorityQueue<ComparableDijkstraPath<E>> prioQueue;
	private List<E> theShortestPath;

	public DirectedGraph(int noOfNodes) {
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

	public Iterator<E> shortestPath(int from, int to) {
		boolean[] visitedNode = new boolean[noOfNodes];
		prioQueue.add(new ComparableDijkstraPath<E>(from));
		while (prioQueue.size() != 0) {
			ComparableDijkstraPath<E> dE = prioQueue.poll();
			if (!visitedNode[dE.last]) {
				if (dE.last == to) {
					return dE.getPath().iterator();
				} else {
					visitedNode[dE.last] = true;

					for (E edge : this.graph[dE.last]) {
						if (!visitedNode[edge.to]) {
							prioQueue.add(new ComparableDijkstraPath<E>(dE,
									edge));
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
			Comparable<ComparableDijkstraPath<E>> {
		public int last;
		public double weight;
		public List<E> theShortestPath = new LinkedList<E>();;

		public ComparableDijkstraPath(int from) {
			this.last = from;
			this.weight = 0;
			this.theShortestPath = new LinkedList<E>();

		}

		public ComparableDijkstraPath(ComparableDijkstraPath<E> path, E edge) {
			this.weight = path.weight;
			this.theShortestPath = new LinkedList<E>(path.theShortestPath);
			addEdgeToShortestPath(edge);

		}

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
