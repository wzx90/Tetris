package UI;
import Data.GameData;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

public class PlayerPanel extends JPanel
{
    static int ROOT_X1 = 25;
    static int ROOT_X2 = 100;
    static int ROOT_Y = 15;
    static int FOUT_SIZE = 20;
    GameData gameData;

    PlayerPanel(GameData gameData)
    {
        this.gameData = gameData;
        setOpaque(false);
        setBounds(15, 440, 200, 170);

    }

   /* @Override
    public void paintComponent(Graphics g){
        g.setFont(new Font("黑体", Font.PLAIN, 30));
        g.setColor(new Color(80, 80, 80));
        g.drawString(""+gameData.getScore(), 245, 88);

        int i = 0;
        g.setFont(new Font("黑体", Font.PLAIN, 15));
        for(String name: gameData.getPlayersName())
        {
            g.drawString(name, ROOT_X1, ROOT_Y + i*FOUT_SIZE);
                i++;
        }
        i = 0;
        for(int score: gameData.getPlayerScore())
        {
            g.drawString("....  " + score, ROOT_X2, ROOT_Y + i*FOUT_SIZE);
            i++;
        }
    }*/
}

