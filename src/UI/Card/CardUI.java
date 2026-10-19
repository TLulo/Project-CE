package UI.Card;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import Engine.Card.Card;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CardUI<C extends Card> extends StackPane {
    private static final String IMAGE_FOLDER = "images/cards/";
    private static final Map<String, Image> imageCache = new HashMap<>();

    public static final double DEFAULT_WIDTH = 110;
    private static final double RATIO = 1.4;

    private final C card;
    private final double width;
    private final double height;

    public CardUI(C card, boolean faceUp) {
        this(card, faceUp, DEFAULT_WIDTH);
    }

    public CardUI(C card, boolean faceUp, double width) {
        this.card = card;
        this.width = width;
        this.height = width * RATIO;

        setPrefSize(width, height);
        setMinSize(width, height);
        setMaxSize(width, height);
        setAlignment(Pos.CENTER);

        String path = IMAGE_FOLDER + (faceUp ? card.getId() + ".png" : "back.png");
        Image img = loadImage(path);
        if (img != null) {
            getChildren().add(buildImageView(img));
        } else {
            getChildren().add(buildFallback(faceUp));
        }
    }

    public C getCard() {
        return card;
    }

    public void setSelected(boolean selected) {
        setTranslateY(selected ? -14 : 0);
        setEffect(selected ? new DropShadow(18, Color.GOLD) : null);
    }

    private Node buildImageView(Image img) {
        ImageView view = new ImageView(img);
        view.setFitWidth(width);
        view.setFitHeight(height);
        view.setPreserveRatio(false);

        Rectangle clip = new Rectangle(width, height);
        clip.setArcWidth(15);
        clip.setArcHeight(15);
        view.setClip(clip);
        return view;
    }

    // Used when the image file does not exist
    private Node buildFallback(boolean faceUp) {
        StackPane pane = new StackPane();

        Rectangle base = new Rectangle(width, height);
        base.setArcWidth(15);
        base.setArcHeight(15);
        base.setFill(faceUp ? Color.LIGHTBLUE : Color.web("#3b4a8a"));
        base.setStroke(Color.BLACK);
        base.setStrokeWidth(1.5);
        pane.getChildren().add(base);

        if (faceUp) {
            Label id = new Label(String.valueOf(card.getId()));
            id.setStyle("-fx-font-size: " + (width * 0.3) + "px; -fx-font-weight: bold;");
            pane.getChildren().add(id);
        } else {
            Rectangle inner = new Rectangle(width - 16, height - 16);
            inner.setArcWidth(10);
            inner.setArcHeight(10);
            inner.setFill(Color.TRANSPARENT);
            inner.setStroke(Color.WHITE);
            pane.getChildren().add(inner);
        }
        return pane;
    }

    private static Image loadImage(String path) {
        if (imageCache.containsKey(path)) {
            return imageCache.get(path);
        }
        Image result = null;
        try (FileInputStream in = new FileInputStream(path)) {
            Image img = new Image(in);
            result = img.isError() ? null : img;
        } catch (Exception e) {
            System.err.println("Warning (CardUI): image not found: " + path);
        }
        imageCache.put(path, result);
        return result;
    }
}
