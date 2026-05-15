package com.example.numbermatch;
import java.util.ArrayList;


/**
 * Clase Node
 * Models a node in an 8 way linked list
 * @author Cecilia M. Curlango Rosas
 * @version 01 2026
 */
public class Node implements Comparable<Node> {
    private boolean esVacio;
    private int number; // INFO part
    private Node up, down,
            left, right,
            downLeft, downRight,
            upLeft, upRight;

    private boolean conexionSerpienteDerecha;
    private boolean conexionSerpienteIzquierda;


    public Node(int number) {
        this.number = number;
        up = null;
        down = null;
        left = null;
        right = null;
        downLeft = null;
        downRight = null;
        upLeft = null;
        upRight = null;
    }

    @Override
    public int compareTo(Node otro) {
        if (otro == null || this.esVacio || otro.esVacio) return -1;

        if (this.number == otro.number || (this.number + otro.number == 10)) {
            return 0;
        }
        return -1;
    }


    /**
     * Updates all links
     * to neighboring nodes so that
     * nothing points to it anymore.
     */
    public void delete() {
        this.esVacio = true;

        // Conexión Horizontal: Une Izquierda con Derecha
        if (left != null) left.setRight(right);
        if (right != null) right.setLeft(left);

        // Conexión Vertical: Une Arriba con Abajo
        if (up != null) up.setDown(down);
        if (down != null) down.setUp(up);

        // Conexiones Diagonales (Ejemplo: upLeft con downRight)
        if (upLeft != null) upLeft.setDownRight(downRight);
        if (downRight != null) downRight.setUpLeft(upLeft);

        if (upRight != null) upRight.setDownLeft(downLeft);
        if (downLeft != null) downLeft.setUpRight(upRight);
    }
    /**
     * Returns whether input node is next to
     * node.
     * @return true if nodes are next to each other
     */
    public boolean isNeighbor(Node input) {
        if (input == null || this.esVacio || input.isVacio()) return false;

        // Comprobamos si el nodo de entrada está en cualquiera de nuestras 8 direcciones
        return (input == up || input == down ||
                input == left || input == right ||
                input == upLeft || input == upRight ||
                input == downLeft || input == downRight);
    }

    /**
     * Returns whether input node contains same value or
     * adds up to 10.
     * @return true if nodes contain the same value or add up to 10
     */
    public boolean isMatchValue(Node input) {
        if (input == null || this.esVacio || input.isVacio()) return false;
        int v1 = this.number;
        int v2 = input.getNumber();
        return (v1 == v2 || (v1 + v2 == 10));
    }

    public void restore() {
        this.esVacio = false;

        // Reconectar con los vecinos que el nodo aún tiene guardados en sus punteros
        if (up != null) up.setDown(this);
        if (down != null) down.setUp(this);
        if (left != null) left.setRight(this);
        if (right != null) right.setLeft(this);

        if (upLeft != null) upLeft.setDownRight(this);
        if (upRight != null) upRight.setDownLeft(this);
        if (downLeft != null) downLeft.setUpRight(this);
        if (downRight != null) downRight.setDownLeft(this);
    }


    public Node getDown() {
        return down;
    }


    public void setDown(Node down) {
        this.down = down;
    }


    public Node getLeft() {
        return left;
    }


    public void setLeft(Node left) {
        this.left = left;
    }


    public Node getRight() {
        return right;
    }


    public void setRight(Node right) {
        this.right = right;
    }


    public Node getDownLeft() {
        return downLeft;
    }


    public void setDownLeft(Node downLeft) {
        this.downLeft = downLeft;
    }


    public Node getDownRight() {
        return downRight;
    }


    public void setDownRight(Node downRight) {
        this.downRight = downRight;
    }


    public Node getUpLeft() {
        return upLeft;
    }


    public void setUpLeft(Node upLeft) {
        this.upLeft = upLeft;
    }


    public Node getUpRight() {
        return upRight;
    }


    public void setUpRight(Node upRight) {
        this.upRight = upRight;
    }


    public Node getUp() {
        return up;
    }


    public void setUp(Node up) {
        this.up = up;
    }


    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }


    @Override
    public String toString() {
        return esVacio ? "[ ]" : String.valueOf(number);
    }


    /**
     * Returns an ArrayList containing all non-empty neighboring nodes
     * @return ArrayList<Node> with neighboring non-empty nodes
     */


    public ArrayList<Node> getNeighbors() {
        ArrayList<Node> neighbors = new ArrayList<>();
        Node[] posibles = {up, down, left, right, upLeft, upRight, downLeft, downRight};
        for (Node n : posibles) {
            if (n != null && !n.isVacio()) {
                neighbors.add(n);
            }
        }
        return neighbors;
    }
    public boolean isVacio() {
        return esVacio;
    }

    public void setVacio(boolean esVacio) {
        this.esVacio = esVacio;
    }
    public boolean isConexionSerpienteDerecha() {
        return conexionSerpienteDerecha;
    }

    public void setConexionSerpienteDerecha(boolean conexionSerpienteDerecha) {
        this.conexionSerpienteDerecha = conexionSerpienteDerecha;
    }

    public boolean isConexionSerpienteIzquierda() {
        return conexionSerpienteIzquierda;
    }

    public void setConexionSerpienteIzquierda(boolean conexionSerpienteIzquierda) {
        this.conexionSerpienteIzquierda = conexionSerpienteIzquierda;
    }

}

