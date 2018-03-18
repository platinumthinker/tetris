import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.ecs.Entity;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.effect.Effect;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Map;

public class BasicGameApp extends GameApplication {
    private enum Type {
        PLAYER,
        BLOCK
    }

    private Entity player;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setApplicationMode(ApplicationMode.DEVELOPER);
        settings.setCloseConfirmation(false);
        settings.setIntroEnabled(false);
        settings.setMenuEnabled(false);
        settings.setProfilingEnabled(false);
        settings.setTitle("Simple Tetris");
    }

    @Override
    protected void initGame() {
        player = Entities.builder()
                .type(Type.PLAYER)
                .at(100, 100)
                .viewFromNode(new Rectangle(40, 40))
                .buildAndAttach(getGameWorld());
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
    }

    @Override
    protected void initUI() {
        Text uiScore = getUIFactory().newText("", Color.BLACK, 16.0);
        uiScore.setSmooth(true);
        uiScore.setTranslateX(20);
        uiScore.setTranslateY(20);
        uiScore.textProperty().bind(getGameState().intProperty("score").asString("Score: %s"));
        getGameScene().addUINode(uiScore);
    }


    @Override
    protected void onUpdate(double tpf) {
        getGameState().increment("score", +1);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
