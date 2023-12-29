package org.csu.mypetstore.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class VerifyUtil {
    private static final String codeChars="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String codeNum="0123456789";
    public static String getVerifyCode(){
        int width=60;
        int height=20;
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<4;i++){
            int rand=(int) (Math.random() *36);
            sb.append(codeChars.charAt(rand));
        }
        return sb.toString();
    }
public static String getVerifyCodeNumber(){
        int width=60;
        int height=20;
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<4;i++){
            int rand=(int) (Math.random() *10);
            sb.append(codeNum.charAt(rand));
        }
        return sb.toString();
    }
    public static BufferedImage getCodeImage(String code){
        int width=60;
        int height=20;
        //创建内存图像并获得图形上下文
        BufferedImage image=new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics g=image.getGraphics();
        /*
         * 产生图像
         * 画背景
         */
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);

        /*
         * 随机产生120个干扰点
         */

        for(int i=0;i<120;i++){
            int x=(int)(Math.random()*width);
            int y=(int)(Math.random()*height);
            int red=(int)(Math.random()*255);
            int green=(int)(Math.random()*255);
            int blue=(int)(Math.random()*255);
            g.setColor(new Color(red,green,blue));
            g.drawOval(x, y, 1, 0);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.ITALIC|Font.BOLD,18));

        //在不同高度输出验证码的不同字符
        g.drawString(""+code.charAt(0), 1, 17);
        g.drawString(""+code.charAt(1), 16, 15);
        g.drawString(""+code.charAt(2), 31, 18);
        g.drawString(""+code.charAt(3), 46, 16);
        g.dispose();
        return image;
    }
}
