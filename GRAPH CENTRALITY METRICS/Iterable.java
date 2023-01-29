
import java.util.Iterator;

public interface Iterable<T> {
	/** @return An iterator for a collection of objects of type T. */
	Iterator<T> iterator();

} // end Iterable