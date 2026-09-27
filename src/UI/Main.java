package UI;
import Engine.Card.StdCard;
import UI.Card.CardUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        StdCard card = new StdCard(1, 15, "Trebol");

        CardUI<StdCard> cardView = new CardUI<StdCard>(card);

        StackPane root = new StackPane(cardView);

        Scene scene = new Scene(root, 1024, 780);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}