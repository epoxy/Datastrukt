package se.chalmers.datastrukt.lab3;

import java.util.*;

import sun.awt.windows.ThemeReader;

public class DirectedGraph<E extends Edge> {

	private List<E>[] edges;
	private CompKruskalEdge cKE = new CompKruskalEdge();
	private int noOfNodes;
	private PriorityQueue<DijkstraElement> prioQueue;

	public DirectedGraph(int noOfNodes) {
		this.noOfNodes = noOfNodes;
		this.prioQueue = new PriorityQueue<DijkstraElement>();
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

		prioQueue.add(new DijkstraElement(from));
		while (prioQueue.size() != 0) {
			DijkstraElement dE = prioQueue.poll();
			if (!visitedNode[dE.edgeNo]) {
				if (dE.edgeNo == to) {
					dE.getPath().iterator();
				} else {
					visitedNode[dE.edgeNo] = true;
					for (E edge : this.edges[dE.edgeNo]) {
						visitedNode[edge.to] = true;
						prioQueue.add(new DijkstraElement(edge));
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

	private class DijkstraElement<E extends Edge> implements
			Comparable<DijkstraElement> {
		// private public kmr Œt dem i klassen eftersom dem Šr publika men ej
		// utanfšr klassen eftersom dem Šr i en inre klass!
		public int edgeNo;
		public double weight;
		public List<E> theShortestPath;
	
		public DijkstraElement(int from) {
			this.edgeNo = from;
			this.weight = 0;
			this.theShortestPath = new LinkedList<E>();
			

		}

		public DijkstraElement(E edge) {
			this.edgeNo = edge.from; // to?
			this.weight = edge.getWeight();			
			theShortestPath.add(edge);

		}
		public List<E> getPath() {
			List<E> temp = new LinkedList<E>();
			
			for(E elem : this.theShortestPath) {
				temp.add(elem);
			}
			
			return temp;
		}

		public int compareTo(DijkstraElement other) {
			return Double.compare(weight, other.weight);
		}
	}
}
