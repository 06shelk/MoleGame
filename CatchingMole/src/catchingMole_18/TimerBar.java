package catchingMole_18;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class TimerBar extends JLabel implements Runnable {
    int width = 50, height = 350;
    int x = 1149, y = 250; // 초기 위치를 위로 올림
    Color color = new Color(255, 0, 0);
    
    private Game game;
    int second;

    public TimerBar(int second) {
        setBackground(color);
        setOpaque(true);
        setBounds(x, y, width, height);
        setBorder(new LineBorder(Color.BLACK, 3));
        this.second = 10; // 타이머를 1분(60초)으로 설정
    }

    @Override
    public void run() {
        while (height > 0) { // 높이가 0보다 큰 동안 반복
            try {
                Thread.sleep(1000 / (350 / second)); // 높이에 따라 타이머 바가 줄어드는 속도를 조절
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (height > 0) {
                y += 1; // y좌표를 1씩 증가시켜 타이머 바를 위로 올림
                height -= 1; // 높이가 1씩 줄어듦
                setBounds(x, y, width, height);
            }
        }
    }
	
}
