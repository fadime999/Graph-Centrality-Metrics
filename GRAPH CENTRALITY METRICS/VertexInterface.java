
import java.util.Iterator;

public interface VertexInterface
 {

/** Marks this vertex as visited. */
 public void visit();

 /** Removes this vertex's visited mark. */
 public void unvisit();

/** Sees whether this vertex is marked as visited.
 @return True if the vertex is visited. */
 public boolean isVisited();

/** Connects this vertex and a given vertex with a weighted edge.
The two vertices cannot be the same, and must not already
 have this edge between them. In a directed graph, the edge
 points toward the given vertex.
 @param endVertex A vertex in the graph that ends the edge.
 @param edgeWeight A real-valued edge weight, if any.
 @return True if the edge is added, or false if not. */

 public Iterator<VertexInterface> getNeighborIterator();

/** Creates an iterator of the weights of the edges to this
vertex's neighbors.
@return An iterator of edge weights for edges to neighbors of this
 vertex. */

 /** Gets an unvisited neighbor, if any, of this vertex.
 @return Either a vertex that is an unvisited neighbor or null
if no such neighbor exists. */
 public VertexInterface getUnvisitedNeighbor();

/** Records the previous vertex on a path to this vertex.
 @param predecessor The vertex previous to this one along a path. */
 public void setPredecessor(VertexInterface predecessor);

/** Gets the recorded predecessor of this vertex.
 @return Either this vertex's predecessor or null if no predecessor
 was recorded. */
 public VertexInterface getPredecessor();
 /** Sees whether a predecessor was recorded for this vertex.
 @return True if a predecessor was recorded. */
 public boolean hasPredecessor();
 public void setCost(double newCost);
 public int getBetweennessCount();
 public void setBetweennessCount(int betweennessCount);
/** Gets the recorded cost of the path to this vertex.
 @return The cost of the path. */
public double getCost();

public String getName();

 } // end VertexInterface