package UI;
import Control.Operation;
import Data.GameData;
import Data.PlayerData;

import javax.swing.*;
import java.awt.*;

//主窗口=静态界面+游戏界面
public class MainWindow
{
    public JFrame jFrame;

    GamePanel gamePanel;
    Container mainPanel;//用来放下面那个layerpane
    Operation operation;
    GameData gameData;
    StaticPanel staticPanel;
    ScoreNextPanel scoreNextPanel;
    PlayerPanel playerPanel;

    public MainWindow(Operation operation, GameData gameData)
    {
        jFrame= new JFrame();
        this.operation = operation;
        this.gameData = gameData;//从构造器获取参数
        jFrame.setBounds(550, 100, 360, 640 );//单位是像素点个数
        //setLocationRelativeTo(null);//窗口居中
        jFrame.setResizable(false);//不允许改变窗口大小
        jFrame.setLayout(null);//图片自由布局，用了setbounds就要用
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点击关闭则结束程序
        jFrame.setTitle("Tetris");
        //设置背景
        setBackground();
        //绘制静态界面
        setStaticPanel();
        //绘制游戏界面
        setGamePanel();
        //绘制得分和提示区
        setScoreNextPanel();
        //设置图层顺序
        //setPanelOrder();
        //设置窗口获取按键的权力!
        jFrame.setFocusable(true);
        //设置玩家排名界面
        setPlayerPanel();
    }

    //设置背景
    public void setBackground()
    {
        ImageIcon background1 = new ImageIcon("Project-Tetris\\Image\\background.png");//读取图片
        JLabel label1 = new JLabel(background1);//用标签获取图片
        label1.setBounds(0, 0, 360,640);//相较于窗口的位置
        jFrame.getContentPane().add(label1);//获取主窗口的窗格并添加图片
    }

    //绘制静态界面
    public void setStaticPanel()
    {
        mainPanel = jFrame.getLayeredPane();
        staticPanel = new StaticPanel(operation);
        mainPanel.add(staticPanel);//layerpane会把contentpane覆盖掉
    }

    //绘制游戏区
    public void setGamePanel()
    {
        gamePanel = new GamePanel(gameData);//传入游戏数据
        mainPanel.add(gamePanel);//在mainPanel上加上游戏区
    }
    //绘制得分和提示区
    private void setScoreNextPanel()
    {
        scoreNextPanel = new ScoreNextPanel(gameData);
        mainPanel.add(scoreNextPanel);
    }
    //设置玩家排名界面
    private void setPlayerPanel()
    {
        playerPanel = new PlayerPanel(gameData);
        mainPanel.add(playerPanel);
    }
    //设置图层顺序
    private void setPanelOrder()
    {
        mainPanel.setComponentZOrder(staticPanel, 1);//数字越小越在上层
        mainPanel.setComponentZOrder(gamePanel, 0);
        mainPanel.setComponentZOrder(scoreNextPanel, 0);
        //mainPanel.setComponentZOrder(playerPanel, 0);
    }


    //获取操作
    public Operation getOperation(){ return operation;}
    //获取数据
    public GameData getGameData(){ return gameData;}
    //获取游戏区
    public GamePanel getGamePanel()
    {
        return gamePanel;
    }
    //获取得分和提示区
    public ScoreNextPanel getScoreNextPanel()
    {
        return scoreNextPanel;
    }


    //游戏结束时现弹窗
    public void end(String string)
    {
        JOptionPane.showMessageDialog(jFrame,string, "Game Over!" ,1);//在主窗口内显示

    }
}
