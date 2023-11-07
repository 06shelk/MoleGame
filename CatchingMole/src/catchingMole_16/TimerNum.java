package catchingMole_16;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

class TimerNum extends JLabel implements Runnable {
	Thread introMusic;
	int width = 75, height = 75;
	int x = 1137, y = 585;
	
	int second;
	private Game game;
	 
	public TimerNum(int second, Game game) {
		this.game = game;
		
		setBounds(x, y, width, height);
		setText(second + "");
		setFont(new Font("맑은고딕", Font.PLAIN, 20));
		setHorizontalAlignment(JLabel.CENTER);
		
		 this.second = 10; // 타이머를 1분(60초)으로 설정
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);	// 1초
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (second > 0) {
				second -= 1;		// 1초씩 줄어듦
				setText(second + "");
			} else {
				game.nextScreen(); // 시간이 다 된 경우 nextScreen 호출
				break;
				
			}
		}
	}
}