package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {

        public static final int SERVER_PORT = 8189;

        public static void main(String[] args) {
            try {
                ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
                System.out.println("Ожидание подключения...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключился");
                DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                while (true) {
                    String str = dataInputStream.readUTF();
                    if (str.equals("/end")) {
                        break;
                    }
                    Thread tread = new Thread(() -> {
                        Scanner scanner = new Scanner(System.in);
                        if (!scanner.next().isBlank()) {
                            try {
                                dataOutputStream.writeUTF(scanner.nextLine());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
            catch (IOException e){
                System.out.println("Порт уже занят!");
                e.printStackTrace();
            }
        }
    }

