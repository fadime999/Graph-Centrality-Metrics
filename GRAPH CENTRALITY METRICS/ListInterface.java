/** An interface for the ADT list.
 Entries in a list have positions that begin with 1.3 @author Frank M. Carrano
 */
 public interface ListInterface<T>
 {
 /** Adds a new entry to the end of this list.
 Entries currently in the list are unaffected.
 The list’s size is increased by 1.
@param newEntry The object to be added as a new entry. */
 public void add(T newEntry);

/** Adds a new entry at a specified position within this list.
14 Entries originally at and above the specified position
15 are at the next higher position within the list.
16 The list’s size is increased by 1.
17 @param newPosition An integer that specifies the desired
18 position of the new entry.
19 @param newEntry The object to be added as a new entry.
20 @throws IndexOutOfBoundsException if either
21 newPosition < 1 or newPosition > getLength() + 1. */
 public void add(int newPosition, T newEntry);

 /** Removes the entry at a given position from this list.
25 Entries originally at positions higher than the given
26 position are at the next lower position within the list,
27 and the list’s size is decreased by 1.
28 @param givenPosition An integer that indicates the position of
29 the entry to be removed.
30 @return A reference to the removed entry.
31 @throws IndexOutOfBoundsException if either
32 givenPosition < 1 or givenPosition > getLength(). */
 public T remove(int givenPosition);
 /** Removes all entries from this list. */
 public void clear();
 /** Replaces the entry at a given position in this list.
39 @param givenPosition An integer that indicates the position of
40 the entry to be replaced.
41 @param newEntry The object that will replace the entry at the
42 position givenPosition.
43 @return The original entry that was replaced.
44 @throws IndexOutOfBoundsException if either
45 givenPosition < 1 or givenPosition > getLength(). */
 public T replace(int givenPosition, T newEntry);
 /** Retrieves the entry at a given position in this list.
49 @param givenPosition An integer that indicates the position of
50 the desired entry.
51 @return A reference to the indicated entry.
52 @throws IndexOutOfBoundsException if either
53 givenPosition < 1 or givenPosition > getLength(). */
 public T getEntry(int givenPosition);

/** Retrieves all entries that are in this list in the order in which
57 they occur in the list.
58 @return A newly allocated array of all the entries in the list.
59 If the list is empty, the returned array is empty. */
public T[] toArray();
 /** Sees whether this list contains a given entry.
63 @param anEntry The object that is the desired entry.
64 @return True if the list contains anEntry, or false if not. */
 public boolean contains(T anEntry);
 /** Gets the length of this list.
68 @return The integer number of entries currently in the list. */
 public int getLength();
 } // end ListInterface