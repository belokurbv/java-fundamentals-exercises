package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    Node<T> root;
    int size = 0;

    @ToString
    static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        public Node(T value) {
            this.value = value;
        }

        public static <T>  Node<T> of(T element) {
            return new Node<>(element);
        }
    }

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        var tree = new RecursiveBinarySearchTree<T>();
        Arrays.stream(elements).forEach(tree::insert);
        return tree;
    }

    @Override
    public boolean insert(T element) {
        if (root == null) {
            this.root = Node.of(element);
            size++;
            return true;
        }
        return insertElement(root, element);
    }

    boolean insertElement(Node<T> node, T element) {
        if (element.compareTo(node.value) > 0) {
            if (node.right == null) {
                node.right = Node.of(element);
                size++;
                return true;
            } else {
                return insertElement(node.right, element);
            }
        } else if (element.compareTo(node.value) < 0) {
            if (node.left == null) {
                node.left = Node.of(element);
                size++;
                return true;
            } else {
                return insertElement(node.left, element);
            }
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        Objects.requireNonNull(element);
        return findNode(root, element) != null;
    }

    Node<T> findNode(Node<T> node, T element) {
        if (node == null) {
            return null;
        } else if (element.compareTo(node.value) > 0) {
            return findNode(node.right, element);
        } else if (element.compareTo(node.value) < 0) {
            return findNode(node.left, element);
        }
        return node;
    }

    @Override
    public int size() {
       return size;
    }

    @Override
    public int depth() {
        return root != null ? depth(root) - 1: 0;
    }

    int depth(Node<T> node) {
        if (node != null) {
            return 1 + Math.max(depth(node.left), depth(node.right));
        }
        return 0;
    }

    void inOrderTraversal(Node<T> node, Consumer<T> consumer) {
        if (node != null) {
            inOrderTraversal(node.left, consumer);
            consumer.accept(node.value);
            inOrderTraversal(node.right, consumer);
        }
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    public static void main(String[] args) {
        var tree = of(1, 2, 3);
        System.out.println(tree.root);
        tree.inOrderTraversal(System.out::println);
    }
}
