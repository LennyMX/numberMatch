package com.example.numbermatch;

import java.util.Random;

public class Juego {
    private ListaOrtogonal tablero;
    private Pila<Movimiento> historial;
    private int matchesEncontrados;
    private int filas, columnas;

    public Juego(int filas, int columnas) {

        this.filas = filas;
        this.columnas = columnas;

        this.tablero = new ListaOrtogonal(columnas);

        Random rnd = new Random();

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < columnas; j++) {

                tablero.agregarNumero(rnd.nextInt(9) + 1);
            }
        }

        this.historial = new Pila<>(filas * columnas);

        this.matchesEncontrados = 0;
    }

//metodo para revisar si se pueden matchear 
    public boolean intentarMatch(Node n1, Node n2) {
        if (n1 == null || n2 == null || n1 == n2) return false;
        if (n1.isNeighbor(n2) && n1.compareTo(n2) == 0) {
            // Guardar el movimiento en la pila antes de borrar
            Movimiento mov = new Movimiento(n1, n2);
            historial.push(mov);

            // Aplicar el borrado 
            n1.delete();
            n2.delete();

            // 3. Actualizar contador
            matchesEncontrados++;
            return true;
        }
        return false;
    }

    public void deshacerMovimiento() {
        if (!historial.pilaVacia()) {
            Movimiento mov = historial.pop();
            mov.deshacer();
            matchesEncontrados--;
        }
    }
    public Node[] obtenerPistaNodos() {
        Node filaActual = tablero.getInicio();

        while (filaActual != null) {

            Node actual = filaActual;

            while (actual != null) {

                if (!actual.isVacio()) {

                    for (Node vecino : actual.getNeighbors()) {

                        if (!vecino.isVacio() &&
                                actual.compareTo(vecino) == 0) {

                            return new Node[]{actual, vecino};
                        }
                    }
                }

                actual = actual.getRight();
            }

            filaActual = filaActual.getDown();
        }

        return null;
    }
    public int contarPendientes() {
        int contador = 0;
        Node filaActual = tablero.getInicio();

        while (filaActual != null) {
            Node actual = filaActual;
            while (actual != null) {
                if (!actual.isVacio()) {
                    for (Node vecino : actual.getNeighbors()) {
                        if (actual.compareTo(vecino) == 0) {
                            contador++;
                        }
                    }
                }
                actual = actual.getRight();
            }
            filaActual = filaActual.getDown();
        }
        return contador / 2;
    }

    public boolean haTerminado() {
        return contarPendientes() == 0;
    }
    public int getMatchesEncontrados() {
        return matchesEncontrados;
    }
    public ListaOrtogonal getTablero() {
        return tablero;
    }
}
