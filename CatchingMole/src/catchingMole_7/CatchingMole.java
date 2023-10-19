package catchingMole_7;

import java.awt.Color;
import java.awt.Container;
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
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.LineBorder;


public class CatchingMole extends JFrame {
	
	private Image screenImage; //화면이미지
	private Graphics screenGraphic; //컴퓨터의 해상도나 색상에 관한 정보를 보여줌
	
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground.png")).getImage(); //초기 시작 화면을 담는다.
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png"))); //menuBar(메뉴바 이미지를 읽고 처리, Image객체 넘김
	
	//버튼이미지
	private ImageIcon exitbuttonEnteredImage = (new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"))); // 나가기 이미지 클릭
	private ImageIcon exitButtonBasicImage = (new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png")));// 나가기 이미지 기본
	private ImageIcon startButtonBasicImage = (new ImageIcon(Main.class.getResource("../images/startButtonBasic.png")));  //게임 시작 이미지 기본
	private ImageIcon startButtonEnteredImage = (new ImageIcon(Main.class.getResource("../images/startButtonEntered.png")));// 게임 시작 이미지 클릭
	private ImageIcon moleButtonBasicImage = (new ImageIcon(Main.class.getResource("../images/moleButtonBasic.png")));// 두더지 이미지 기본
	private ImageIcon moleButtonEnteredImage = (new ImageIcon(Main.class.getResource("../images/moleButtonEntered.png")));// 두더지 이미지 클릭
	private ImageIcon mainButtonBasicImage = (new ImageIcon(Main.class.getResource("../images/mainButtonBasic.png")));// 메인화면버튼 이미지 기본
	private ImageIcon mainButtonEnteredImage = (new ImageIcon(Main.class.getResource("../images/mainButtonEntered.png")));// 메인화면버튼 이미지 클릭
	
	//게임 두더지 이미지
	private ImageIcon moleButtonGameDoubleImage = (new ImageIcon(Main.class.getResource("../images/moleButtonGameDouble.png")));// 황금 두더지 이미지 기본
	private ImageIcon moleButtonGameDoubleClickImage = (new ImageIcon(Main.class.getResource("../images/moleButtonGameDoubleClick.png")));// 황금 두더지 이미지 기본
	private ImageIcon moleButtonGameMinusImage = (new ImageIcon(Main.class.getResource("../images/moleButtonGameMinus.png")));// 황금 두더지 이미지 기본
	private ImageIcon moleButtonGameMinusClickImage = (new ImageIcon(Main.class.getResource("../images/moleButtonGameMinusClick.png")));// 황금 두더지 이미지 기본
	
	private Image grassImage = new ImageIcon(Main.class.getResource("../images/grass.png")).getImage(); //잔디라는 이미지를 읽고 처리, Image객체 넘김
	private Image recordImage = new ImageIcon(Main.class.getResource("../images/record.png")).getImage(); //기록보기 이미지를 읽고 처리, Image객체 넘김
	
	
	//JButton
	private JButton exitbutton = new JButton(exitButtonBasicImage); //나가기 버튼
	private JButton startButton = new JButton(startButtonBasicImage); //시작 버튼
	private JButton moleButton = new JButton(moleButtonBasicImage); //두더지 버튼
	private JButton moleGameBasicButton = new JButton(moleButtonBasicImage); //기본 두더지 버튼
	private JButton moleGameDoubleButton = new JButton(moleButtonGameDoubleImage); //황금 두더지 버튼
	private JButton moleGameMinusButton = new JButton(moleButtonGameMinusImage); //벌칙 두더지 버튼
	private JButton mainButtonBasicButton = new JButton(mainButtonBasicImage); //시작 버튼
	
	private JCheckBox bgmCheck = new JCheckBox("BGM");
	
	// 마우스 위치
	private int mouseX, mouseY; //캔버스 (0,0)에 대한 마우스의 현재 수평, 수직 위치 담기
	
	private boolean grassImageHidden = false;
	private boolean main = false;
	
	
	private boolean gameStart = false;
	
	private JLabel jLscore;  // 상대방 목숨
	
	private int score = 0;
	
	private JProgressBar timerBar; // 타이머 바
	private Timer gameTimer; // 게임 타이머
	
	

	
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
		
		main = true;
		
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
				Music buttonEnteredMusic = new Music("buttonClick.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				}catch (InterruptedException ex) {
					ex.printStackTrace();
				}
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
				Music buttonEnteredMusic = new Music("buttonClick.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(700);
				}catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				
				//게임화면으로 이동
				startButton.setVisible(false);
				moleButton.setVisible(false);
				background = new ImageIcon(Main.class.getResource("../images/mainBackgound.png")).getImage(); //초기 시작 화면을 담는다.
				 // 버튼을 누를 때 grassImage 숨기기
				grassImageHidden = true;
				gameStart = true;
				logic();
				
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
			@Override
			public void mousePressed(MouseEvent e) { //마우스가 클릭했을 때
				Music buttonEnteredMusic = new Music("buttonClick.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(700);
				}catch (InterruptedException ex) {
					ex.printStackTrace();
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
	    
	    //메인화면 버튼
    	mainButtonBasicButton.setVisible(false);
    	mainButtonBasicButton.setBounds(1050, 630, 176, 60); // 위치와 크기 조정
    	mainButtonBasicButton.setBorderPainted(false); // 버튼 테두리 설정 
    	mainButtonBasicButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
    	mainButtonBasicButton.setFocusPainted(false); //포커스 표시 설정
    	mainButtonBasicButton.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				mainButtonBasicButton.setIcon(mainButtonEnteredImage); //마우스 눌렀을 때 이미지
				mainButtonBasicButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				mainButtonBasicButton.setIcon(mainButtonBasicImage); //마우스 기본 이미지로
				mainButtonBasicButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
				background = new ImageIcon(Main.class.getResource("../images/introBackground.png")).getImage(); //초기 시작 화면을 담는다.
				main = true;
				moleButton.setVisible(true);
				bgmCheck.setVisible(true);
				startButton.setVisible(true);
				startButton.setVisible(true);
				mainButtonBasicButton.setVisible(false);
			}
		});
		add(mainButtonBasicButton); //닫기 버튼 설치		
	    
		//두더지 버튼
		moleGameBasicButton.setVisible(false);
		moleGameBasicButton.setBounds(230, 110, 90, 120); //버튼 위치
		moleGameBasicButton.setBorderPainted(false); // 버튼 테두리 설정 
		moleGameBasicButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		moleGameBasicButton.setFocusPainted(false); //포커스 표시 설정
		moleGameBasicButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				moleGameBasicButton.setIcon(moleButtonEnteredImage); //마우스 기본 이미지로
				moleGameBasicButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스 손모양으로 바뀜		 
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				moleGameBasicButton.setIcon(moleButtonBasicImage); //마우스 기본 이미지로
			}
			@Override
			public void mousePressed(MouseEvent e) { //마우스가 클릭했을 때
				score = score+2; // 점수 증가
				jLscore.setText(Integer.toString(score)); // 화면에 점수 표시 갱신
				Music buttonEnteredMusic = new Music("toyHammer.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(300);
				}catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				
			}
		});
		add(moleGameBasicButton); // 시작 버튼 설치
				
				
		//황금 두더지 버튼
		moleGameDoubleButton.setVisible(false);
		moleGameDoubleButton.setBounds(560, 110, 90, 120); //버튼 위치
		moleGameDoubleButton.setBorderPainted(false); // 버튼 테두리 설정 
		moleGameDoubleButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		moleGameDoubleButton.setFocusPainted(false); //포커스 표시 설정
		moleGameDoubleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				moleGameDoubleButton.setIcon(moleButtonGameDoubleClickImage); //마우스 오버 이미지로
				moleGameDoubleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스 손모양으로 바뀜		 
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				moleGameDoubleButton.setIcon(moleButtonGameDoubleImage); //마우스 기본 이미지로
			}
			@Override
			public void mousePressed(MouseEvent e) { //마우스가 클릭했을 때
				score = score+4; // 점수 증가
				jLscore.setText(Integer.toString(score)); // 화면에 점수 표시 갱신
				Music buttonEnteredMusic = new Music("toyHammer.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(300);
				}catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		});
		add(moleGameDoubleButton); // 시작 버튼 설치
				
					
		//벌칙 두더지 버튼
		moleGameMinusButton.setVisible(false);
		moleGameMinusButton.setBounds(880, 110, 90, 120); //버튼 위치
		moleGameMinusButton.setBorderPainted(false); // 버튼 테두리 설정 
		moleGameMinusButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		moleGameMinusButton.setFocusPainted(false); //포커스 표시 설정
		moleGameMinusButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				moleGameMinusButton.setIcon(moleButtonGameMinusClickImage); //마우스 오버 이미지로
				moleGameMinusButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스 손모양으로 바뀜		 
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				moleGameMinusButton.setIcon(moleButtonGameMinusImage); //마우스 기본 이미지로
			}
			@Override
			public void mousePressed(MouseEvent e) { //마우스가 클릭했을 때
				score = score - 6; // 점수 증가
				jLscore.setText(Integer.toString(score)); // 화면에 점수 표시 갱신
				Music buttonEnteredMusic = new Music("toyHammer.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(300);
				}catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		});
		add(moleGameMinusButton); // 시작 버튼 설치			
		
		
		
		
	}//CatchingMole()
	
	public void paint(Graphics g) { 
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 정해놨던 width값 height값을 screenImage에 넣음
		screenGraphic = screenImage.getGraphics(); //graphics 객체로 반환
		// graphics 어떤 컴퍼넌트에 그리기 위한 도구나 정보를 담고 있는 추상적인 클래스
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	
		if (main) { // grassImageHidden 변수로 이미지 숨김 여부 판단
	        g.drawImage(grassImage, 910, 520, null);
	    }
		
		if (gameStart) {
	        int[][] grassPositions = { 	//잔디 위치를 정리
	            {200, 200}, {530, 200}, {850, 200},
	            {200, 380}, {530, 380}, {850, 380},
	            {200, 550}, {530, 550}, {850, 550}
	        };
	        
	        for (int[] pos : grassPositions) {
	            g.drawImage(grassImage, pos[0], pos[1], null);
	        }
	    }
	}
	
	public void logic() {
		main = false;
		score = 0;
		moleGameMinusButton.setVisible(true);
		moleGameDoubleButton.setVisible(true);
		moleGameBasicButton.setVisible(true);
		
		Music introMusic = new Music("backgroundMusic1.mp3", true);
		introMusic.start();
		
		//점수판
		Container c = getContentPane(); // c 라는 컨테이너 생성 
		c.setLayout(null); // 동,서,남,북,중앙으로 나누는 배치관리자
		jLscore = new JLabel(Integer.toString(score)); // 상대방
		jLscore.setFont(new Font("나눔고딕",Font.BOLD,30));
		jLscore.setBounds(1150, 100, 100, 200); 
		c.add(jLscore);
		
		// 타이머
		 timerBar = new JProgressBar(JProgressBar.VERTICAL); // 세로 방향으로 설정
		 timerBar.setBounds(1149, 250, 20, 300); // 위치와 크기 조정
		 timerBar.setForeground(Color.RED); // 원하는 색상으로 변경
		 timerBar.setBorder(new LineBorder(Color.BLACK, 3)); // 테두리 색상과 굵기 설정
		 timerBar.setValue(100); // 초기 값 설정 (100%로 시작)
		 timerBar.setStringPainted(false); // 백분율 표시 비활성화
		 add(timerBar); // 프레임에 타이머 바 추가
		
		// 타이머 설정
	    gameTimer = new Timer(1000, e -> {
	        int currentValue = timerBar.getValue();
	        if (currentValue > 0) {
	            timerBar.setValue(currentValue - 1); // 매 초마다 10%씩 감소
	        } else {
	            // 타이머가 0에 도달하면 게임 종료 로직을 추가할 수 있습니다.
	            // 예를 들어, 게임 종료 메시지를 표시하거나 점수 계산을 마무리할 수 있습니다.
	        	background = new ImageIcon(Main.class.getResource("../images/resultBackground.png")).getImage(); //초기 시작 화면을 담는다.
	        	gameStart = false;
	        	moleGameMinusButton.setVisible(false);
	        	moleGameDoubleButton.setVisible(false);
	        	moleGameBasicButton.setVisible(false);
	        	timerBar.setVisible(false);
	        	jLscore.setVisible(false);
	        	bgmCheck.setVisible(false);
	        	mainButtonBasicButton.setVisible(true);
	        	
	        	gameTimer.stop();
	            // 여기에 게임 종료 처리를 추가하세요.
	        }
	    });
	    gameTimer.start(); // 타이머 시작
		
		
		// 타이머를 리셋하거나 초기화하는 작업이 필요한 경우에 이 곳에 추가합니다.
	    timerBar.setValue(5); // 타이머 바를 다시 100%로 설정
	    
	    gameTimer.restart(); // 타이머를 재시작하여 게임 시간을 다시 측정합니다.
	}
	
	
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null); // 초기배경이미지를 넣음
		paintComponents(g);//기존 컴포넌트의 모양에 변화를 주고자 할 때
		this.repaint(); // 메소드 호출
		//this 객체, 자기자신
	}
}
