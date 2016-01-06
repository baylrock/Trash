package chat.client.main.client.main;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by baylrock on 06.01.2016.
 */
public class clientGUI extends JFrame {

    private ClientMain mainObj;
    private JButton Send;
    private JTextField textField;
    private JTextField textName;
    private JTextArea chatArea;
    private JMenuBar menuBar;
    private JMenuItem reconectMenuItem;
    private JMenu menu;


    public ClientMain getMainObj() {
        return mainObj;
    }

    public void setMainObj(ClientMain mainObj) {
        this.mainObj = mainObj;
    }

    public JButton getSend() {
        return Send;
    }

    public void setSend(JButton send) {
        Send = send;
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }


    clientGUI(ClientMain mainObj) {
        this.mainObj = mainObj;
        Send = new JButton("Send Mess");
        Send.addActionListener(new SendActionListener());
        textField = new JTextField(20);
        textName = new JTextField(10);
        chatArea = new JTextArea(15,50);
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane chatScrol = new JScrollPane(chatArea);
        chatScrol.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        chatScrol.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        menuBar = new JMenuBar();
        menuBar.add((menu = new JMenu("Menu")));
        menu.add((reconectMenuItem = new JMenuItem("Reconect")));
        reconectMenuItem.addActionListener(new ReconectMenuItemListener());
        setJMenuBar(menuBar);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(chatScrol);
        mainPanel.add(textName);
        mainPanel.add(textField);
        mainPanel.add(Send);

        getContentPane().add(BorderLayout.CENTER,mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }

    public JTextArea getChatArea() {
        return chatArea;
    }

    public void setChatArea(JTextArea chatArea) {
        this.chatArea = chatArea;
    }

    class SendActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (textName.getText() == "") { textName.setText("ANONYMUS");}
            try {
                PrintWriter writer = mainObj.getWriter();

                writer.println(textName.getText()+" : "+textField.getText());
                writer.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            textField.setText("");
            textField.requestFocus();
        }
    }

    class ReconectMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
            mainObj.socket = new Socket("144.76.102.25",4242);
            mainObj.setWriter(new PrintWriter(mainObj.socket.getOutputStream()));
            mainObj.setReader(new BufferedReader(new InputStreamReader(mainObj.socket.getInputStream(),"windows-1251")));
            System.out.println("Conection complite");
            } catch (IOException ex ) {
                ex.printStackTrace();
                System.out.println("Conection Errrorrrr");
            }
        }
    }





}
