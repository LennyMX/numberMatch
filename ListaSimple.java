package com.example.numbermatch;

public class ListaSimple {
    private Node inicio;
    private int tamaño;

    public ListaSimple() {
        this.inicio = null;
        this.tamaño = 0;
    }

    public void insertarInicio(int dato) {
        Node nuevo = new Node(dato);
        nuevo.setRight(inicio);
        inicio = nuevo;
        tamaño++;
    }

    public void insertarFin(int dato) {
        Node nuevo = new Node(dato);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            Node r = inicio;
            while (r.getRight() != null) {
                r = r.getRight();
            }
            r.setRight(nuevo);
        }
        tamaño++;
    }

    public Node eliminarInicio() {
        if (inicio == null) {
            System.out.println("Lista vacia");
            return null;
        }
        Node aux = inicio;
        inicio = inicio.getRight();
        aux.setRight(null); 
        tamaño--;
        return aux;
    }

    public Node obtener(int indice) {
        if (indice < 0 || indice >= tamaño) {
            return null;
        }
        Node aux = inicio;
        for (int i = 0; i < indice && aux != null; i++) {
            aux = aux.getRight();
        }
        return aux;
    }

    public int getTamaño() {
        return tamaño;
    }
}

