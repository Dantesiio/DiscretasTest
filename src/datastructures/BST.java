package datastructures;

import java.util.Locale;

public class BST <K extends Comparable,T>implements IBinarySearchTree<K,T> {
    private Node<K, T> root;

    @Override
    public T getRoot() {
        if (root == null) {
            return null;
        } else {
            return this.root.getElement();
        }
    }

    @Override
    public T search(K goal) {
        return search(root, goal);
    }

    private T search(Node<K, T> current, K goal) {
        if (current == null) {
            System.out.println("Not found");
            return null;
        } else if (current.getKey().equals(goal)) {
            return current.getElement();
        }

        if (goal.compareTo(current.getKey()) < 0) {
            return search(current.getLeft(), goal);
        } else {
            return search(current.getRight(), goal);
        }

    }

    @Override
    public void insert(K key, T element) {
        Node<K, T> newNode = new Node<>(element, key);
        if (root == null) {
            root = newNode;
        } else {
            insert(root, newNode);
        }
    }

    private void insert(Node<K, T> current, Node<K, T> newNode) {
        if (newNode.getKey().compareTo(current.getKey()) < 0) {
            //Meter a la izquierda
            if (current.getLeft() == null) {
                current.setLeft(newNode);
            } else {
                insert(current.getLeft(), newNode);
            }
        } else if (newNode.getKey().compareTo(current.getKey()) > 0) {
            //Meter a la derecha
            if (current.getRight() == null) {
                current.setRight(newNode);
            } else {
                insert(current.getRight(), newNode);
            }

        } else {
            //No hacer nada
        }
    }

    @Override
    public T delete(K key) {
        root = delete(null, root, key);
        return null;
    }

    private Node<K, T> delete(Node<K, T> parent, Node<K, T> current, K goal) {
        if (current == null) {
            return null;
        }
//Encontramos al nodo
        if (goal.equals(current.getKey())) {
//Es un nodo hoja
            if (current.getRight() == null && current.getLeft() == null) {
                if (parent == null) {
                    root = null;
                } else if (parent.getLeft() == current) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            }
//Eliminar un nodo que tiene hijo derecho
            else if (current.getRight() != null && current.getLeft() == null) {
                if (parent == null) {
                    root = current.getRight();
                } else if (parent.getLeft() == current) {
                    parent.setLeft(current.getRight());
                } else {
                    parent.setRight(current.getRight());
                }
            }
//Eliminar un nodo que tiene hijo izquierdo
            else if (current.getRight() == null && current.getLeft() != null) {
                if (parent == null) {
                    root = current.getLeft();
                } else if (parent.getLeft() == current) {
                    parent.setLeft(current.getLeft());
                } else {
                    parent.setRight(current.getLeft());
                }
            } else { // clave actual es igual a clave buscada
                Node<K, T> successor = getMin(current.getRight());
//Sobrescribir la key y los valores
                current.setKey(successor.getKey());
                current.setElement(successor.getElement());
                current.setRight(delete(current, current.getRight(), successor.getKey()));
            }
            return current;
        } else if (goal.compareTo(current.getKey()) < 0) {
            current.setLeft(delete(current, current.getLeft(), goal));
        } else { // goal.compareTo(current.getKey()) > 0
            current.setRight(delete(current, current.getRight(), goal));
        }
        return current;
    }

    private Node<K,T> getMin(Node<K,T> current) {
        if (current.getLeft() == null) {
            return current;
        } else {
            return getMin(current.getLeft());
        }
    }


    /**
     * Retorna una cadena que representa los elementos de los nodos del árbol en orden ascendente.
     * @return una cadena que representa los elementos de los nodos del árbol en orden ascendente
     */
    @Override
    public String inOrder() {
        return inOrder(root).trim();
    }
    public String inOrder(Node<K,T> current){
        if (current==null){
            return "";
        }else {
            return inOrder(current.getLeft())+ " " + current.getKey() + inOrder(current.getRight());
        }

    }
}



