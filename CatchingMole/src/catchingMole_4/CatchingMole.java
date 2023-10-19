package catchingMole_4;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CatchingMole extends JFrame {
	
	private Image screenImage; //화면이미지
	private Graphics screenGraphic; //컴퓨터의 해상도나 색상에 관한 정보를 보여줌
	
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground.png")).getImage(); //초기 시작 화면을 담는다.
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png"))); //menuBar(메뉴바 이미지를 읽고 처리, Image객체 넘김
	
	private ImageIcon exitbuttonEnteredImage = (new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"))); // 나가기 이미지 클릭
	private ImageIcon exitButtonBasicImage = (new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png")));// 나가기 이미지 기본
	private ImageIcon startButtonBasicImage = (new ImageIcon(Main.class.getResource("../images/startButtonBasic.png")));  //게임 시작 이미지 기본
	private ImageIcon startButtonEnteredImage = (new ImageIcon(Main.class.getResource("../images/startButtonEntered.png")));// 게임 시작 이미지 클릭
	private ImageIcon moleButtonBasicImage = (new ImageIcon(Main.class.getResource("../images/moleButtonBasic.png")));// 두더지 이미지 기본
	private ImageIcon moleButtonEnteredImage = (new ImageIcon(Main.class.getResource("../images/moleButtonEntered.png")));// 두더지 이미지 기본
	private Image grassImage = new ImageIcon(Main.class.getResource("../images/grass.png")).getImage(); //잔디라는 이미지를 읽고 처리, Image객체 넘김
	private Image recordImage = new ImageIcon(Main.class.getResource("../images/record.png")).getImage(); //기록보기 이미지를 읽고 처리, Image객체 넘김
	
	private JButton exitbutton = new JButton(exitButtonBasicImage); //나가기 버튼
	private JButton startButton = new JButton(startButtonBasicImage); //시작 버튼
	private JButton moleButton = new JButton(moleButtonBasicImage); //시작 버튼
	
	private JCheckBox bgmCheck = new JCheckBox("BGM");
	
	// 마우스 위치
	private int mouseX, mouseY; //캔버스 (0,0)에 대한 마우스의 현재 수평, 수직 위치 담기
	
	private boolean grassImageHidden = false;
	
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
		
		startButton.setBounds(485, 450, 310, 110); //버튼 위치
		startButton.setBorderPainted(false); // 버튼 테두리 설정 
		startButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		startButton.setFocusPainted(false); //포커스 표시 설정
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				startButton.setIcon(startButtonEnteredImage);//마우스 눌렀을 때 이미지
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스 손모양으로 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				startButton.setIcon(startButtonBasicImage); //마우스 기본 이미지로
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) { //마우스가 클릭했을 때
				startButton.setVisible(false);
				moleButton.setVisible(false);
				bgmCheck.setVisible(false);
				background = new ImageIcon(Main.class.getResource("../images/mainBackgound.png")).getImage(); //초기 시작 화면을 담는다.
				 // 버튼을 누를 때 grassImage 숨기기
				grassImageHidden = true;
			}
		});
		add(startButton); // 시작 버튼 설치
		
		//두더지 버튼
		moleButton.setBounds(940, 440, 90, 120); //버튼 위치
		moleButton.setBorderPainted(false); // 버튼 테두리 설정 
		moleButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		moleButton.setFocusPainted(false); //포커스 표시 설정
		moleButton.addMouseListener(new MouseAdapter() {
			private JLabel imageLabel; // 이미지를 표시할 JLabel
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				moleButton.setIcon(moleButtonEnteredImage); //마우스 기본 이미지로
				moleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스 손모양으로 바뀜
				 if (imageLabel == null) {
			            // 이미지를 표시할 JLabel 생성 및 설정
			            imageLabel = new JLabel(new ImageIcon(recordImage));
			            imageLabel.setBounds(860, 300, recordImage.getWidth(null), recordImage.getHeight(null));
			            add(imageLabel);
			            revalidate(); // 컴포넌트 갱신
			            repaint();    // 컴포넌트 다시 그리기
			        }
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				moleButton.setIcon(moleButtonBasicImage); //마우스 기본 이미지로
				moleButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
				 if (imageLabel != null) {
			            remove(imageLabel); // 이미지 JLabel 삭제
			            revalidate();
			            repaint();
			            imageLabel = null; // 다음에 마우스가 들어왔을 때 새로 생성
			        }
			}
		});
		add(moleButton); // 시작 버튼 설치
		
		//
		
		//BGM 체크박스
		bgmCheck.setBounds(950, 600, 150, 150);
		bgmCheck.setFont(new Font("Arial", Font.BOLD, 24)); // 글꼴 설정
		bgmCheck.setBackground(new Color(175, 212, 133)); // 원하는 배경색상으로 변경
		bgmCheck.setFocusPainted(false);
	    add(bgmCheck);
		
		
	}//CatchingMole()
	
	public void paint(Graphics g) { 
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 정해놨던 width값 height값을 screenImage에 넣음
		screenGraphic = screenImage.getGraphics(); //graphics 객체로 반환
		// graphics 어떤 컴퍼넌트에 그리기 위한 도구나 정보를 담고 있는 추상적인 클래스
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	
		if (!grassImageHidden) { // grassImageHidden 변수로 이미지 숨김 여부 판단
	        g.drawImage(grassImage, 910, 520, null);
	    }
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null); // 초기배경이미지를 넣음
		paintComponents(g);//기존 컴포넌트의 모양에 변화를 주고자 할 때
		this.repaint(); // 메소드 호출
		//this 객체, 자기자신
	}
}
