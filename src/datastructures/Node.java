package datastructures;

public class Node <K extends Comparable,T> {
    private T element;
    private K key;
    //Datos

    //Enlaces
    private Node right;
    private Node left;

    public Node(T element, K key) {
        this.element = element;
        this.key = key;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public Node<K,T> getRight() {
        return right;
    }

    public void setRight(Node<K,T> right) {
        this.right = right;
    }

    public Node<K,T> getLeft() {
        return left;
    }

    public void setLeft(Node<K,T> left) {
        this.left = left;
    }

}

