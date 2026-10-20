package UI.Table;

import java.util.function.Supplier;

import Engine.Card.StdCard;
import Engine.Player.StdPlayer;
import Engine.SetCard.Hand;
import Engine.Table.StdTable;
import UI.Card.CardUI;
import UI.SetCard.DeckUI;
import UI.SetCard.HandUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Full table view for a StdTable driven by an Engine.
 * Hot-seat style: the current player's hand is face up at the bottom,
 * the other players' hands are face down at the top.
 *
 * This class builds the table's presentation. Main supplies interaction behavior.
 */
public class StdTableUI extends BorderPane {
    private static final double MAIN_CARD_WIDTH = 110;
    private static final double OPPONENT_CARD_WIDTH = 70;

    private final Supplier<StdTable> tableSource;
    private final Supplier<StdPlayer<StdCard>> currentPlayerSource;

    private final VBox opponents = new VBox(10);
    private final HBox center = new HBox(50);
    private final VBox bottom = new VBox(10);
    private final Label status = new Label();
    private DeckUI<StdCard> drawPile;
    private DeckUI<StdCard> discardPile;
    private HandUI<StdCard> currentHand;
    private HBox currentField;
    private Button endTurn;

    // Ej.: new StdTableUI(engine::getTable, engine::getCurrentPlayer)
    public StdTableUI(Supplier<StdTable> tableSource,
                      Supplier<StdPlayer<StdCard>> currentPlayerSource) {
        this.tableSource = tableSource;
        this.currentPlayerSource = currentPlayerSource;

        setStyle("-fx-background-color: #1e5b3a;");
        setPadding(new Insets(16));

        opponents.setAlignment(Pos.CENTER);
        center.setAlignment(Pos.CENTER);
        bottom.setAlignment(Pos.CENTER);
        status.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

        setTop(opponents);
        setCenter(center);
        setBottom(bottom);

        refresh();
    }

    public void refresh() {
        StdTable table = tableSource.get();
        StdPlayer<StdCard> current = currentPlayerSource.get();

        opponents.getChildren().clear();
        center.getChildren().clear();
        bottom.getChildren().clear();

        if (current == null) {
            showEmptyTable();
            return;
        }

        buildOpponents(table, current);
        buildCenter(table);
        buildBottom(current);
    }

    private void showEmptyTable() {
        status.setText("No players at the table");
        bottom.getChildren().add(status);
    }

    // Other players: hand face down, field face up
    private void buildOpponents(StdTable table, StdPlayer<StdCard> current) {
        for (StdPlayer<StdCard> player : table.getPlayers()) {
            if (player == current) {
                continue;
            }
            Label name = new Label(nameOf(player) + " (" + player.getHandAmount() + " cards)");
            name.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
            HandUI<StdCard> hand = new HandUI<StdCard>(
                    () -> handOf(player), false, OPPONENT_CARD_WIDTH);
            Label fieldTitle = new Label(nameOf(player) + "'s field");
            fieldTitle.setStyle("-fx-text-fill: white;");
            HBox field = buildField(player, OPPONENT_CARD_WIDTH);
            opponents.getChildren().addAll(name, hand, fieldTitle, field);
        }
    }

    // Center: draw pile and discard pile
    private void buildCenter(StdTable table) {
        drawPile = new DeckUI<StdCard>(
                () -> table.getDrawDeck(), false, "Draw", MAIN_CARD_WIDTH, true);
        discardPile = new DeckUI<StdCard>(
                () -> table.getDiscardDeck(), true, "Discard", MAIN_CARD_WIDTH, true);
        center.getChildren().addAll(drawPile, discardPile);
    }

    // Bottom: current player's field and hand (face up), status and end-turn button
    private void buildBottom(StdPlayer<StdCard> current) {
        Label name = new Label(nameOf(current));
        name.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;");

        Label fieldTitle = new Label(nameOf(current) + "'s field");
        fieldTitle.setStyle("-fx-text-fill: white;");
        currentField = buildField(current, MAIN_CARD_WIDTH);
        currentHand = new HandUI<StdCard>(
                () -> handOf(current), true, MAIN_CARD_WIDTH);

        endTurn = new Button("End turn");

        bottom.getChildren().addAll(name, fieldTitle, currentField, currentHand, status, endTurn);
    }

    public DeckUI<StdCard> getDrawPile() {
        return drawPile;
    }

    public DeckUI<StdCard> getDiscardPile() {
        return discardPile;
    }

    public HandUI<StdCard> getCurrentHand() {
        return currentHand;
    }

    public HBox getCurrentField() {
        return currentField;
    }

    public Button getEndTurnButton() {
        return endTurn;
    }

    public Label getStatusLabel() {
        return status;
    }

    // HandUI expects a Hand; the player only exposes its cards as a List
    private Hand<StdCard> handOf(StdPlayer<StdCard> player) {
        Hand<StdCard> hand = new Hand<StdCard>(player.getHandCapacity());
        hand.setHand(player.getPlayerHand());
        return hand;
    }

    // A player's table cards (a Board, exposed as List by StdPlayer), drawn face up
    private HBox buildField(StdPlayer<StdCard> player, double cardWidth) {
        HBox field = new HBox(10);
        field.setAlignment(Pos.CENTER);
        field.setMinHeight(cardWidth * 1.4);
        for (StdCard card : player.getPlayerTable()) {
            field.getChildren().add(new CardUI<StdCard>(card, true, cardWidth));
        }
        return field;
    }

    private String nameOf(StdPlayer<StdCard> player) {
        return player.getName() != null ? player.getName() : "Player " + player.getId();
    }
}