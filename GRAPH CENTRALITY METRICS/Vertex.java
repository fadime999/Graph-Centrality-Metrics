import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Vertex implements VertexInterface {
	private String name;
	private ListWithIteratorInterface<Edge> edgeList; // Edges to neighbors
	private VertexInterface prodecessor;
	private boolean visited;
	private double cost;
	private int betweennessCount;

	public Vertex(String name) {
		this.name = name;
		prodecessor = null;
		edgeList = new ArrayListWithIterator<>();
	}

	public void addEdge(Edge e) {
		edgeList.add(e);
	}

	public ListWithIteratorInterface<Edge> getEdges() {
		return edgeList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public VertexInterface getPredecessor() {
		return prodecessor;
	}

	public void setPredecessor(VertexInterface predecessor) {
		this.prodecessor = predecessor;

	}

	public Boolean getVisited() {
		return visited;
	}

	public void setVisited(Boolean visited) {
		this.visited = visited;
	}

	public boolean hasPredecessor() {
		if (getPredecessor() != null) {
			return true;
		} else {
			return false;
		}
	}

	public void setCost(double newCost) {
		this.cost = newCost;
	}

	public double getCost() {
		return cost;
	}

	public int getBetweennessCount() {
		return betweennessCount;
	}

	public void setBetweennessCount(int betweennessCount) {
		this.betweennessCount = betweennessCount;
	}

	public Iterator<VertexInterface> getNeighborIterator() {
		return new NeighborIterator();
	} // end getNeighborIterator

	private class NeighborIterator implements Iterator<VertexInterface> {
		private Iterator<Edge> tempedges;

		private NeighborIterator() {
			tempedges = getEdges().getIterator();
		} // end default constructor

		public boolean hasNext() {
			return tempedges.hasNext();
		} // end hasNext

		public VertexInterface next() {
			VertexInterface nextNeighbor = null;
			if (tempedges.hasNext()) {
				Edge edgeToNextNeighbor = tempedges.next();
				nextNeighbor = edgeToNextNeighbor.getDestination();
			} else
				throw new NoSuchElementException();
			return nextNeighbor;
		} // end next

		public void remove() {
			throw new UnsupportedOperationException();
		} // end remove
	} // end NeighborIterator

	public void visit() {
		setVisited(true);
	}

	public void unvisit() {
		setVisited(false);
	}

	public boolean isVisited() {
		if (getVisited()) {
			return true;
		} else {
			return false;
		}
	}

	public VertexInterface getUnvisitedNeighbor() {
		VertexInterface result = null;
		Iterator<VertexInterface> neighbors = getNeighborIterator();
		while (neighbors.hasNext() && (result == null)) {
			VertexInterface nextNeighbor = neighbors.next();
			if (!nextNeighbor.isVisited())
				result = nextNeighbor;
		} // end while
		return result;
	} // end getUnvisitedNeighbor

}
