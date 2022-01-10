import java.net.*;
import java.io.*;

//Responsible for creating server socket and the transfer of game data with client
public class Server extends Thread{

    private ServerSocket serverSocket;

    public Server(ServerSocket ss) {
        serverSocket = ss;
    }

    public void run() {
        while(true) {
            try {
                System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("Just connected to " + server.getRemoteSocketAddress());

                //Opens IO streams for game data transfer
                ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(server.getInputStream());

                ServerGame.run();

                while(true) {
                    //writes custom data object to output stream
                    out.writeObject(new GameDataPacket(ServerGame.p1y, 0,ServerGame.ballx,ServerGame.bally,0,0));
                    GameDataPacket dataIn = (GameDataPacket) in.readObject();
                    //updates game variables with client info
                    ServerGame.p2y = dataIn.getP2y();
                }

            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


}
