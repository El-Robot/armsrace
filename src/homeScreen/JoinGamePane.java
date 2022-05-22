package homeScreen;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.crypto.spec.PSource;
import java.io.IOException;

public class JoinGamePane extends VBox {

    public JoinGamePane() {

        super();

        Button newGameButton = new Button("Join Game");
        getChildren().add(newGameButton);
        setAlignment(Pos.CENTER);

        newGameButton.setOnAction(event -> {

            // remove join game button
            getChildren().clear();
            // add input and try button

            TextField ipInput = new TextField();
            TextField portInput = new TextField();

            Button join = new Button("Join Game");
            join.setOnAction(tryPort -> {

                String ip = ipInput.getText();
                int port = Integer.parseInt(portInput.getText());

                try {
                    Home.setScene(ip, port);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

            getChildren().addAll(ipInput, portInput, join);

        });

    }

}
