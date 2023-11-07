package catchingMole_15;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame{
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    Image screenImage;
    Graphics screenGraphics;
    JLabel background;

    public Frame() { // 화면 정보
        setSize(1280, 720); // 창 크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫을 때 프로그램 종료
        setTitle("두더지 잡기");
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        background=null;//배경이미지
        
    }
    public void paint(Graphics g) {//그리는 함수
        screenImage = createImage(Frame.WIDTH, Frame.HEIGHT);
        screenGraphics = screenImage.getGraphics();
        screenDraw(screenGraphics);
        g.drawImage(screenImage, 0, 0, null);//background를 그려줌
    }

    public void screenDraw(Graphics g) {
    	paintComponents(g);
        this.repaint();
    }

}