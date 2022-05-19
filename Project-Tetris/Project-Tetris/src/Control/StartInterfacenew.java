package Control;

import javax.swing.*;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartInterfacenew   {
    JFrame StartInterface = new JFrame("Start Interface");
    JButton newG=new JButton("NEW GAME");
    JButton loadgame=new JButton("LOAD GAME");
    JButton login=new JButton("LOG IN");
    ImageIcon imagic2=new ImageIcon("D:\\BaiduNetdiskDownload\\java俄罗斯方块源码\\" +
            "java俄罗斯方块源码\\tetris\\src\\tetris\\game\\view\\start2.png");

    public void init(){
        JPanel startStaticPanel=new JPanel();


        //startStaticPanel.add(newG);
        newG.setBounds(125,320,110,40);
        newG.setFocusPainted(false);
        StartInterface.add(newG);
        newG.addActionListener(w -> {
            new MainFrame();
            StartInterface.dispose();

        });


        startStaticPanel.add(loadgame);
        loadgame.setBounds(125,380,110,40);
        StartInterface.add(loadgame);
        loadgame.addActionListener(e -> {
            Load();

        });



        startStaticPanel.add(login);
        login.setBounds(125,440,110,40);
        StartInterface.add(login);
        login.addActionListener(e -> {
            Login();

        });





        StartInterface.setBounds(550,100,360,640);
        StartInterface.setResizable(false);
        StartInterface.setLayout(null);
        StartInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StartInterface.setVisible(true);


        JLabel j2=new JLabel(imagic2);
        j2.setBounds(0,0,360,640);
        StartInterface.getContentPane().add(j2);
    }



    /*
    * 加载游戏
    * */
    public void Load(){
        JFrame Save = new JFrame();
        Save.setTitle("LOAD GAME");
        Save.setLocation(510, 300);
        Save.setSize(450, 150);
        Save.setVisible(true);
        Save.setLayout(null);
        JLabel txt1 = new JLabel();
        txt1.setText("Input save's name to save game(don't need include filename extension)");
        txt1.setSize(420, 20);
        txt1.setLocation(5, 5);
        txt1.setVisible(true);
        Save.add(txt1);
        JTextField save = new JTextField();
        save.setLocation(5, 40);
        save.setSize(420, 20);
        Save.add(save);
        JButton OK = new JButton("OK");
        OK.setLocation(5, 75);
        OK.setSize(160, 20);
        Save.add(OK);
        JButton Cancel = new JButton("Cancel");
        Cancel.setLocation(260, 75);
        Cancel.setSize(160, 20);
        Save.add(Cancel);
        Cancel.addActionListener(a -> {
            Save.dispose();
        });

    }
    public void Login(){
        JFrame Save = new JFrame();
        Save.setTitle("LOG IN");
        Save.setLocation(510, 300);
        Save.setSize(450, 150);
        Save.setVisible(true);
        Save.setLayout(null);
        JLabel txt1 = new JLabel();
        txt1.setText("Input your name to log in the game");
        txt1.setSize(420, 20);
        txt1.setLocation(5, 5);
        txt1.setVisible(true);
        Save.add(txt1);
        JTextField save = new JTextField();
        save.setLocation(5, 40);
        save.setSize(420, 20);
        Save.add(save);
        JButton OK = new JButton("OK");
        OK.setLocation(5, 75);
        OK.setSize(160, 20);
        Save.add(OK);
        JButton Cancel = new JButton("Cancel");
        Cancel.setLocation(260, 75);
        Cancel.setSize(160, 20);
        Save.add(Cancel);
        Cancel.addActionListener(a -> {
            Save.dispose();
        });

    }
}




