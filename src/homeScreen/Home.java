package homeScreen;

import clients.PlayerView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Home extends Application {

    private static Stage stage;

    public static void main(String[] args) {

        launch();

    }

    @Override
    public void start(Stage stage) throws Exception {

        Home.stage = stage;
        stage.setTitle("Arms Race v0.1");
        stage.centerOnScreen();
        BorderPane mainPane = new BorderPane();
        stage.setScene(new Scene(mainPane,500, 500));
        stage.setResizable(false);

        NewGamePane newGame = new NewGamePane();
        newGame.setFillWidth(true);
        newGame.setStyle("-fx-background-color: lightgreen");
        newGame.setPrefWidth(250);

        JoinGamePane joinGame = new JoinGamePane();
        joinGame.setFillWidth(true);
        joinGame.setStyle("-fx-background-color: lightblue");
        joinGame.setPrefWidth(250);

        mainPane.setRight(newGame);
        mainPane.setLeft(joinGame);

        stage.show();

    }

    public static void setScene(String ip, int port) throws IOException {

        PlayerView p = new PlayerView(ip, port);
        stage.setTitle("NEW TITLE");
        stage.setScene(new Scene(p, 500, 500));
        stage.show();

    }

    @Override
    public void stop() throws Exception {
        super.stop(); //To change body of generated methods, choose Tools | Templates.
        System.exit(0);
    }
}
