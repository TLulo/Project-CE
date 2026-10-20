package UI;

import Engine.Card.StdCard;
import Engine.SetCard.Deck;
import Engine.SetCard.Hand;
import UI.Card.CardUI;
import UI.SetCard.DeckUI;
import UI.SetCard.HandUI;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
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

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #2b6e3f;");
        root.getChildren().addAll(handView2, handView, deckView);

        StackPane.setAlignment(handView2, Pos.TOP_CENTER);
        StackPane.setAlignment(handView, Pos.BOTTOM_CENTER);
         StackPane.setAlignment(deckView, Pos.CENTER);

        Scene scene = new Scene(root, 1024, 780);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}