package clients;

import game_stuff.Attributes;

import java.io.*;
import java.net.Socket;

public class Player implements Runnable {

    private int year = 0;
    private boolean turnStarted = false;
    private final Socket connection;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    private final int number;
    private final PlayerView gui;
    private Attributes attributes;

    public Player(String ip, int port, PlayerView playerView) throws IOException {

        connection = new Socket("localhost", port);
        gui = playerView;

        out = new ObjectOutputStream(
                connection.getOutputStream());

        in = new ObjectInputStream(
                connection.getInputStream());

        // gets player number from server
        number = in.read();

        System.out.println(number);

    }

    @Override
    public void run() {

        try {

            while (!connection.isClosed()) {
                attributes = (Attributes) in.readObject();
                turnWaiting();

            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitingForPlayers() throws IOException {
        in.read();
        System.out.println("game has started");
    }

    public void turnWaiting() throws InterruptedException, IOException {
        synchronized (this) {
            turnStarted = true;
            // waits 30 seconds
            wait(30000);
            out.writeObject(attributes);
            year++;
            turnStarted = false;
        }
    }
}