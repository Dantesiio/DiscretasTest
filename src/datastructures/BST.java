package datastructures;

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
        return delete(null, root, key);
    }

    private T delete(Node<K, T> parent, Node<K, T> current, K goal) {
        if (current == null) {
            return null;
        }
        //Encontramos al nodo
        if (goal.equals(current.getKey())) {
            T result = current.getElement();
            //Es un nodo hoja
            if (current.getRight() == null && current.getLeft() == null) {
                if (parent.getLeft() == current) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            }
            //Eliminar un nodo que tiene hijo derecho
            else if (current.getRight() != null && current.getLeft() == null) {
                if (parent.getLeft() == current) {
                    parent.setLeft(current.getRight());
                } else {
                    parent.setRight(current.getRight());
                }
            }
            //Eliminar un nodo que tiene hijo izquierdo
            else if (current.getRight() == null && current.getLeft() != null) {
                if (parent.getLeft() == current) {
                    parent.setLeft(current.getLeft());
                } else {
                    parent.setRight(current.getLeft());
                }
                return result;
            } else { // clave actual es igual a clave buscada
                Node<K, T> sucessor = getMin(current.getRight());
                //Sobrescribir la key y los valores
                current.setKey(sucessor.getKey());
                current.setElement(sucessor.getElement());
                return delete(current, current.getRight(), sucessor.getKey());
            }
            return result;
        } else if (goal.compareTo(current.getKey()) < 0) {
            return delete(current, current.getLeft(), goal);
        } else { // goal.compareTo(current.getKey()) > 0
            return delete(current, current.getRight(), goal);
        }
    }

    private Node<K,T> getMin(Node<K,T> right) {
        return getMin(root);
    }

    /**
     * Retorna una cadena que representa los elementos de los nodos del árbol en orden ascendente.
     * @return una cadena que representa los elementos de los nodos del árbol en orden ascendente
     */
    @Override
    public String inOrder() {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);
        return sb.toString();
    }
    /**
     * Recorre los nodos del árbol en orden ascendente y agrega su elemento a una cadena.
     * @param current el nodo actual que se está visitando
     * @param sb el objeto StringBuilder que se utiliza para construir la cadena resultante
     */
    private void inOrder(Node<K, T> current, StringBuilder sb) {
        if (current == null) {
            return;
        }
        inOrder(current.getLeft(), sb);
        sb.append(current.getKey()).append(" ");
        inOrder(current.getRight(), sb);
    }

}

