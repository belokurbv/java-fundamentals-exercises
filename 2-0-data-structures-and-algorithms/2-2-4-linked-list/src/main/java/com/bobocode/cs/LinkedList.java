package com.bobocode.cs;


import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedList<T> implements List<T> {
    Node<T> first;
    Node<T> last;
    int size;

    static class Node<T> {
        T value;
        Node<T> next;

        private Node(T value) {
            this.value = value;
        }

        public static <T> Node<T> valueOf(T value) {
            return new Node<>(value);
        }
    }

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        var list = new LinkedList<T>();
        Arrays.asList(elements).stream().forEach(list::add);
        return list;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        if (first == null) {
            first = last = Node.valueOf(element);
        } else {
            var node = Node.valueOf(element);
            last.next = node;
            this.last = node;
        }
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            var node = this.first;
            this.first = Node.valueOf(element);
            first.next = node;
        } else if (index == size) {
            var node = Node.valueOf(element);
            last.next = node;
            last = node;
        } else {
            var node = getNode(index - 1);
            var dump = node.next;
            node.next = Node.valueOf(element);
            node.next.next = dump;
        }
        size++;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            var node = this.first;
            this.first = Node.valueOf(element);
            first.next = node.next;
        } else if (index == size - 1) {
            var node = Node.valueOf(element);
            last.next = node;
            last = node;
        } else {
            var node = getNode(index - 1);
            var dump = node.next;
            node.next = Node.valueOf(element);
            node.next.next = dump.next;
        }
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        if (first == null && index == 0) {
            throw new IndexOutOfBoundsException();
        }
        return getNode(index).value;
    }

    Node<T> getNode(int index) {
        if (index == 0 && first == null) {
            throw new NoSuchElementException();
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        int i = 0;
        Node<T> node = this.first;
        if (node == null) {
            throw new NoSuchElementException();
        }
        while (node.next != null) {
            if (i == index) {
                return node;
            }
            node = node.next;
            i++;
        }
        return node;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        return getNode(0).value;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return last.value;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        T element;
        if (index == 0 && first != null) {
            element = first.value;
            first = first.next;
        } else {
            var node = getNode(index - 1);
            element = node.next.value;
            node.next = node.next.next;
            if (index == size - 1) {
                last = node;
            }
        }
        size--;
        return element;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        if (first == null) {
            return false;
        }
        var node = first;
        while (node.next != null) {
            if (node.value.equals(element)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    public void reverse() {
        Node<T> prev = null;
        Node<T> current = first;
        Node<T> next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        first = prev;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        if (first != null) {
            var node = first;
            while (node.next != null) {
                sb.append(node.value);
                sb.append(" -> ");
                node = node.next;
            }
            sb.append("null");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        var list = of("1", "2", "3", "4", "5");
        var list2 = new LinkedList<>();
        // list.remove(4);
        list.reverse();
        System.out.println(list);
    }
}
