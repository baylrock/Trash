package chat.client.main.client.main;

import javax.swing.table.TableColumn;
import java.io.*;
import java.net.Socket;

/**
 * OLOLOLOLOLO template
 */
public class ClientMain {
    public static void main (String[] args) {
        ClientMain here = new ClientMain();
    }

    clientGUI gui;
    Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    ClientMain() {



        gui = new clientGUI(this);
        SetConection();
        Thread thread = new Thread(new InpurReaderThread());
        thread.start();

    }

    public void SetConection() {
        try {
            socket = new Socket("144.76.102.25",4242);
            setWriter(new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8")));
            setReader(new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8")));

            System.out.println("-------------Conection complite------------");
            gui.getChatArea().setText("--------------Conected!-----------------" + "\n");
        } catch (IOException e) {
            gui.getChatArea().setText("--------------Conection Failed!-------------" + "\n");
            e.printStackTrace();
        }
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    class InpurReaderThread implements Runnable {

        @Override
        public void run() {
            String message, prviousMess = "";

            try {
                while((message = getReader().readLine())!=null) {

                    if (message.contains("/list/")) {
                        message = message.replace("/list/","");
                        if (!gui.getUsersArea().getText().contains(message)) { gui.getUsersArea().append(message); }
                        prviousMess = message;
                        continue;
                    }
                    if (message.equals(prviousMess)) {
                        prviousMess = message;
                        continue;
                    }
                    prviousMess = message;

                    System.out.println(message);
                    gui.getChatArea().append(message+"\n");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                gui.getChatArea().append("-----------Conection Failed!!--------------- "+"\n");
            }
        }
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }
}
