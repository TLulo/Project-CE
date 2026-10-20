package UI;

import Engine.Card.StdCard;
import Engine.SetCard.Deck;
import Engine.SetCard.Hand;
import UI.Card.CardUI;
import UI.SetCard.DeckUI;
import UI.SetCard.HandUI;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Hand<StdCard> hand = new Hand<StdCard>(5);
        for (int i = 1; i <= 3; i++) {
            hand.add(new StdCard(i, i, "test"));
        }

        Deck<StdCard> deck = new Deck<StdCard>();
        for (int i = 1; i <= 10; i++) {
            deck.add(new StdCard(i, i, "test"));
        }
        
        HandUI<StdCard> handView = new HandUI<StdCard>(hand, true);
        HandUI<StdCard> handView2 = new HandUI<StdCard>(hand, false);

        DeckUI<StdCard> deckView = new DeckUI<>(() -> deck, false, "Mazo");
        // DeckUI<StdCard> deckView = new DeckUI<>(() -> deck, false, "Mazo", CardUI.DEFAULT_WIDTH);

        Button change = new Button("Vaciar");
        change.setOnAction(e -> CardUI.setImageFolder("test"));
 
        Button defaultImage = new Button("Default");
        defaultImage.setOnAction(e -> CardUI.setImageFolder("images/cards/"));

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #2b6e3f;");
        root.getChildren().addAll(handView2, handView, deckView, change, defaultImage);

        StackPane.setAlignment(handView2, Pos.TOP_CENTER);
        StackPane.setAlignment(handView, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(deckView, Pos.CENTER);
        
        VBox buttons = new VBox(10); // 10 px de separación
        buttons.setAlignment(Pos.CENTER_RIGHT);
        buttons.setPadding(new Insets(0, 20, 0, 0));

        buttons.getChildren().addAll(change, defaultImage);

        StackPane.setAlignment(buttons, Pos.CENTER_RIGHT);
        root.getChildren().add(buttons);

        Scene scene = new Scene(root, 1024, 780);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}