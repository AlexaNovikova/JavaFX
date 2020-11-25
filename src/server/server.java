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
            System.out.println("Ожидаем подключения...");
            try{ ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключился");
                   while (!clientSocket.isClosed()){

                    DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
                    DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

                    Thread thread = new Thread(() -> {
                        while (true) {
                            Scanner scanner = new Scanner(System.in);
                            String serverMessage = scanner.nextLine();
                            if (!serverMessage.isEmpty()) {
                                try {
                                    dataOutputStream.writeUTF("Сообщение от сервера: " + serverMessage);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

                    thread.setDaemon(true);
                    thread.start();

                    while (true) {
                        String str;
                        try {
                            str = dataInputStream.readUTF();
                            System.out.println(str);
//                        dataOutputStream.writeUTF("Сообщение от сервера: " + str.toUpperCase());
                            if(str.equalsIgnoreCase("exit")){
                                System.out.println("Клиент прервал соединение.");
                                dataOutputStream.flush();
                                break;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    dataInputStream.close();
                    dataOutputStream.close();
                    clientSocket.close();
                   }

            }
            catch (IOException e){
                System.out.println("Порт уже занят!");
                e.printStackTrace();
            }

        }

    }


