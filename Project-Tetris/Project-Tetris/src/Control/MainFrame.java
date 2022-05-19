package Control;
import Data.GameData;
import UI.MainWindow;

import javax.swing.*;

//控制层与层之间的关系
public class MainFrame
{
    public MainFrame()
    {
        //实例化操作
        Operation operation = new Operation();
        //加载数据
        GameData gameData = new GameData();
        //将数据和窗口进行关联
        MainWindow mainWindow = new MainWindow(operation, gameData);
        //将窗口和操作进行关联，用键盘操作
        operation.setWindow(mainWindow);
        //将数据和操作进行关联
        operation.setData(gameData);
        //启用线程
        new AutoDown(gameData, mainWindow).start();

        mainWindow.jFrame.setVisible(true);//显示窗口


    }
}

//方块自动下落
class AutoDown extends Thread//继承一个线程
{
    private GameData gameData;
    private MainWindow mainWindow;
    AutoDown(GameData gameData, MainWindow mainWindow)
    {
        this.gameData = gameData;
        this.mainWindow = mainWindow;
    }
    public void run() {
        while (true) {
            try {
                if (gameData.state == 1)
                {
                    if (gameData.move(false, 1))
                    {
                        mainWindow.getScoreNextPanel().repaint();//刷新得分和提示区
                    }
                    mainWindow.getGamePanel().repaint();//刷新游戏区
                    sleep(200);
                }
                else if (gameData.state == 3)
                {
                    mainWindow.end("Your score is : " + gameData.getScore() + " points ! ");
                    gameData.initialGameData();
                    mainWindow.getOperation().button1.setText(gameData.button1Choices[gameData.state]);
                    gameData.state = 0;
                }
                else if (gameData.state == 4)
                {

                }
                else { sleep(150);}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


