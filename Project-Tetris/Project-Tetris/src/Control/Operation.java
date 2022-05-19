package Control;
import Data.GameData;
import UI.GamePanel;
import UI.ImageButton;
import UI.MainWindow;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class Operation
{
    MainWindow mainWindow;
    GameData gameData;
    public ImageButton left;
    public ImageButton right;
    public ImageButton rotate;
    public ImageButton down;
    public JButton button1;
    public JButton save;
    public JButton exit;

    public Operation()
    {
        buttonOperation();
    }

    public void buttonOperation()
    {
        //下左右移动和旋转
        left = new ImageButton(new ImageIcon("Project-Tetris\\Image\\left.png"));
        left.addActionListener(a->
        {
            if(gameData.state == 1)
            {
                gameData.move(true, -1);
                mainWindow.getGamePanel().repaint();
            }
        });
        right = new ImageButton(new ImageIcon("Project-Tetris\\Image\\right.png"));
        right.addActionListener(a->
        {
            if(gameData.state == 1)
            {
                gameData.move(true, 1);
                mainWindow.getGamePanel().repaint();
            }
        });
        down= new ImageButton(new ImageIcon("Project-Tetris\\Image\\down.png"));
        down.addActionListener(a->
        {
            if(gameData.state == 1)
            {
                if(gameData.move(false, 1))//方块组落地为true
                {
                    mainWindow.getScoreNextPanel().repaint();//方块组落地刷新得分
                }
                    mainWindow.getGamePanel().repaint();
            }
        });
        rotate = new ImageButton(new ImageIcon("Project-Tetris\\Image\\rotate.png"));
        rotate.addActionListener(a->
        {
            if(gameData.state == 1)
            {
                gameData.rotate();
                mainWindow.getGamePanel().repaint();
            }
        });
        button1 = new JButton("Start");
        button1.addActionListener(a->
        {
            //弹窗
            if (gameData.state == 1){ gameData.state = 2;pause();}
            else { gameData.state = 1;}
            button1.setText(gameData.button1Choices[gameData.state]);
        });




        save = new JButton("Save");
        save.addActionListener(a -> {
            Save();//保存弹窗
        });


        exit = new JButton("Exit");
        exit.addActionListener(a->
        {
            mainWindow.jFrame.dispose();
            new StartInterfacenew().init();
            gameData.state = 4;

        });
    }

    //关联窗口
    //将按键输入到窗口，而不是panel和button
    public void setWindow(MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;
        //取消按钮截获按键
        button1.setFocusable(false);
        save.setFocusable(false);
        exit.setFocusable(false);
        this.mainWindow.jFrame.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e)
            {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_LEFT:
                        if(gameData.state == 1)
                        {
                            gameData.move(true, -1);
                            mainWindow.getGamePanel().repaint();
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(gameData.state == 1)
                        {
                            gameData.move(true, 1);
                            mainWindow.getGamePanel().repaint();
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (gameData.state == 1)
                        {
                            if(gameData.move(false, 1))//方块组落地为true
                            {
                                mainWindow.getScoreNextPanel().repaint();//方块组落地刷新得分
                            }
                            mainWindow.getGamePanel().repaint();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if(gameData.state == 1)
                        {
                            gameData.rotate();
                            mainWindow.getGamePanel().repaint();
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        if (gameData.state == 1){ gameData.state = 2;}
                        else { gameData.state = 1;}
                        button1.setText(gameData.button1Choices[gameData.state]);
                        break;
                }
            }
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    //关联数据
    public void setData(GameData gameData)
    {
        this.gameData = gameData;
    }
/*
保存按钮弹窗
* */
    public void Save(){
        JFrame Save = new JFrame();
        Save.setTitle("SAVE GAME");
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
        OK.addActionListener(a -> {
            Save.dispose();
            new StartInterfacenew().init();
        });
        JButton Cancel = new JButton("Cancel");
        Cancel.setLocation(260, 75);
        Cancel.setSize(160, 20);
        Save.add(Cancel);
        Cancel.addActionListener(a -> {
            Save.dispose();
        });

    }
    /*
    * 暂停按钮弹窗
    * */
    public void pause(){
        JFrame Save = new JFrame();
        Save.setTitle("CHOICE");
        Save.setLocation(510, 300);
        Save.setSize(450, 150);
        Save.setVisible(true);
        Save.setLayout(null);
        JLabel txt1 = new JLabel();
        txt1.setText("             Do you want to continue?");
        txt1.setSize(420, 40);
        txt1.setLocation(5, 5);
        txt1.setVisible(true);
        Save.add(txt1);

        JButton OK = new JButton("OK");
        OK.setLocation(5, 55);
        OK.setSize(160, 40);
        Save.add(OK);
        OK.addActionListener(a -> {
            Save.dispose();
            if (gameData.state == 1){ gameData.state = 2;pause();}
            else { gameData.state = 1;}
            button1.setText(gameData.button1Choices[gameData.state]);
        });
        JButton Cancel = new JButton("NO");
        Cancel.setLocation(260, 55);
        Cancel.setSize(160, 40);
        Save.add(Cancel);
        Cancel.addActionListener(a -> {
            Save.dispose();
            mainWindow.jFrame.dispose();
            new StartInterfacenew().init();
        });

    }
}





