package com.example.numbermatch;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Interfaz extends Application {
    private Juego juego;
    private GridPane gridTablero;
    private Label lblMatches, lblPendientes;
    private Node nodoSeleccionado = null;
    private int filas, columnas;
    private Node[][] matrizVisual;

    @Override
    public void start(Stage primaryStage) {
        mostrarDialogoConfiguracion(primaryStage);
    }

    private void mostrarDialogoConfiguracion(Stage stage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titulo = new Label("Configuración Number Match");
        TextField txtFilas = new TextField("4");
        TextField txtCols = new TextField("10");
        Button btnIniciar = new Button("Iniciar Juego");

        root.getChildren().addAll(titulo, new Label("Filas (min 4):"), txtFilas,
                new Label("Columnas (min 10):"), txtCols, btnIniciar);

        btnIniciar.setOnAction(e -> {
            try {
                int f = Math.max(4, Integer.parseInt(txtFilas.getText()));
                int c = Math.max(10, Integer.parseInt(txtCols.getText()));
                iniciarJuego(stage, f, c);
            } catch (NumberFormatException ex) {
                mostrarAlerta("Error", "Por favor ingresa números válidos.");
            }
        });

        stage.setScene(new Scene(root, 300, 300));
        stage.setTitle("Configurar Tablero");
        stage.show();
    }

    private void iniciarJuego(Stage stage, int f, int c) {
        this.filas = f;
        this.columnas = c;

        juego = new Juego(f, c);
        
        matrizVisual = new Node[filas][columnas];
        Node filaR = juego.getTablero().getInicio();
        for (int r = 0; r < filas && filaR != null; r++) {
            Node colR = filaR;
            for (int rowC = 0; rowC < columnas && colR != null; rowC++) {
                matrizVisual[r][rowC] = colR;
                colR = colR.getRight();
            }
            filaR = filaR.getDown();
        }


        BorderPane layoutPrincipal = new BorderPane();
        layoutPrincipal.setPadding(new Insets(10));

        HBox panelInfo = new HBox(20);
        lblMatches = new Label("Matches: 0");
        lblPendientes = new Label("Pendientes: " + juego.contarPendientes());
        panelInfo.getChildren().addAll(lblMatches, lblPendientes);
        layoutPrincipal.setTop(panelInfo);

        gridTablero = new GridPane();
        gridTablero.setHgap(5);
        gridTablero.setVgap(5);
        gridTablero.setAlignment(Pos.CENTER);
        actualizarVistaTablero();
        layoutPrincipal.setCenter(gridTablero);

        HBox panelBotones = new HBox(10);
        panelBotones.setPadding(new Insets(10, 0, 0, 0));
        Button btnPista = new Button("Pista");
        Button btnDeshacer = new Button("Deshacer");

        btnPista.setOnAction(e -> {

            Node[] pista = juego.obtenerPistaNodos();

            if (pista != null) {
                actualizarVistaTablero();
                mostrarPistaVisual(pista[0], pista[1]);
            } else {
                mostrarAlerta("Pista", "No hay movimientos disponibles.");
            }
        });

        btnDeshacer.setOnAction(e -> {
            juego.deshacerMovimiento();
            actualizarVistaTablero();
        });

        panelBotones.getChildren().addAll(btnPista, btnDeshacer);
        layoutPrincipal.setBottom(panelBotones);

        stage.setScene(new Scene(layoutPrincipal, 900, 700));
        stage.setTitle("Number Match");
    }

    private void actualizarVistaTablero() {
        gridTablero.getChildren().clear();

        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < columnas; c++) {
                Node n = matrizVisual[r][c]; // Usamos la posición original fija

                // Si el nodo está marcado como esVacio, crearBotonNodo se encargará
                // de dibujarlo como un hueco transparente en lugar de saltárselo.
                Button btnNodo = crearBotonNodo(n);
                gridTablero.add(btnNodo, c, r);
            }
        }

        lblMatches.setText("Matches: " + juego.getMatchesEncontrados());
        lblPendientes.setText("Pendientes: " + juego.contarPendientes());
    }

    private Button crearBotonNodo(Node n) {
        // Si el nodo es nulo o está vacío, dibujamos un espacio en blanco
        if (n == null || n.isVacio()) {
            Button btnVacio = new Button(""); 
            btnVacio.setPrefSize(45, 45);
            // Hacemos que el fondo sea igual al del tablero para que parezca un hueco
            btnVacio.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
            btnVacio.setDisable(true); // No se puede interactuar
            return btnVacio;
        }

        // Si el nodo NO está vacío, dibujamos el botón normal
        Button btn = new Button(String.valueOf(n.getNumber()));
        btn.setUserData(n);
        btn.setPrefSize(45, 45);
        btn.setStyle("-fx-background-color: #ffffff; -fx-border-color: #dddddd; -fx-text-fill: #000000; -fx-font-weight: bold;");

        btn.setOnAction(e -> {
            if (nodoSeleccionado == null) {
                nodoSeleccionado = n;
                btn.setStyle("-fx-background-color: #add8e6; -fx-border-color: #333333;");
            } else {
                if (juego.intentarMatch(nodoSeleccionado, n)) {
                    actualizarVistaTablero();
                    if (juego.haTerminado()) mostrarAlerta("Fin", "¡Felicidades!");
                } else {
                    actualizarVistaTablero();
                }
                nodoSeleccionado = null;
            }
        });

        return btn;
    }
    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private void mostrarPistaVisual(Node n1, Node n2) {

        for (javafx.scene.Node componente : gridTablero.getChildren()) {

            if (componente instanceof Button) {

                Button btn = (Button) componente;

                Object data = btn.getUserData();

                if (data == n1 || data == n2) {

                    btn.setStyle(
                            "-fx-background-color: yellow;" +
                                    "-fx-border-color: black;" +
                                    "-fx-font-weight: bold;"
                    );
                }
            }
        }
    }

}
