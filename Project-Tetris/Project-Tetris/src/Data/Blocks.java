package Data;
import java.awt.*;

//构造方块组
public class Blocks
{
    public Point[] points= new Point[4];

    public Blocks(int[] xs, int[] ys)
    {
        for (int i = 0; i < 4; i++)
        {
            points[i] = new Point(xs[i], ys[i]);
        }
    }

    //复制单个方块
    public Blocks(Blocks blocks)
    {
        for (int i = 0; i < 4; i++)
        {
            points[i] = new Point(blocks.points[i].x, blocks.points[i].y);
        }
    }
}
