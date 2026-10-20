package UI;

import Engine.Card.StdCard;
import Engine.Engine.Engine;
import Engine.Player.StdPlayer;
import Engine.SetCard.Deck;
import Engine.Table.StdTable;
import UI.Card.CardUI;
import UI.ScaledPane.ScaledPane;
import UI.Table.StdTableUI;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // 1. Mesa y engine
        Deck<StdCard> deck = new Deck<StdCard>();
        String[] suits = {"clubs", "diamonds", "hearts", "spades"};
        int id = 1;
        for (String suit : suits) {
            for (int value = 2; value <= 14; value++) {
                deck.add(new StdCard(id++, value, suit));
            }
        }

        StdTable table = new StdTable(deck.getAllCards());
        table.getDrawDeck().shuffle();
        for (int i = 1; i <= 3; i++) {
            StdPlayer<StdCard> player = new StdPlayer<>(5);
            player.setName("Player " + i);
            table.addPlayer(player);
        }

        Engine<StdPlayer<StdCard>, StdCard, StdTable> engine = new Engine<>(table);
        engine.setGame(2);

        // 2. La pantalla de la mesa
        StdTableUI tableUI = new StdTableUI(engine::getTable, engine::getCurrentPlayer);
        bindEndTurn(tableUI, engine);

        // 3. Envolverla para que escale
        ScaledPane scaled = new ScaledPane(tableUI);
        scaled.setStyle("-fx-background-color: #1e5b3a;");

        // 4. Botón de configuración (fuera del escalado, siempre del mismo tamaño)
        Button config = new Button("Config");
        config.setOnAction(e -> askImageFolder(tableUI, engine));
        StackPane.setAlignment(config, Pos.TOP_RIGHT);
        StackPane.setMargin(config, new Insets(10));

        StackPane root = new StackPane(scaled, config);
        stage.setScene(new Scene(root, 1024, 780));
        stage.show();
    }

    // Pide una ruta relativa para las imágenes de las cartas y redibuja la mesa
    private void askImageFolder(StdTableUI tableUI,
                                Engine<StdPlayer<StdCard>, StdCard, StdTable> engine) {
        TextInputDialog dialog = new TextInputDialog(CardUI.getImageFolder());
        dialog.setTitle("Configuración");
        dialog.setHeaderText("Carpeta de imágenes de las cartas");
        dialog.setContentText("Ruta relativa (vacío = por defecto):");

        dialog.showAndWait().ifPresent(path -> {
            CardUI.setImageFolder(path);
            tableUI.refresh();
            bindEndTurn(tableUI, engine);
        });
    }

    // refresh() recrea el botón "End turn", así que el handler se vuelve a asignar
    private void bindEndTurn(StdTableUI tableUI,
                             Engine<StdPlayer<StdCard>, StdCard, StdTable> engine) {
        tableUI.getEndTurnButton().setOnAction(e -> {
            engine.nextTurn();
            tableUI.refresh();
            bindEndTurn(tableUI, engine);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}