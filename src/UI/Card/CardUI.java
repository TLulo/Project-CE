package UI.Card;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

import Engine.Card.Card;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CardUI<C extends Card> extends StackPane {
    private static final String IMAGE_FOLDER = "images/cards/";

    private final Label cardId;
    private final Rectangle base;
    private ImageView cardimage = null;
    private final int width = 100;
    private final int height = 140;

    public CardUI(C card){
        setPrefSize(width, height);
        setMaxSize(width, height);
        base = new Rectangle(width, height);
        base.setFill(Color.LIGHTBLUE);

        cardId = new Label(String.valueOf(card.getId()));
        setAlignment(cardId,Pos.TOP_LEFT);

        base.setArcHeight(15);
        base.setArcWidth(15);

        setImageById(card.getId());
        
        getChildren().addAll(base, cardimage,cardId);
    }

    private void setImageById(int id){
        String path = IMAGE_FOLDER + id + ".png";
        cardimage = new ImageView();
        try {
            Image img = new Image(new FileInputStream(path));
            cardimage.setImage(img);
            cardimage.setFitWidth(width);
            cardimage.setFitHeight(height);
            cardimage.setVisible(true);
            base.setVisible(false);
            cardId.setVisible(false);
        } catch (Exception e) {
            System.err.println("Image not found: " + path);
            cardimage.setImage(null);
            cardimage.setVisible(false);
            base.setVisible(true);
            cardId.setVisible(true);
        }
    }
}
