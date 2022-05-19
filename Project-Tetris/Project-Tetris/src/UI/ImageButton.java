package UI;
import javax.swing.*;

//有图像的按钮
public class ImageButton extends JButton
{
     public ImageButton(ImageIcon image)
    {
        setContentAreaFilled(false);//设置为背景透明
        setFocusPainted(false);//中间点击框隐藏
        setIcon(image);//更改图片
        //取消截获按键
        setFocusable(false);
        //setBorder(null);//去除边框
    }
}
