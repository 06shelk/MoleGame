package catchingMole_2;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class CatchingMole extends JFrame {
	
	private Image screenImage; //화면이미지
	private Graphics screenGraphic; //컴퓨터의 해상도나 색상에 관한 정보를 보여줌
	
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground.png")).getImage(); //초기 시작 화면을 담는다.
	
	
	public CatchingMole() {
		setTitle("두더지 잡기"); //제목
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);//게임창 크기
		setResizable(false); // 사용자가 화면의 크기를 바꿀 수 없음
		setLocationRelativeTo(null); // 게임창의 정가운데로 감
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //게임창을 종료했을 때 프로그램 종료
		setVisible(true); //화면이 눈에 보임
	}
	
	public void paint(Graphics g) { 
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 정해놨던 width값 height값을 screenImage에 넣음
		screenGraphic = screenImage.getGraphics(); //graphics 객체로 반환
		// graphics 어떤 컴퍼넌트에 그리기 위한 도구나 정보를 담고 있는 추상적인 클래스
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null); // 초기배경이미지를 넣음
		this.repaint(); // 메소드 호출
		//this 객체, 자기자신
	}
}
