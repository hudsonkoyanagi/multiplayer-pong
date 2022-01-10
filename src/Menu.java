import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

//GUI for user to pick to be either the host or the client
public class Menu extends Thread{

    public void run() {
        System.setProperty("java.net.preferIPv4Stack", "true");
        createMainMenu();
    }
    public static void createMainMenu(){
        JFrame mainMenu = new JFrame();
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton serverB = new JButton("Host Server");
        serverB.setBounds(10,40,200,40);

        JButton clientB = new JButton("Connect to Server");
        clientB.setBounds(10,90,200,40);

        mainMenu.add(serverB);
        mainMenu.add(clientB);

        serverB.addActionListener(e ->{
            try {
                createHostMenu();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        clientB.addActionListener(e ->{
            try {
                createClientMenu();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        mainMenu.setSize(250,200);
        mainMenu.setLayout(null);
        mainMenu.setVisible(true);
    }

    public static void createHostMenu() throws IOException {
        JFrame hostMenu = new JFrame();
        hostMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel portField = new JLabel("");
        portField.setBounds(5, 10, 300, 40);
        portField.setFont(new Font("Dialog", Font.PLAIN, 20));

        JLabel ipField = new JLabel("");
        ipField.setBounds(5, 60, 300, 40);
        ipField.setFont(new Font("Dialog", Font.PLAIN, 20));

        JLabel serverNameField = new JLabel("");
        serverNameField.setBounds(5, 110, 300, 40);
        serverNameField.setFont(new Font("Dialog", Font.PLAIN, 20));

        JLabel messageField = new JLabel("Waiting for client");
        messageField.setBounds(5,160,300,40);
        messageField.setFont(new Font("Dialog", Font.PLAIN, 15));

        ServerSocket ss = new ServerSocket(0);
        InetAddress ip = InetAddress.getLocalHost();

        portField.setText("Port: "+ss.getLocalPort());
        hostMenu.add(portField);

        ipField.setText("IP Address: "+ip.getHostAddress());
        hostMenu.add(ipField);

        serverNameField.setText("Sever Name: " + ip.getHostName());
        hostMenu.add(serverNameField);

        hostMenu.add(messageField);

        Thread server = new Server(ss);
        server.start();

        hostMenu.setSize(400,250);
        hostMenu.setLayout(null);
        hostMenu.setVisible(true);

    }

    public static void createClientMenu() throws IOException {
        JFrame clientMenu = new JFrame();
        clientMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextField portField = new JTextField("Port Number");
        portField.setBounds(5, 0, 200, 40);

        JTextField ipField = new JTextField("IP");
        ipField.setBounds(5, 50, 200, 40);

        JTextField serverNameField = new JTextField("Server Name");
        serverNameField.setBounds(5, 100, 200, 40);

        JButton connectB = new JButton("Connect");
        connectB.setBounds(5, 150,100,40);
        connectB.addActionListener(e->{
            InetAddress ip = null;
            try {
                ip = InetAddress.getByName(ipField.getText());
                System.out.println(ip);
            } catch (UnknownHostException unknownHostException) {
                unknownHostException.printStackTrace();
            }
            Thread client = new Client(Integer.parseInt(portField.getText()), serverNameField.getText(), ip);
            client.start();
        });


        clientMenu.add(portField);
        clientMenu.add(ipField);
        clientMenu.add(serverNameField);
        clientMenu.add(connectB);

        clientMenu.setSize(250,250);
        clientMenu.setLayout(null);
        clientMenu.setVisible(true);
    }
}