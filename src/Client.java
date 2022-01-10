import java.net.*;
import java.io.*;

//Responsible for creating client socket and the transfer of game data with server
public class Client extends Thread{

    int port;
    String serverName;
    InetAddress ip;

    public Client(int port, String serverName, InetAddress ip){
        System.setProperty("java.net.preferIPv4Stack", "true");
        this.port = port;
        this.serverName = serverName;
        this.ip = ip;
    }

    public void run(){

        try {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port, ip, 0);
            System.out.println("Connected to " + client.getRemoteSocketAddress());

            //Opens IO streams for game data transfer
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());

            ClientGame.run();

            while (true) {
                //Reads in data from server and updates game variables
                GameDataPacket dataIn = (GameDataPacket) in.readObject();
                ClientGame.p1y = dataIn.getP1y();
                ClientGame.ballx = dataIn.getBallx();
                ClientGame.bally = dataIn.getBally();
                //writes out client data
                out.writeObject(new GameDataPacket(0, ClientGame.p2y,0,0,0,0));
            }
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
