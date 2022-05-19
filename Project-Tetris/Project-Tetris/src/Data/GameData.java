package Data;
import UI.MainWindow;
import java.awt.*;
import java.util.Random;

public class GameData
{
    //创建7种方块组组合
    public static Blocks[] BLOCKS = new Blocks[]
    {
         new Blocks(new int[]{0,0,0,1}, new int[]{1,0,-1,-1}),//画图的时候向右和向下为正！
         new Blocks(new int[]{0,0,1,1}, new int[]{1,0,0,-1}),
         new Blocks(new int[]{-1,0,0,0}, new int[]{-1,-1,0,1}),
         new Blocks(new int[]{0,0,1,1}, new int[]{-1,0,0,1}),
         new Blocks(new int[]{-1,0,1,2}, new int[]{0,0,0,0}),
         new Blocks(new int[]{0,0,1,1}, new int[]{0,1,0,1}),
         new Blocks(new int[]{-1,0,0,1}, new int[]{0,0,-1,0}),
    };
    //颜色数组给方块组上色
    public Color[] colors = new Color[]
    {
        new Color(250,0,0,100),
        new Color(0,250,0,100),
        new Color(0,0,250,100),
        new Color(250,250,0,100),
        new Color(250,0,250,100),
        new Color(0,250,250,100),
        new Color(150,150,150,100),
    };
    //创建在游戏区的方块组
    public Blocks blocks;
    //x，y是旋转中心像素点位置
    public int x;
    public int y;
    //存放在保留的方块组的位置的数组
    public int[][] existBlocks;
    //存放要消去的行的index
    int[] deleteNum;
    //随机因子
    Random random;
    //当前方块组颜色选择
    public int current;
    //下一个方块组提示
    public int next;
    //得分
    public int score;
    //游戏状态
    public int state;
    //start pause save exit 的button1按钮信息
    public String[] button1Choices = new String[]{"Start", "Pause", "Continue", "Restart" };
    //玩家的数据
    public PlayerData playerData;

    public GameData()
    {
        initialGameData();

    }

    //重置游戏数据
    public void initialGameData()
    {
        existBlocks = new int[20][38];//36+2=38
        random = new Random();
        next = random.nextInt(7);//生成[0,7)随机整数
        initialBlocks();
        score = 0;
    }

    //重置下落方块组
    private void initialBlocks()
    {
        x = 9;
        y = -2;//起始位置
        deleteNum = new int[38];
        blocks = new Blocks(BLOCKS[next]);
        current = next;
        next = random.nextInt(7);//生成[0,7)随机整数
    }

    //左右下移动方法
    public boolean move(boolean isHorizental, int step)
    {
        boolean isMove = true;
        if (isHorizental) {
            for (Point point : blocks.points)
            {
                if (x + point.x + step < 0 || x + point.x + step > 19
                        || y + point.y + 2 < 0 || y + point.y + 2 > 37
                        || existBlocks[x + point.x + step][y + point.y + 2] != 0)
                {
                    return false;
                }
            }
            x += step;
            return false;//true表示方块组停止运动
        }
        else
        {
            for (Point point : blocks.points)
            {
                //如果方块组碰到底或者下面有方块，则停住保存方块组
                if (point.y + y + step > 35 || existBlocks[x + point.x][y + point.y + 2 + step] != 0)//一定要加step！
                {
                    saveBlocks();
                    boolean isDelete = deleteLineTest();
                    if(isDelete)
                    {
                        deleteLine();
                    }
                    if (isOver())
                    {
                        state = 3;
                    }
                    initialBlocks();//重置下落方格组
                    return true;
                }
            }
            y += step;
            return false;
        }
    }

    //顺时针旋转方法
    public void rotate()
    {
        boolean isRotate = true;
        for (Point point : blocks.points)
        {
            int x1 = x - point.y;
            int y1 = y + point.x;
            if (x1 < 0 || x1 > 19 || y1 > 35 || y1 < -2 ){ isRotate = false; break;}
            if(existBlocks[x1][y1 + 2] != 0) { isRotate = false;}
            if (current == 5){ isRotate = false;}//正方形不旋转
        }
        if(isRotate)
        {
            for (Point point : blocks.points)
            {
                int temp = point.x;
                point.x = -point.y;
                point.y = temp;
            }
        }
    }

    //保存方块组
    public void saveBlocks()
    {
        for (Point point : blocks.points)
        {
            existBlocks[x + point.x][y + point.y + 2] = current + 1;
        }
    }

    //消行检测
    public boolean deleteLineTest()
    {
        boolean isDelete = false;
        boolean isExistEmpty;
        for (int i = 37; i >= 2; i--)
        {
            isExistEmpty = false;
            for (int j = 0; j < 20; j++)
            {
                if (existBlocks[j][i] == 0)
                {
                    isExistEmpty = true;
                    break;
                }
            }
            if (!isExistEmpty)
            {
                isDelete = true;
                deleteNum[i - 1] = deleteNum[i] + 1;
            }
            else
            {
                deleteNum[i - 1] = deleteNum[i];
            }
        }
        return isDelete;
    }

    //消行和得分增加
    public void deleteLine()
    {
        for (int i = 37; i >= 2; i--)
        {
            for (int j = 0; j < 20; j++)
            {
                existBlocks[j][i + deleteNum[i]] = existBlocks[j][i];
            }
        }
        score += deleteNum[2] * 10;//得分等于消除的行数*10
    }

    //游戏结束判断
    public boolean isOver()
    {
        for (int j = 0; j < 20; j++)
        {
            if (existBlocks[j][2] != 0)
            {
                return true;
            }
        }
        return false;
    }

    //得分
    public String getScore()
    {
        return score + "";
    }
}
