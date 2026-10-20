package UI.ScaledPane;

import javafx.scene.layout.Region;
import javafx.scene.transform.Scale;

/**
 * Contenedor que escala su contenido de forma uniforme para que siempre entre
 * en la ventana. El contenido se maqueta a su tamaño preferido (el "tamaño de
 * diseño") y luego se reduce o amplía, manteniendo la proporción y centrado.
 *
 * Es genérico: sirve para StdTableUI y para cualquier UI de un juego.
 */
public class ScaledPane extends Region {
    private static final double MAX_SCALE = 1.5;

    private final Region content;
    private final Scale scale = new Scale(1, 1, 0, 0);

    public ScaledPane(Region content) {
        this.content = content;
        content.getTransforms().add(scale);
        getChildren().add(content);
    }

    public Region getContent() {
        return content;
    }

    @Override
    protected void layoutChildren() {
        double designW = content.prefWidth(-1);
        double designH = content.prefHeight(designW);
        if (designW <= 0 || designH <= 0 || getWidth() <= 0 || getHeight() <= 0) {
            return;
        }

        double s = Math.min(getWidth() / designW, getHeight() / designH);
        s = Math.min(s, MAX_SCALE);

        content.resize(designW, designH);
        scale.setX(s);
        scale.setY(s);
        content.relocate((getWidth() - designW * s) / 2, (getHeight() - designH * s) / 2);
    }

    @Override
    protected double computePrefWidth(double height) {
        return content.prefWidth(-1);
    }

    @Override
    protected double computePrefHeight(double width) {
        return content.prefHeight(-1);
    }

    // Permite achicar la ventana libremente: el contenido se escala
    @Override
    protected double computeMinWidth(double height) {
        return 0;
    }

    @Override
    protected double computeMinHeight(double width) {
        return 0;
    }
}