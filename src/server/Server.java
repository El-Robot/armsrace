package server;

import game_stuff.Attributes;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Server implements Runnable {

    private int port;
    private ServerSocket ss;
    private Socket[] players;
    private ObjectInputStream[] playerInputs;
    private ObjectOutputStream[] playerOutputs;
    private Attributes[] playerAttributes;

    private BlockingQueue<Integer> blockingQueue;

    public Server(int playerCount) throws IOException {

        try {
            ss = new ServerSocket(0);
            port = ss.getLocalPort();
            blockingQueue = new ArrayBlockingQueue<>(playerCount);

            players = new Socket[playerCount];
            playerInputs = new ObjectInputStream[playerCount];
            playerOutputs = new ObjectOutputStream[playerCount];
            playerAttributes = new Attributes[playerCount];

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getPort() {
        return port;
    }

    @Override
    public void run() {

        // get the players
        for (int i = 0; i < players.length; i++) {

            try {

                players[i] = ss.accept();

                playerInputs[i] = new ObjectInputStream(players[i].getInputStream());
                playerOutputs[i] = new ObjectOutputStream(players[i].getOutputStream());
                playerAttributes[i] = new Attributes();
                playerOutputs[i].write(i);

            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Player " + i + " is in!");

        }

        System.out.println("That's everyone!!!");

        try {
            for (ObjectOutputStream d : playerOutputs) {
                d.write(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
