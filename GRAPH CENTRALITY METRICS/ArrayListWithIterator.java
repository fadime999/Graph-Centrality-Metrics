import java.security.PublicKey;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayListWithIterator<T> implements ListWithIteratorInterface<T> {
	private T[] listt; // Array of list entries; ignore list[0]
	private int numberOfEntries;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;

	public ArrayListWithIterator() {
		this(DEFAULT_CAPACITY);
	} // end default constructor

	public ArrayListWithIterator(int initialCapacity) {
		// Is initialCapacity too small?
		if (initialCapacity < DEFAULT_CAPACITY)
			initialCapacity = DEFAULT_CAPACITY;
		else // Is initialCapacity too big?
			checkCapacity(initialCapacity);

		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempList = (T[]) new Object[initialCapacity + 1];
		listt = tempList;
		numberOfEntries = 0;
		initialized = true;
	} // end constructor

	public void add(T newEntry) {
		checkInitialization();
		listt[numberOfEntries + 1] = newEntry;
		numberOfEntries++;
		ensureCapacity();
	} // end add

	private void makeRoom(int newPosition) {
		int newIndex = newPosition;
		int lastIndex = numberOfEntries;
		for (int index = lastIndex; index >= newIndex; index--)
			listt[index + 1] = listt[index];
	} // end makeRoom

	public void add(int newPosition, T newEntry) {
		checkInitialization();
		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
			if (newPosition <= numberOfEntries)
				makeRoom(newPosition);
			listt[newPosition] = newEntry;
			numberOfEntries++;
			ensureCapacity(); // Ensure enough room for next add
		} else
			throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
	} // end add

	private void removeGap(int givenPosition) {
		assert (givenPosition >= 1) && (givenPosition < numberOfEntries);
		int removedIndex = givenPosition;
		int lastIndex = numberOfEntries;
		for (int index = removedIndex; index < lastIndex; index++)
			listt[index] = listt[index + 1];
	} // end removeGap

	public T remove(int givenPosition) {
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			T result = listt[givenPosition]; // Get entry to be removed
			// Move subsequent entries toward entry to be removed,
			// unless it is last in list
			if (givenPosition < numberOfEntries)
				removeGap(givenPosition);
			numberOfEntries--;
			return result; // Return reference to removed entry
		} else
			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
	} // end remove

	public void clear() {
		for (int i = 0; i < listt.length; i++) {
			remove(i);
		}
	} // end clear

	public T replace(int givenPosition, T newEntry) {
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			T originalEntry = listt[givenPosition];
			listt[givenPosition] = newEntry;
			return originalEntry;
		} else
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
	} // end replace

	public T getEntry(int givenPosition) {
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			return listt[givenPosition];
		} else
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
	} // end getEntry

	private void checkInitialization() {
		if (!initialized)
			throw new SecurityException("ArrayBag object is not initialized " + "properly.");
	} // end checkInitialization

	public T[] toArray() {
		checkInitialization();
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];
		for (int index = 0; index < numberOfEntries; index++) {
			result[index] = listt[index + 1];
		} // end for
		return result;
	} // end toArray

	public boolean contains(T anEntry) {
		checkInitialization();
		boolean found = false;
		int index = 1;
		while (!found && (index <= numberOfEntries)) {
			if (anEntry.equals(listt[index]))
				found = true;
			index++;
		} // end while
		return found;
	} // end contains

	public int getLength() {
		return numberOfEntries;
	} // end getLength

	public boolean isEmpty() {
		return numberOfEntries == 0; // Or getLength() == 0
	} // end isEmpty
		// Doubles the capacity of the array list if it is full.
		// Precondition: checkInitialization has been called.

	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY)
			throw new IllegalStateException(
					"Attempt to create a bag whose " + "capacity exeeds allowed " + "maximum of " + MAX_CAPACITY);
	} // end checkCapacity

	private void ensureCapacity() {
		int capacity = (listt.length - 1);
		if (numberOfEntries >= capacity) {
			int newCapacity = 2 * capacity;
			checkCapacity(newCapacity); // Is capacity too big?
			listt = Arrays.copyOf(listt, newCapacity + 1);
		} // end if
	} // end ensureCapacity

	public Iterator<T> iterator() {
		return new IteratorForArrayList();
	} // end iterator

	public Iterator<T> getIterator() {
		return iterator();
	} // end getIterator

	public class IteratorForArrayList implements Iterator<T> {
		public int nextIndex; // Index of next entry
		private boolean wasNextCalled; // Needed by remove

		private IteratorForArrayList() {
			nextIndex = 1; // Begin at list's first entry
			wasNextCalled = false;
		} // end default constructor

		public boolean hasNext() {
			return nextIndex <= numberOfEntries;
		} // end hasNext

		public T next() {
			if (hasNext()) {
				wasNextCalled = true;
				T nextEntry = listt[nextIndex];
				nextIndex++; // Advance iterator
				return nextEntry;
			} else {
				throw new NoSuchElementException("Illegal call to next(); " + "iterator is after end of list.");
			}

		} // end next

		public void remove() {
			if (wasNextCalled) {
				// nextIndex was incremented by the call to next, so it is
				// 1 larger than the position number of the entry to be removed
				ArrayListWithIterator.this.remove(nextIndex - 1);
				nextIndex--; // Index of next entry in iteration
				wasNextCalled = false; // Reset flag
			} else
				throw new IllegalStateException("Illegal call to remove(); " + "next() was not called.");
		} // end remove
	} // end IteratorForArrayList
}
// end ArrayListWithIterator