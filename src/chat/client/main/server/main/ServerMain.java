package chat.client.main.server.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by baylrock on 06.01.2016.
 */
public class ServerMain {
    ArrayList<PrintWriter> clients;

    public static void main(String[] args) {
        ServerMain sm = new ServerMain();
    }

    ServerMain() {
        try {
            ServerSocket SS = new ServerSocket(4242);
            clients = new ArrayList<PrintWriter>();
            while (true) {
                Socket soc = SS.accept();
                PrintWriter printWriter = new PrintWriter(soc.getOutputStream());
                clients.add(printWriter);

                Thread thread = new Thread(new ClientHandler(soc));
                thread.start();
                System.out.println("ClientCnected with adress - " + soc.getInetAddress());
                SendToAll("ClientCnected with adress - " + soc.getInetAddress());
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    class ClientHandler implements Runnable{
        BufferedReader reader;
        Socket soc;

        @Override
        public void run() {
            String message;
            try {

                while ((message = reader.readLine())!=null) {
                    System.out.println(message);
                    SendToAll(message);
                }
            } catch (IOException e) {
                System.out.println("Conection faild with " + soc.getInetAddress());
                SendToAll("Conection faild with " + soc.getInetAddress());
            }

        }

        public ClientHandler(Socket clientSocet) {
            soc = clientSocet;
            try {
                reader = new BufferedReader(new InputStreamReader(soc.getInputStream(),"UTF-8"));  //windows-1251
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void SendToAll(String message) {
        Iterator iterator = clients.iterator();
        try {
            while (iterator.hasNext()) {
                PrintWriter writer = (PrintWriter) iterator.next();
                writer.println(message);
                writer.flush();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
