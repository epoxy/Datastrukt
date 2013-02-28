package se.chalmers.datastrukt.lab3;

import java.util.*;

public class DirectedGraph<E extends Edge> {

	private List<E>[] graph;
	private int noOfNodes;
	private PriorityQueue<ComparableDijkstraPath<E>> prioQueue;
	private List<E> theShortestPath;
	private PriorityQueue<CompKruskalEdge<E>> mstprioQueue;
	private List<E>[] cc;
	//private List<E> tempLongest, tempShortest;

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
		cc = new List[noOfNodes];
		for (int i = 0; i < noOfNodes; i++) {
			cc[i] = new LinkedList<E>();
		}

		for (int i = 0; i < graph.length; i++) {
			for (E edge : this.graph[i]) {
				mstprioQueue.add(new CompKruskalEdge<E>(edge));
			}
		}

		List<E> tempLongest = null;
		List<E> tempShortest = null;
		while (mstprioQueue.size() != 0) {
			CompKruskalEdge<E> cKe = mstprioQueue.poll();

			if (!(cc[cKe.edge.from].contains(cKe.edge) && cc[cKe.edge.to]
					.contains(cKe.edge))) {

				if (cc[cKe.edge.from].size() >= cc[cKe.edge.to].size()) {
					tempLongest = cc[cKe.edge.from];
					tempShortest = cc[cKe.edge.to];

				} else {
					tempLongest = cc[cKe.edge.to];
					tempShortest = cc[cKe.edge.from];
				}

				;
				for (E edge : tempShortest) {
					tempLongest.add(edge);
				}
				tempShortest = tempLongest;
				tempLongest.add(cKe.edge);// 8
			}
		}
		return tempLongest.iterator();
	}


	private class CompKruskalEdge<E extends Edge> implements
			Comparable<CompKruskalEdge<E>> {
		public E edge;

		public CompKruskalEdge(E edge) {
			this.edge = edge;
		}

		@Override
		public int compareTo(CompKruskalEdge<E> e) {
			return Double.compare(e.edge.getWeight(), e.edge.getWeight());

		}
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
