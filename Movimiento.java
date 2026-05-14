package com.example.numbermatch;
public class Movimiento {
    private Node nodo1;
    private Node nodo2;

    public Movimiento(Node n1, Node n2) {
        this.nodo1 = n1;
        this.nodo2 = n2;
    }

    public void deshacer() {
        if (nodo1 != null && nodo2 != null) {
            nodo1.restore();
            nodo2.restore();
        }
    }

    public Node getNodo1() {
        return nodo1;
    }

    public Node getNodo2() {
        return nodo2;
    }
}

