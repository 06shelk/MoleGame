package catchingMole_1;

import javax.swing.JFrame;

public class CatchingMole extends JFrame {
	
	public CatchingMole() {
		setTitle("두더지 잡기"); //제목
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);//게임창 크기
		setResizable(false); // 사용자가 화면의 크기를 바꿀 수 없음
		setLocationRelativeTo(null); // 게임창의 정가운데로 감
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //게임창을 종료했을 때 프로그램 종료
		setVisible(true); //화면이 눈에 보임
	}
}
