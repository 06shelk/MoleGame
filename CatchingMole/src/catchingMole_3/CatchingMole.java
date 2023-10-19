package catchingMole_3;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CatchingMole extends JFrame {
	
	private Image screenImage; //화면이미지
	private Graphics screenGraphic; //컴퓨터의 해상도나 색상에 관한 정보를 보여줌
	
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground.png")).getImage(); //초기 시작 화면을 담는다.
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png"))); //menuBar(메뉴바 이미지를 읽고 처리, Image객체 넘김
	
	private ImageIcon exitbuttonEnteredImage = (new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"))); // 나가기 이미지 클릭
	private ImageIcon exitButtonBasicImage = (new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png")));// 나가기 이미지 기본
	
	private JButton exitbutton = new JButton(exitButtonBasicImage); //나가기 버튼
	
	// 마우스 위치
	private int mouseX, mouseY; //캔버스 (0,0)에 대한 마우스의 현재 수평, 수직 위치 담기
	
	public CatchingMole() {
		setUndecorated(true); // 메뉴바 안보임
		setTitle("두더지 잡기"); // 제목
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 화면 크기
		setResizable(false); // 창 크기 조절x
		setLocationRelativeTo(null); // 화면 정중앙
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 아예 실행x
		setVisible(true); // 눈에 보임
		setBackground(new Color(0, 0, 0, 0)); //배경색
		setLayout(null); // 생각하는 그 위치에 넣어짐
	
		//닫기 버튼
		exitbutton.setBounds(1240, 0, 32, 32); //버튼 위치
		exitbutton.setBorderPainted(false); // 버튼 테두리 설정
		exitbutton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		exitbutton.setFocusPainted(false); //포커스 표시 설정
		exitbutton.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				exitbutton.setIcon(exitbuttonEnteredImage); //마우스 눌렀을 때 이미지
				exitbutton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				exitbutton.setIcon(exitButtonBasicImage); //마우스 기본 이미지로
				exitbutton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
				System.exit(0); // 게임에서 나가짐
			}
		});
		add(exitbutton); //닫기 버튼 설치		
		
		// 메뉴바 위치 모션
		menuBar.setBounds(0, 0, 1280, 32); //메뉴바 위치
		menuBar.addMouseListener(new MouseAdapter() {// 메뉴바 이벤트 
			@Override
			public void mousePressed(MouseEvent e) { //마우스를 눌렀을 때
			mouseX = e.getX(); //마우스 이벤트가 발생한 x좌표를 얻음
			mouseY = e.getY(); //마우스 이벤트가 발생한 y좌표를 얻음
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() { 
			public void mouseDragged(MouseEvent e) { //마우스 드래그를 했을 때
			int x = e.getXOnScreen(); //윈도우 창 내에서의 x 좌표를 x로 지정
			int y = e.getYOnScreen(); //윈도우 창 내에서의 y 좌표를 y로 지정
			setLocation(x - mouseX, y - mouseY); // 좌표값을 구하고 해당 좌표로 컴포넌트의 위치를 조정
			}
		});
		add(menuBar);
		
		
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
		paintComponents(g);//기존 컴포넌트의 모양에 변화를 주고자 할 때
		this.repaint(); // 메소드 호출
		//this 객체, 자기자신
	}
}
