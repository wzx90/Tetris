package UI;
import Data.GameData;
import javax.swing.*;
import java.awt.*;

//游戏界面
public class GamePanel extends JPanel {
    GameData gameData;

    public GamePanel(GameData gameData) {
        this.gameData = gameData;
        setOpaque(false);//设置画布透明
        setBounds(15, 20, 200, 360);
    }

    //重载方法，自动执行
    public void paintComponent(Graphics graphics) {
        //循环画出四个方块形成方块组
        for (Point point : gameData.blocks.points) {
            graphics.setColor(gameData.colors[gameData.current]);
            graphics.fill3DRect((point.x + gameData.x) * 10, (point.y + gameData.y) * 10, 10, 10, false);
            graphics.drawImage(new ImageIcon("Project-Tetris\\Image\\mask1.png").getImage(),
                    (point.x + gameData.x) * 10, (point.y + gameData.y) * 10, 10, 10, null);
        }

        //画保存的方块组
        //从底部开始画
        for (int i = 37; i >= 2; i--) {
            for (int j = 0; j < 20; j++) {
                if (gameData.existBlocks[j][i] != 0) {
                    graphics.setColor(gameData.colors[gameData.existBlocks[j][i] - 1]);
                    graphics.fill3DRect(j * 10, (i - 2) * 10, 10, 10, false);//可以让单个方块显现
                    graphics.drawImage(new ImageIcon("Project-Tetris\\Image\\mask0.png").getImage(),
                            j * 10, (i - 2) * 10, 10, 10, null);
                }
            }
        }
    }
}


