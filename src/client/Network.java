package client;

import javafx.application.Platform;

import javax.imageio.IIOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.TreeMap;

public class Network {

    private static final String SERVER_ADRESS = "localhost";
    private static final int SERVER_PORT = 8189;

    private final int port;
    private final String host;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private Socket socket;

    Network() {
        this(SERVER_PORT, SERVER_ADRESS);
    }

    Network(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setDataOutputStream(String str) {
        try {
            this.dataOutputStream.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean Connect() {
        try {
            socket = new Socket(SERVER_ADRESS, SERVER_PORT);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            return true;
        } catch (IOException e) {
            System.out.println("Ошибка установки соединения!");
            e.printStackTrace();
            return false;
        }
        }



    public void Close() {
        try {
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitMessage(Controller controller) {
//       {Thread thread = new Thread(() -> {
                while (!socket.isClosed()) {
                    try {  String message = dataInputStream.readUTF();
                    controller.appendMessage(message);
                }
                    catch (SocketException e) {
                        // user closed connection
                        return;
                    }
                 catch (IOException e) {
                System.out.println("Соединение потеряно!");
                 e.printStackTrace();
            }
        }

    }
//        );
//        thread.setDaemon(true);
//        thread.start();
//}
//}

}

