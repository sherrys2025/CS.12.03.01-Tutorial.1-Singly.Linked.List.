public class SinglyLinkedList<T> {

    // Inner Node class.
    public class Node<T> {
        /**Properties of the Node class.
          * The two properties should be:
          * 1. data (the data stored in the node).
          * 2. next (a reference (also known as a pointer) to the next node.
          */
        T data;
        Node<T> next;

        /**Constructor of the Node class.
         * The constructor should set the data property of the Node to be the value passed as a parameter.
         * The constructor should set the next property of the Node to be null.
         */

        public Node(){
            data = null;
            next = null;
        }
        public Node(T data){
            this.data = data;
            next = null;
        }

        public Node(T data, Node<T> next){
            this.data = data;
            this.next = next;
        }
    }

    /**
     * Properties of the Singly Linked List class.
     * The three properties should be:
     * 1. size (records the number of nodes in our Singly Linked List)
     * 2. head (a reference to the first (also known as the head) node in our Singly Linked List).
     * 3. tail (a reference to the last (also known as the tail) node in our Singly Linked List.
    */

    int size;
    Node<T> head;
    Node<T> tail;

    /** Constructor.
     *  Creates a Singly Linked List with a head node.
     */

    public SinglyLinkedList(T value) {
        head = new Node(value);
        tail = head;
        size = 1;
    }

    /** traversal
     *  helper method to traverse through the linked list
     */
    private Node traversal(int index) {
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    // Methods

    /** size
     * returns the size of the Singly Linked List.
     */
    public int size() {
        return size;
    }

    /** isEmpty
     * returns whether the Singly Linked List is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /** peekFirst
     * returns the data stored in the head node.
     * throws a run time exception if the Singly Linked List is empty.
     */

    public T peekFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Singly Linked List is empty.");
        }
        return head.data;
    }

    /** peekLast
     * returns the data stored in the tail node.
     * throws a run time exception if the Singly Linked List is empty.
     */
    public T peekLast() {
        if (isEmpty()) {
            throw new RuntimeException("Singly Linked List is empty.");
        }
        return tail.data;
    }

    /** addFirst
     * Adds a node to the front of the Singly Linked List.
     * If the Singly Linked List is empty,
     */
    public void addFirst(T value) {
        if (isEmpty()) {
            head = new Node(value);
            tail = head;
            size = 1;
        } else {
            Node<T> newHead = new Node(value, head);
            head = newHead;
            size++;
        }
    }

    /** addLast
     * Adds a node to the back of the Singly Linked List.
     */
    public void addLast(T value) {
        if (isEmpty()) {
            head = new Node(value);
            tail = head;
            size = 1;
        } else {
            Node<T> newTail = new Node(value);
            tail.next = newTail;
            tail = newTail;
            size++;
        }
    }

    /** insertAt
     * Inserts a node at a specific index.
     * If the index is equal to 0, then we can invoke the addFirst method.
     * If the index is equal to size, then we can invoke the addLast method.
     * throws an illegal argument exception if the index is invalid.
     */
    public void insert(T value, int index) {
        if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            addLast(value);
        } else {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("index to be inserted is out of index.");
            }

            Node prev = traversal(index-1);
            Node toAdd = new Node(value, prev.next);
            prev.next = toAdd;
            size++;
        }
    }

    /** removeFirst
     * Removes the first (also known as the head node) from the Singly Linked List.
     * Throws a run time exception if the Singly Linked List is empty.
     * Returns the data stored in the head node.
     * If the size of the Singly Linked List becomes 0, need to set the tail to null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot remove nodes from an empty Linked List.");
        }

        Node toDelete = head;
        head = head.next;
        size--;
        return (T) toDelete.data;
    }

    /** removeLast
     * Removes the last (also known as the tail node) from the Singly Linked List.
     * Throws a run time exception if the Singly Linked List is empty.
     * Returns the data stored in the tail node.
     * If the size of the Singly Linked List becomes 0, need to set the tail to null.
     */
    public T removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot remove nodes from an empty Linked List.");
        }
        Node toDelete = tail;
        tail = traversal(size-2);
        tail.next = null;
        size--;
        return (T) toDelete.data;

    }

    /** removeAt
     * Removes a node at a specific index.
     * Returns the data stored in the removed node.
     * If the index is equal to 0, then we can invoke the removeFirst method.
     * If the index is equal to size-1, then we can invoke the removeLast method.
     * throws an illegal argument exception if the index is invalid.
     */
    public T removeAt(int index) {
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index out of bounds.");
        }

        Node before = traversal(index-1);
        Node toDelete = before.next;
        before.next = toDelete.next;
        size--;
        return (T) toDelete.data;

    }

    /** contains
     * Determines whether the Singly Linked List contains a node that holds data equivalent to the value passed.
     * Returns a boolean.
     */
    public boolean contains(T value) {
        if (isEmpty()) {
            return false;
        }
        Node temp = head;
        while (temp != null) {
            if (temp.data == value) {
                return true;
            }
            temp = temp.next;
        }
        return false;

    }

    /** valueAt
     * Returns the data held in the node at a specified index.
     * Throws an illegal argument exception if the index is invalid.
     */
    public T valueAt(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        Node atIndex = traversal(index);
        return (T) atIndex.data;

    }

    /** reverse
     * Reverses the Singly Linked List.
     */
    public void reverse() {
        Node newTail = head;
        Node tempTail = newTail;
        Node tempNext = newTail.next;
        newTail.next = null;

        while (tempTail != tail) {
            Node temp = tempNext.next;
            tempNext.next = tempTail;
            tempTail = tempNext;
            tempNext = temp;
        }
        head = tail;
        tail = newTail;
    }

    /** toString
     * Returns a String representation of the Singly Linked List.
     */
    public String toString() {
        String linkedList = "";
        Node temp = head;
        while (temp != null) {
            linkedList += temp.data.toString() + " -> ";
            temp = temp.next;
        }
        return linkedList + "null";
    }

}
