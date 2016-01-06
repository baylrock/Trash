package chat.client.main.client.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by baylrock on 06.01.2016.
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

    private void SetConection() {
        try {
            socket = new Socket("144.76.102.25",4242);
            setWriter(new PrintWriter(socket.getOutputStream()));
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
            String message;
            try {
                while((message = getReader().readLine())!=null) {

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
