
public class Edge {
	private VertexInterface source;
	private VertexInterface destination;
	
	public Edge(VertexInterface source, VertexInterface destination) {
		this.source = source;
		this.destination = destination;
	}

	public VertexInterface getSource() {
		return source;
	}

	public void setSource(VertexInterface source) {
		this.source = source;
	}

	public VertexInterface getDestination() {
		return destination;
	}

	public void setDestination(VertexInterface destination) {
		this.destination = destination;
	}

}
