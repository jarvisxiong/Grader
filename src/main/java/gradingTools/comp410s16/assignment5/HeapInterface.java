package gradingTools.comp410s16.assignment5;


public interface HeapInterface {

  /*
    Interface: The BHEAP will provide this collection of operations:

    insert
      in: an EntryPair object, containing the priority and string
      return: void
    delMin
      in: nothing
      return: void
    getMin
      in: nothing
      return: an element (an EntryPair object)
    size
      in: nothing
      return: integer 0 or greater
    build
      in: array of elements that need to be in the heap
      return: void
      (assume for a build that the bheap will start empty)
  */

    // ADT operations

    void insert(EntryPair entry);
    void delMin();
    EntryPair getMin();
    int size();
    void build(EntryPair [] entries);
}