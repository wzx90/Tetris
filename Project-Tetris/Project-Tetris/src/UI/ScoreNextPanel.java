package UI;
import Data.GameData;
import javax.swing.*;
import java.awt.*;

public class ScoreNextPanel extends JPanel {
    GameData gameData;
    int[] offsetx = new int[]{ 0, 0, 10, 0, 0, 0, 7};//七个方块组的横向偏移量

    public ScoreNextPanel(GameData gameData) {
        this.gameData = gameData;
        setOpaque(false);//设置画布透明
        setBounds(245, 30, 80, 245);
    }

    //重载方法，自动执行
    public void paintComponent(Graphics graphics) {
        //显示得分
        graphics.setFont(new Font("Times New Roman", Font.BOLD, 40));
        if (Integer.parseInt(gameData.getScore()) == 0)
        {
            graphics.drawString(gameData.getScore(), 24, 60);
        }
        if (Integer.parseInt(gameData.getScore()) > 0 && Integer.parseInt(gameData.getScore()) < 100)
        {
            graphics.drawString(gameData.getScore(), 15, 60);
        }
        if (Integer.parseInt(gameData.getScore()) >= 100)
        {
            graphics.drawString(gameData.getScore(), 5, 60);
        }

        //循环画出四个方块形成方块组
        for (Point point : gameData.BLOCKS[gameData.next].points) {
            graphics.setColor(gameData.colors[gameData.next]);
            //相较于panel左上角的坐标
            graphics.fill3DRect(point.x * 15 + 22 + offsetx[gameData.next], point.y * 15 + 150, 15, 15, false);
            graphics.drawImage(new ImageIcon("Project-Tetris\\Image\\mask1.png").getImage(),
                    point.x * 15 + 22 + offsetx[gameData.next], point.y * 15 + 150, 15, 15,  null);



        }

    }
}


