package chat.client.main.client.main;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by baylrock on 06.01.2016.
 */
public class clientGUI extends JFrame {

    private ClientMain mainObj;
    private JButton Send;
    private JTextField textField;
    private JTextArea chatArea;
    private JMenuBar menuBar;
    private JMenuItem reconectMenuItem;
    private JMenuItem nameMenuItem;
    private JMenu menu;
    private String UserName;
    private NameFrame nameFrame;
    private JFrame usersframe;
    private JTextArea usersArea;


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
        nameFrame = new NameFrame();

        Send = new JButton("Send Mess");
        Send.addActionListener(new SendActionListener());

        textField = new JTextField(20);

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
        menu.add((nameMenuItem = new JMenuItem("Set NickName")));
        nameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameFrame.setVisible(true);
                mainObj.gui.setVisible(false);
                usersframe.setVisible(false);
            }
        });
        reconectMenuItem.addActionListener(new ReconectMenuItemListener());
        setJMenuBar(menuBar);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(chatScrol);
        mainPanel.add(textField);
        mainPanel.add(Send);




        getContentPane().add(BorderLayout.CENTER,mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();


        usersframe = new JFrame();
        usersframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JScrollPane usersScrolPane = new JScrollPane(usersArea = new JTextArea(10,10));
        usersArea.append("Users Online: \n");
        usersScrolPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        usersframe.getContentPane().add(usersScrolPane);
        usersframe.pack();




    }

    public JTextArea getChatArea() {
        return chatArea;
    }

    public void setChatArea(JTextArea chatArea) {
        this.chatArea = chatArea;
    }

    public JTextArea getUsersArea() {
        return usersArea;
    }

    public void setUsersArea(JTextArea usersArea) {
        this.usersArea = usersArea;
    }


    class SendActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String message;
            if ((message=textField.getText()).equals("")) {return;}
            if (UserName.equals("")) { UserName = "ANONYMUS";}
            try {
                PrintWriter writer = mainObj.getWriter();

                writer.println(UserName+" : "+textField.getText());
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
            mainObj.setReader(new BufferedReader(new InputStreamReader(mainObj.socket.getInputStream(),"UTF-8")));
            System.out.println("Conection complite");
            } catch (IOException ex ) {
                ex.printStackTrace();
                System.out.println("Conection Errrorrrr");
            }
        }
    }

    class NameFrame extends JFrame {

        public NameFrame() {
            JTextField field = new JTextField(10);
            field.requestFocus();
            JFrame frame = this;

            JButton button = new JButton("Set");
            JPanel panel = new JPanel();
            panel.add(field);
            panel.add(button);
            getContentPane().add(panel);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    UserName = field.getText();
                    if (!UserName.equals("")) {
                        mainObj.gui.setVisible(true);
                        frame.setVisible(false);
                        usersframe.setVisible(true);
                    }

                }
            });
            pack();
            setVisible(true);
        }

    }





}
