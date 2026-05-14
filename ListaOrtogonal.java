package com.example.numbermatch;

public class ListaOrtogonal {

    private Node inicio;

    private int anchoMaximo;



    public ListaOrtogonal(int ancho) {
        this.inicio = null;
        this.anchoMaximo = ancho;
    }

    public void agregarNumero(int valor) {

        Node nuevo = new Node(valor);
        if (inicio == null) {
            inicio = nuevo;
            return;

        }
        Node actual = inicio;
        while (actual.getDown() != null) {
            actual = actual.getDown();
        }
        int cuentaHorizontal = 1;
        while (actual.getRight() != null) {

            actual = actual.getRight();

            cuentaHorizontal++;

        }
// 3. Conectar el nuevo nodo

        if (cuentaHorizontal < anchoMaximo) {
            actual.setRight(nuevo);
            nuevo.setLeft(actual);
            if (actual.getUp() != null) {
                nuevo.setUpLeft(actual.getUp());

                actual.getUp().setDownRight(nuevo);

                if (actual.getUp().getRight() != null) {

                    Node arriba = actual.getUp().getRight();

                    nuevo.setUp(arriba);

                    arriba.setDown(nuevo);
                    if (arriba.getRight() != null) {

                        nuevo.setUpRight(arriba.getRight());

                        arriba.getRight().setDownLeft(nuevo);

                    }

                }

            }

        } else {

            Node ultimoFilaAnterior = actual;

            Node primeroFilaActual = actual;

            // Retroceder SOLO una fila
            for (int i = 1; i < anchoMaximo; i++) {

                if (primeroFilaActual.getLeft() != null) {
                    primeroFilaActual = primeroFilaActual.getLeft();
                }
            }

            // Enlace vertical
            primeroFilaActual.setDown(nuevo);
            nuevo.setUp(primeroFilaActual);
            ultimoFilaAnterior.setRight(nuevo);
            nuevo.setLeft(ultimoFilaAnterior);
            ultimoFilaAnterior.setConexionSerpienteDerecha(true);
            nuevo.setConexionSerpienteIzquierda(true);
            // Diagonal
            if (primeroFilaActual.getRight() != null) {

                nuevo.setUpRight(primeroFilaActual.getRight());
                primeroFilaActual.getRight().setDownLeft(nuevo);
            }
        }

    }

    public void mostrarTablero() {

        if (inicio == null) {

            System.out.println("Lista vacia");

            return;

        }
        Node filaR = inicio;

        while (filaR != null) {

            Node colR = filaR;

            while (colR != null) {
                int num = colR.getNumber();

                if (num == 0) System.out.print(". ");

                else System.out.print(num + " ");



                colR = colR.getRight();

            }

            System.out.println();

            filaR = filaR.getDown();

        }

    }

    public Node getInicio() {

        return inicio;

    }

}
