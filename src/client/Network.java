package client;

import javax.imageio.IIOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Network{

    private static final String SERVER_ADRESS = "localhost";
    private static final int SERVER_PORT = 8189;

    private final int port;
    private final String host;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private Socket socket;

    Network(){
        this(SERVER_PORT, SERVER_ADRESS);
    }

    Network( int port, String host){
        this.port=port;
        this.host=host;
    }

    public boolean Connect (){
        try {
            socket = new Socket(SERVER_ADRESS, SERVER_PORT);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream= new DataOutputStream(socket.getOutputStream());
           return true;
        }
        catch (IOException e) {
            System.out.println("Ошибка установки соединения!");
            e.printStackTrace();
            return false;

        }
    }
    public void Close (){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

