import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	private HashMap<String, Vertex> vertices;
	public HashMap<String, Edge> edges;
	private Stack<String> path;

	public Graph() {
		this.vertices = new HashMap<>();
		this.edges = new HashMap<>();
	}

	public void addEdge(String source, String destination) {

		if (edges.get(source + "-" + destination) == null && edges.get(destination + "-" + source) == null) {
			Vertex source_v, destination_v;

			if (vertices.get(source) == null) {
				source_v = new Vertex(source);
				vertices.put(source, source_v);
			} else
				source_v = vertices.get(source);

			if (vertices.get(destination) == null) {
				destination_v = new Vertex(destination);
				vertices.put(destination, destination_v);
			} else
				destination_v = vertices.get(destination);

			Edge edge = new Edge(source_v, destination_v);
			Edge edge2 = new Edge(destination_v, source_v);
			source_v.addEdge(edge);
			destination_v.addEdge(edge);
			source_v.addEdge(edge2);
			destination_v.addEdge(edge2);
			edges.put(source + "-" + destination, edge);
			edges.put(destination + "-" + source, edge2);

		} else {
			System.out.println("This edge has already added!");
		}
	}

	public HashMap<String, Edge> getEdges() {
		return this.edges;
	}

	protected void resetVertices() {
		for (Vertex nextVertex : vertices.values()) {
			nextVertex.unvisit();
			nextVertex.setCost(0);
			nextVertex.setPredecessor(null);
		}
	} // end resetVertices

	public <T> int getShortestPath(T begin, T end, Stack<String> path) {
		resetVertices();
		boolean done = false;

		Queue<VertexInterface> vertexQueue = new LinkedList<VertexInterface>();
		VertexInterface originVertex = vertices.get(begin);
		VertexInterface endVertex = vertices.get(end);
		originVertex.visit();
		// Assertion: resetVertices() has executed setCost(0)
		// and setPredecessor(null) for originVertex
		vertexQueue.add(originVertex);
		while (!done && !vertexQueue.isEmpty()) {
			VertexInterface frontVertex = vertexQueue.remove();
			Iterator<VertexInterface> neighbors = frontVertex.getNeighborIterator();
			while (!done && neighbors.hasNext()) {
				VertexInterface nextNeighbor = neighbors.next();
				if (!nextNeighbor.isVisited()) {
					nextNeighbor.visit();
					nextNeighbor.setCost(1 + frontVertex.getCost());
					nextNeighbor.setPredecessor(frontVertex);
					vertexQueue.add(nextNeighbor);
				} // end if
				if (nextNeighbor.equals(endVertex))
					done = true;
			} // end while
		} // end while
			// Traversal ends; construct shortest path
		int pathLength = (int) endVertex.getCost();
		path.push(endVertex.getName());
		endVertex.setBetweennessCount(endVertex.getBetweennessCount() + 1);
		VertexInterface vertex = endVertex;
		while (vertex.hasPredecessor()) {
			vertex = vertex.getPredecessor();
			vertex.setBetweennessCount(vertex.getBetweennessCount() + 1);
			path.push(vertex.getName());
		} // end while
		return pathLength;
	} // end getShortestPath

	public void print() {
		System.out.println("Source\tDestination");
		for (Edge e : edges.values()) {
			System.out.println("" + e.getSource().getName() + "\t" + e.getDestination().getName() + "\t\t" + " ");
		}
	}

	public void highestBetweenness(String s) {
		int max = 0;
		String temp = null;
		for (Vertex v : vertices.values()) {
			if (v.getBetweennessCount() > max) {
				max = v.getBetweennessCount();
				temp = v.getName();
			}
		}
		if (s.equalsIgnoreCase("graph_karate")) {
			System.out.println("Zachary Karate Club Network – The Highest Node for Betweennes and the value: "
					+ "Node->" + temp + " Value->" + max);
		} else {
			System.out.println("Facebook Social Network - The Highest Node for Betweennes and the value: " + "Node->"
					+ temp + " Value->" + max);
		}
	}

	public int size() {
		return vertices.size();
	}

}
