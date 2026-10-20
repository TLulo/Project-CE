package blackjack;

import blackjack.src.Engine.BlackjackEngine;
import blackjack.src.Player.BlackjackPlayer;
import blackjack.src.Table.BlackjackTable;
import blackjack.UI.BlackjackTableUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Blackjack hot-seat sobre el framework. Main propio, independiente de UI.Main.
 * Solo arma el engine y conecta las acciones; la presentación vive en blackjack.UI.
 * Cartas: images/cards/{id}.png (id 1..52) y back.png; si no existen CardUI dibuja un reemplazo.
 */
public class Main extends Application {
    private static final int PLAYERS = 2;

    @Override
    public void start(Stage stage) {
        BlackjackTable table = new BlackjackTable(BlackjackTable.buildStandardDeck());
        for (int i = 1; i <= PLAYERS; i++) {
            BlackjackPlayer player = new BlackjackPlayer();
            player.setName("Player " + i);
            table.addPlayer(player);
        }
        BlackjackEngine engine = new BlackjackEngine(table);
        BlackjackTableUI ui = new BlackjackTableUI(engine);

        ui.getHitButton().setOnAction(e -> {
            engine.hit();
            ui.refresh();
        });
        ui.getStandButton().setOnAction(e -> {
            engine.stand();
            ui.refresh();
        });
        ui.getNewRoundButton().setOnAction(e -> {
            engine.startRound();
            ui.refresh();
        });

        engine.startRound();
        ui.refresh();

        stage.setTitle("Blackjack");
        stage.setScene(new Scene(ui, 1024, 780));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
