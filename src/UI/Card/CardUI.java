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
    private final int width = 128;//1280*0.1

    public CardUI(C card, boolean visible){
        setPrefSize(width, width*1.4);
        setMaxSize(width, width*1.4);
        base = new Rectangle(width, width*1.4);
        base.setFill(Color.LIGHTBLUE);

        cardId = new Label(String.valueOf(card.getId()));
        setAlignment(cardId,Pos.TOP_LEFT);

        base.setArcHeight(15);
        base.setArcWidth(15);

        if (visible) {
            String path = IMAGE_FOLDER + card.getId() + ".png";
            cardId.setVisible(true);
            setImage(path);
        }else{
            String path = IMAGE_FOLDER + "back.png";
            cardId.setVisible(false);
            setImage(path);
        }
        
        getChildren().addAll(base, cardimage,cardId);
    }

    private void setImage(String path){
        cardimage = new ImageView();
        try {
            Image img = new Image(new FileInputStream(path));
            cardimage.setImage(img);
            cardimage.setFitWidth(width);
            cardimage.setFitHeight(width*1.4);
            cardimage.setVisible(true);
            base.setVisible(false);
            cardId.setVisible(false);
        } catch (Exception e) {
            System.err.println("Image not found: " + path);
            cardimage.setImage(null);
            cardimage.setVisible(false);
            base.setVisible(true);
        }
    }
}
