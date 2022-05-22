package clients;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PlayerView extends BorderPane {


    public PlayerView(String ip, int port) throws IOException {

        super();

        VBox ipAndPort = new VBox();
        ipAndPort.getChildren().addAll(
                new Label(ip),
                new Label(port + "")
        );
        ipAndPort.setAlignment(Pos.CENTER);
        setCenter(ipAndPort);

        Player p = new Player(ip, port, this);

        (new Thread(p)).start();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();

    }

    public void startGame() {

        setCenter(new Label("GAME HAS STARTED"));

    }

    public void update() {
        System.out.println("Working");
    }

}
