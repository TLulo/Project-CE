package blackjack.UI;

import blackjack.src.Engine.BlackjackEngine;
import blackjack.src.Player.BlackjackPlayer;
import blackjack.src.Table.BlackjackTable;
import Engine.Card.StdCard;
import UI.SetCard.DeckUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Mesa completa de Blackjack manejada por un BlackjackEngine.
 * Arriba la banca, en el centro el mazo de robo, abajo los jugadores,
 * el estado y los botones. Main solo conecta las acciones de los botones.
 */
public class BlackjackTableUI extends BorderPane {
    private static final double CARD_WIDTH = 90;

    private final BlackjackEngine engine;

    private final BlackjackPlayerUI dealerView;
    private final DeckUI<StdCard> drawPile;
    private final HBox playersBox = new HBox(40);
    private final Label status = new Label();
    private final Button hit = new Button("Hit");
    private final Button stand = new Button("Stand");
    private final Button newRound = new Button("New round");

    // Ej.: new BlackjackTableUI(engine)
    public BlackjackTableUI(BlackjackEngine engine) {
        this.engine = engine;
        BlackjackTable table = engine.getTable();

        setStyle("-fx-background-color: #1e5b3a;");
        setPadding(new Insets(16));

        dealerView = new BlackjackPlayerUI(() -> table.getDealer(), CARD_WIDTH);
        drawPile = new DeckUI<StdCard>(
                () -> table.getDrawDeck(), false, "Draw", CARD_WIDTH, true);

        playersBox.setAlignment(Pos.CENTER);
        status.setStyle("-fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold;");

        HBox buttons = new HBox(10, hit, stand, newRound);
        buttons.setAlignment(Pos.CENTER);
        VBox bottom = new VBox(14, playersBox, status, buttons);
        bottom.setAlignment(Pos.CENTER);

        setTop(dealerView);
        setCenter(drawPile);
        setBottom(bottom);
        BorderPane.setAlignment(dealerView, Pos.CENTER);
        BorderPane.setAlignment(drawPile, Pos.CENTER);

        refresh();
    }

    public void refresh() {
        BlackjackTable table = engine.getTable();
        boolean over = engine.isRoundOver();

        // Banca: segunda carta tapada hasta que termina la ronda
        dealerView.refresh(!over, false, "");

        playersBox.getChildren().clear();
        BlackjackPlayer current = over ? null : engine.getCurrentPlayer();
        for (BlackjackPlayer player : table.getPlayers()) {
            BlackjackPlayerUI view = new BlackjackPlayerUI(() -> player, CARD_WIDTH);
            view.refresh(false, player == current, infoOf(player, over));
            playersBox.getChildren().add(view);
        }

        drawPile.refresh();

        hit.setDisable(over);
        stand.setDisable(over);
        if (over) {
            status.setText("Round over - press New round");
        } else {
            status.setText(current.getName() + "'s turn");
        }
    }

    private String infoOf(BlackjackPlayer player, boolean over) {
        if (over) {
            return String.valueOf(engine.getResult(player));
        }
        if (player.isBust()) {
            return "BUST";
        }
        return player.isStanding() ? "stand" : "";
    }

    public Button getHitButton() {
        return hit;
    }

    public Button getStandButton() {
        return stand;
    }

    public Button getNewRoundButton() {
        return newRound;
    }

    public Label getStatusLabel() {
        return status;
    }

    public DeckUI<StdCard> getDrawPile() {
        return drawPile;
    }
}
