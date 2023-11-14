package catchingMole_17;

import java.awt.Font;
import javax.swing.JLabel;

class TimerNum extends JLabel implements Runnable {
    private Thread introMusic;
    private int width = 75, height = 75;
    private int x = 1137, y = 585;
    private int second;
    private boolean isRunning;  // 추가: 타이머가 실행 중인지 여부
    private Game game;

    public TimerNum(int second, Game game) {
        this.game = game;

        setBounds(x, y, width, height);
        setText(second + "");
        setFont(new Font("맑은고딕", Font.PLAIN, 20));
        setHorizontalAlignment(JLabel.CENTER);

        this.second = second;
        isRunning = true; // 추가: 초기에는 타이머가 실행 중
    }

    @Override
    public void run() {
        while (isRunning) {  // 변경: isRunning이 true일 때만 실행
            try {
                Thread.sleep(1000); // 1초
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (second > 0) {
                second -= 1;
                setText(second + "");
            } else {
            	stopTimer();// 시간이 다 된 경우 타이머를 멈추는 메소드 호출
                game.nextScreen();
                 
            }
        }
    }

    // 추가: 타이머를 멈추는 메소드
    public void stopTimer() {
        isRunning = false;
    }
}
