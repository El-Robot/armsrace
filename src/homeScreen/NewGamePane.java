package homeScreen;

import clients.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import server.Server;

import java.io.IOException;
import java.net.InetAddress;


public class NewGamePane extends VBox {

    public NewGamePane() {

        super();

        Button newGameButton = new Button("New Game");
        getChildren().add(newGameButton);
        setAlignment(Pos.CENTER);

        newGameButton.setOnAction(event -> {

            // remove new game button
            getChildren().clear();
            // add player options
            getChildren().addAll(
                    addPlayersButton(2),
                    addPlayersButton(3),
                    addPlayersButton(4));

        });

    }

    private Button addPlayersButton(int n) {

        Button b = new Button(n + " Players");
        b.setOnAction(event -> {

            try {

                Server s = new Server(n);
                Thread t = new Thread(s);
                t.start();

                String ip = InetAddress.getLocalHost().getHostAddress();
                int port = s.getPort();

                System.out.println(ip);
                System.out.println(port + "");

                Home.setScene(ip, port);

            } catch (IOException e) {
                e.printStackTrace();
            }


        });

        return b;

    }

}
