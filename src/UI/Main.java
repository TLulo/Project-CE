package UI;
import Engine.Card.StdCard;
import Engine.SetCard.Hand;
import UI.Card.CardUI;
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
        HandUI<StdCard> handView = new HandUI<StdCard>(hand, Pos.BOTTOM_CENTER, true);
        HandUI<StdCard> handView2 = new HandUI<StdCard>(hand, Pos.TOP_CENTER, false);


        StackPane root = new StackPane();

        root.getChildren().add(handView2);
        root.getChildren().add(handView);

        Scene scene = new Scene(root, 1024, 780);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}