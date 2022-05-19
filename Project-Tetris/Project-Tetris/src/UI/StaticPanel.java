package UI;
import Control.Operation;
import javax.swing.*;
import java.awt.*;

//静态界面
public class StaticPanel extends JPanel
{
    ImageButton left;
    ImageButton right;
    ImageButton rotate;
    ImageButton down;
    JButton button1;
    JButton save;
    JButton exit;

    public StaticPanel(Operation operation)
    {
        setBounds(0, 0, 360, 640);
        setLayout(null);//自由布局
        setOpaque(false);//取消由父类绘制所覆盖的画布
        paintButton(operation);
        //让按键输入到mainwindow，而不是按钮或者panel
        setFocusable(false);
    }

    //绘制，输入参数类型Graphics是画笔，这是一个重载方法
    //父类JPanel的构造函数中已经调用了这个方法，不用再到子类构造函数调用！！！
    public void paintComponent(Graphics graphics)
    {
        //继承父类绘制，必须要有，但是会再画布上覆盖一层，把背景图片覆盖掉，需要再构造函数中修改
        super.paintComponent(graphics);

        graphics.setColor(new Color(0, 0, 0, 30));//RGB 透明度0-255
        //游戏区
        graphics.fillRect(15, 20,200, 360 );
        //左下排名区
        graphics.fillRect(15, 405, 200, 170);
        //右侧整体排版
        graphics.fillRect(225, 20, 110, 500);
        graphics.setColor(new Color(2, 2, 2, 30));
        graphics.fillRect(235, 30, 90, 70 );
        graphics.fillRect(235, 110, 90, 135);
        graphics.fillRect(235, 255, 90, 90);
        graphics.fillRect(235, 355, 90, 45);
        graphics.fillRect(235, 410, 90, 45);
        graphics.fillRect(235, 465, 90, 45);
        //边框
        graphics.setColor(Color.white);
        ((Graphics2D)graphics).setStroke(new BasicStroke(3l));//将画笔大小设置为3个像素，一定要加l，是浮点数
        graphics.drawRect(13, 18, 204, 364);
        graphics.drawRect(13, 403, 204, 174);
        //加入文字
        graphics.setFont(new Font("Times New Roman", Font.BOLD, 20));
        graphics.drawString("Score", 240, 45);
        graphics.drawString("Next", 240, 127);
        graphics.drawString("Block", 240, 147);
        graphics.drawString("Rank", 25, 430);
    }

    //添加按钮到静态界面上
    public void paintButton(Operation operation)
    {
        //让静态界面上的按钮与操作里的按钮相同
        left = operation.left;
        right = operation.right;
        rotate = operation.rotate;
        down = operation.down;
        button1 = operation.button1;
        save = operation.save;
        exit = operation.exit;

        left.setBounds(235, 255,45, 45);
        right.setBounds(280, 255, 45,45);
        rotate.setBounds(235, 300, 45, 45 );
        down.setBounds(280, 300, 45, 45 );
        button1.setBounds(235, 355, 90, 45);
        save.setBounds(235, 410, 90, 45);
        exit.setBounds(235, 465, 90, 45);

        button1.setContentAreaFilled(false);
        button1.setFocusPainted(false);
        save.setContentAreaFilled(false);
        save.setFocusPainted(false);
        exit.setContentAreaFilled(false);
        exit.setFocusPainted(false);
        add(left); add(right); add(rotate); add(down);
        add(button1); add(save); add(exit);
    }
}
