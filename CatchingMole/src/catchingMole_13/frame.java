package catchingMole_13;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

public class frame extends JFrame { //화면에 대한 정의

	public frame() {
		int frame_width = 1280;
		int frame_height = 720;
		
		setUndecorated(true); // 메뉴바 안보임
		setTitle("두더지 잡기"); // 제목
		setSize(frame_width, frame_height); // 화면 크기
		setResizable(false); // 창 크기 조절x
		setLocationRelativeTo(null); // 화면 정중앙
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 아예 실행x
		setVisible(true); // 눈에 보임
		setBackground(new Color(0, 0, 0, 0)); //배경색
		setLayout(null); // 생각하는 그 위치에 넣어짐
		
		Image screenImage; //화면이미지
		Graphics screenGraphic; //컴퓨터의 해상도나 색상에 관한 정보를 보여줌
	}

}
