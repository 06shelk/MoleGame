package catchingMole_12;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import catchingMole_10.Music;


public class CatchingMole extends JFrame {
	
	private Image screenImage; //화면이미지
	private Graphics screenGraphic; //컴퓨터의 해상도나 색상에 관한 정보를 보여줌
	
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground.png")).getImage(); //초기 시작 화면을 담는다.
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png"))); //menuBar(메뉴바 이미지를 읽고 처리, Image객체 넘김
	private JTextArea textArea;
	
	//버튼이미지
	private ImageIcon exitbuttonEnteredImage = (new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"))); // 나가기 이미지 클릭
	private ImageIcon exitButtonBasicImage = (new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png")));// 나가기 이미지 기본
	private ImageIcon startButtonBasicImage = (new ImageIcon(Main.class.getResource("../images/startButtonBasic.png")));  //게임 시작 이미지 기본
	private ImageIcon startButtonEnteredImage = (new ImageIcon(Main.class.getResource("../images/startButtonEntered.png")));// 게임 시작 이미지 클릭
	private ImageIcon moleButtonBasicImage = (new ImageIcon(Main.class.getResource("../images/moleButtonBasic.png")));// 두더지 이미지 기본
	private ImageIcon moleButtonEnteredImage = (new ImageIcon(Main.class.getResource("../images/moleButtonEntered.png")));// 두더지 이미지 클릭
	private ImageIcon mainButtonBasicImage = (new ImageIcon(Main.class.getResource("../images/mainButtonBasic.png")));// 메인화면버튼 이미지 기본
	private ImageIcon mainButtonEnteredImage = (new ImageIcon(Main.class.getResource("../images/mainButtonEntered.png")));// 메인화면버튼 기본 이미지 
	private ImageIcon resetButtonBasicImage = (new ImageIcon(Main.class.getResource("../images/resetButtonBasic.png")));// reset 이미지 클릭
	private ImageIcon resetButtonEnteredImage = (new ImageIcon(Main.class.getResource("../images/resetButtonEntered.png")));// reset 이미지 클릭
	
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
	private JButton mainButtonBasicButton = new JButton(mainButtonBasicImage); //메인화면 버튼
	private JButton resetButton = new JButton(resetButtonBasicImage); //나가기 버튼
	
	private JCheckBox bgmCheck = new JCheckBox("BGM");
	
	// 마우스 위치
	private int mouseX, mouseY; //캔버스 (0,0)에 대한 마우스의 현재 수평, 수직 위치 담기
	
	
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;
	private boolean isResultScreen = false;
	
	int[][] grassPositions = { 	//잔디 위치를 정리
            {200, 200}, {530, 200}, {850, 200},
            {200, 380}, {530, 380}, {850, 380},
            {200, 550}, {530, 550}, {850, 550}
        };
	
	int[][] molePositions = { 	//두더지 위치를 정리
            {230, 110}, {560, 110}, {880, 110},
            {230, 290}, {560, 290}, {880, 290},
            {230, 460}, {560, 460}, {880, 460}
        };
	
	private JLabel jLscore;  //  점수판
	private int score = 0; //점수
	
	private JProgressBar timerBar; // 타이머 바
	private Timer gameTimer; // 게임 타이머
	private JLabel scoreLabel;
	
	private JLabel allScore;  //  기록판
	private ArrayList<String> scoreResults = new ArrayList<>();
	private ArrayList<String> scoreDates = new ArrayList<>();
	private Random random = new Random(); // Random number generator
	private ArrayList<JButton> moleButtons = new ArrayList<>(); // List to store mole buttons
	
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
		
		isMainScreen = true;		
		
		
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
				clickmusic(); // 클릭 버튼 소리 나옴
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
		
		//게임 시작 버튼
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
				clickmusic(); // 클릭 버튼 소리 나옴
				gamescreen(); // 게임화면
				
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
				clickmusic(); // 클릭 버튼 소리 나옴
				recordscreen(); // 지난 결과 띄우기
			}
		});
		add(moleButton); // 시작 버튼 설치
		
		//
		
		//BGM 체크박스
		bgmCheck.setBounds(1100, 590, 150, 150);
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
				clickmusic(); // 클릭 버튼 소리 나옴
				mainscreen(); // 메인화면 
			}
		});
		add(mainButtonBasicButton); //닫기 버튼 설치		
	    
		// reset 버튼
		resetButton.setVisible(false);
		resetButton.setBounds(50, 80, 36, 41); //버튼 위치
		resetButton.setBorderPainted(false); // 버튼 테두리 설정 
		resetButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		resetButton.setFocusPainted(false); //포커스 표시 설정
		resetButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				resetButton.setIcon(resetButtonEnteredImage);//마우스 눌렀을 때 이미지
				resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스 손모양으로 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				resetButton.setIcon(resetButtonBasicImage); //마우스 기본 이미지로
				resetButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) { //마우스가 클릭했을 때
				clickmusic(); // 클릭 버튼 소리 나옴
				mainscreen(); // 게임화면
				
			}
		});
		add(resetButton); // 시작 버튼 설치
		
		//두더지 버튼
		moleGameBasicButton.setVisible(false);
		moleGameBasicButton.setBounds(230, 290, 90, 120); //버튼 위치
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
				hitmusic(); //뿅망치소리
				
				
			}
		});
		add(moleGameBasicButton); // 시작 버튼 설치
				
				
		//황금 두더지 버튼
		moleGameDoubleButton.setVisible(false);
		moleGameDoubleButton.setBounds(560, 460, 90, 120); //버튼 위치
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
				hitmusic(); //뿅망치소리
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
				hitmusic(); //뿅망치소리
			}
		});
		add(moleGameMinusButton); // 시작 버튼 설치			
		
		
		//점수판
		Container c = getContentPane(); // c 라는 컨테이너 생성 
		jLscore = new JLabel(Integer.toString(score)); // 라벨 생성
		jLscore.setVisible(false);  // 가시성 설정
		jLscore.setFont(new Font("나눔고딕", Font.BOLD, 30));
		jLscore.setBounds(1150, 100, 100, 200);
		c.add(jLscore);
				
		
		// 타이머
		timerBar = new JProgressBar(JProgressBar.VERTICAL); // 세로 방향으로 설정
		timerBar.setBounds(1149, 250, 20, 300); // 위치와 크기 조정
		timerBar.setForeground(Color.RED); // 원하는 색상으로 변경
		timerBar.setBorder(new LineBorder(Color.BLACK, 3)); // 테두리 색상과 굵기 설정
		timerBar.setValue(100); // 초기 값 설정 (100%로 시작)
		timerBar.setStringPainted(false); // 백분율 표시 비활성화
		timerBar.setVisible(false);
		add(timerBar); // 프레임에 타이머 바 추가
		
		
		
		
		
		
		
	}//CatchingMole()
	
	public void paint(Graphics g) { 
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 정해놨던 width값 height값을 screenImage에 넣음
		screenGraphic = screenImage.getGraphics(); //graphics 객체로 반환
		// graphics 어떤 컴퍼넌트에 그리기 위한 도구나 정보를 담고 있는 추상적인 클래스
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	
		if (isMainScreen) { // grassImageHidden 변수로 이미지 숨김 여부 판단
	        g.drawImage(grassImage, 910, 520, null);
	    }
		
		if (isGameScreen) {
	        
	        
	        for (int[] pos : grassPositions) {
	            g.drawImage(grassImage, pos[0], pos[1], null);
	        }
	    }
	}
	
	public void logic() {
		
		Music introMusic = new Music("backgroundMusic1.mp3", true);
		if (bgmCheck.isSelected()) {
	        introMusic.start();
	    }
		
		resetButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) { //마우스가 클릭했을 때
				if (bgmCheck.isSelected()) {
					introMusic.close();
	    	        introMusic.interrupt();
			    }
			}
		});
		
		moleButtons.add(moleGameBasicButton);
        moleButtons.add(moleGameDoubleButton);
        moleButtons.add(moleGameMinusButton);
		
		score = 0;
		
		// Reset the score
        score = 0;
        jLscore.setText(Integer.toString(score)); // Update score display

        
        
        // Add mole buttons to the list
        moleButtons.add(moleGameBasicButton);
        moleButtons.add(moleGameDoubleButton);
        moleButtons.add(moleGameMinusButton);

        // Add action listeners to mole buttons
        for (JButton moleButton : moleButtons) {
            moleButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    moleButton.setVisible(false); // Hide the clicked mole button
                    // Add score or perform other actions here
                }
            });
        }
        
        // Start the game timer
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            	int currentValue = timerBar.getValue();
                if (currentValue > 0) {
                    timerBar.setValue(currentValue - 15); // Decrease timer value by 1
                } else {
                    gameTimer.stop(); // Stop the timer

                    resultscreen(); // Go to the result screen

                    if (bgmCheck.isSelected()) {
                        introMusic.close();
                        introMusic.interrupt();
                    }
                }

                // Randomly show moles
                int numVisibleMoles = random.nextInt(3) + 1; // Random number of moles to show (1 to 3)
                for (int i = 0; i < numVisibleMoles; i++) {
                    int randomMoleType = random.nextInt(3); // 0: Basic, 1: Double, 2: Minus
                    int randomPosition = random.nextInt(9); // 0-8 for different positions
                    
                    JButton moleButton = moleButtons.get(randomMoleType); // Get the mole button for the selected type
                    moleButton.setBounds(molePositions[randomPosition][0], molePositions[randomPosition][1], 90, 120);
                    
                    if(isGameScreen) {
                    	moleButton.setVisible(true);
                    }else {
                    	moleButton.setVisible(false);
                    }
                    
                }
            }
        });

	    gameTimer.start(); // 타이머 시작
		
		
		// 타이머를 리셋하거나 초기화하는 작업이 필요한 경우에 이 곳에 추가합니다.
	    timerBar.setValue(100); // 타이머 바를 다시 100%로 설정
	    
	    gameTimer.restart(); // 타이머를 재시작하여 게임 시간을 다시 측정합니다.
	    
	   
	}
	
	public void scoreReader() { //파일 불러오기
		 // 현재 날짜 출력
	    LocalDate now = LocalDate.now();
		
		// 파일 읽어오기
		try {
			BufferedWriter bw = new BufferedWriter(
			new FileWriter("C:\\test\\output.txt", true));
			
			PrintWriter pw = new PrintWriter(bw, true);
			
			String s;
			
			
			bw.write(now + " : ");
			bw.write(score + "\n");
			
			bw.flush();
			bw.close();
		}catch(Exception ex) {
			return;
		}
		
		// 파일 
        try {
            BufferedReader br = new BufferedReader(
                new FileReader("C:\\test\\output.txt"));
            
            StringBuilder fileContents = new StringBuilder(); // 결과를 저장할 StringBuilder
            
            String s;
            while ((s = br.readLine()) != null) {
                scoreResults.add(s); // 날짜와 점수가 함께 저장된 항목을 그대로 추가
                fileContents.append(s).append("\n"); // 파일 내용을 StringBuilder에 추가
            }
            
            br.close();
            
            // JTextArea에 파일 내용을 설정하여 화면에 표시
            textArea.setText(fileContents.toString());
        } catch (Exception ex) {
            return;
        }
				
	}
	
	public void hitmusic() { // 두더지 잡을 때 나오는 음악
		Music buttonEnteredMusic = new Music("toyHammer.mp3", false);
		buttonEnteredMusic.start();
		try {
			Thread.sleep(300);
		}catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	public void clickmusic() {
		Music buttonEnteredMusic = new Music("buttonClick.mp3", false);
		buttonEnteredMusic.start();
		try {
			Thread.sleep(1000);
		}catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public void mainscreen() { // 메인화면
		background = new ImageIcon(Main.class.getResource("../images/introBackground.png")).getImage(); //초기 시작 화면을 담는다.
		moleButton.setVisible(true);
		bgmCheck.setVisible(true);
		startButton.setVisible(true);
		startButton.setVisible(true);
		timerBar.setVisible(false);
		moleButton.setVisible(true);
		jLscore.setVisible(false);
		resetButton.setVisible(false);
		mainButtonBasicButton.setVisible(false);
		
		
		isMainScreen = true;
		isGameScreen = false;
		isResultScreen = false;
		
		//두더지
		moleGameMinusButton.setVisible(false);
		moleGameDoubleButton.setVisible(false);
		moleGameBasicButton.setVisible(false);
		gameTimer.stop();
	}
	
	
	public void gamescreen() { // 게임화면
		
		//전 화면 요소 없애기
		startButton.setVisible(false);
		moleButton.setVisible(false);
		mainButtonBasicButton.setVisible(false);
		
		//두더지 나오기
		moleGameMinusButton.setVisible(true);
		moleGameDoubleButton.setVisible(true);
		moleGameBasicButton.setVisible(true);	
		
		jLscore.setVisible(true);
		timerBar.setVisible(true);
		bgmCheck.setVisible(false);
		resetButton.setVisible(true);
		
		
		// 버튼을 누를 때 grassImage 숨기기
		isMainScreen = false;
		isGameScreen = true;
		isResultScreen = false;
		
		
		logic(); //게임 로직
		background = new ImageIcon(Main.class.getResource("../images/mainBackgound.png")).getImage(); //초기 시작 화면을 담는다.
		
	}
	
	public void resultscreen() {
		moleGameMinusButton.setVisible(false);
	    moleGameDoubleButton.setVisible(false);
	    moleGameBasicButton.setVisible(false);
	    timerBar.setVisible(false);
	    jLscore.setVisible(true);
	    bgmCheck.setVisible(false);
	    resetButton.setVisible(false);
	    mainButtonBasicButton.setVisible(true);

	    moleButton.setVisible(false);
	    isMainScreen = false;
	    isGameScreen = false;
	    isResultScreen = true;
	    
	    jLscore.setFont(new Font("나눔고딕", Font.BOLD, 50));
		jLscore.setBounds(608, 310, 200, 50); // 점수 라벨 위치 설정
	  
	    
	    background = new ImageIcon(Main.class.getResource("../images/resultBackground.png")).getImage();  
	}
	
	
	

	public void recordscreen() {
	    // ... (화면 설정 코드)

	    moleGameMinusButton.setVisible(false);
	    moleGameDoubleButton.setVisible(false);
	    moleGameBasicButton.setVisible(false);
	    timerBar.setVisible(false);
	    jLscore.setVisible(false);
	    bgmCheck.setVisible(false);
	    resetButton.setVisible(false);
	    mainButtonBasicButton.setVisible(true);
	    moleButton.setVisible(false);
	    startButton.setVisible(false);
	    isMainScreen = false;
	    isGameScreen = false;
	    isResultScreen = false;
	    scoreReader(); // Call scoreReader() to populate scoreResults

	    background = new ImageIcon(Main.class.getResource("../images/recordBackground.png")).getImage();

	    Container c = getContentPane(); // 컨테이너 생성

	    JTextArea textArea = new JTextArea();
	    textArea.setFont(new Font("나눔고딕", Font.BOLD, 24));
	    textArea.setBounds(250, 120, 760, 440); //  // JTextArea 위치 및 크기 설정 (가로 15줄, 세로 2줄)
	    textArea.setEditable(false); // 읽기 전용으로 설정
	    c.add(textArea); // JTextArea를 컨테이너에 추가

	    // Display the score results with dates
	    StringBuilder textContent = new StringBuilder(); // 표시할 텍스트 내용을 저장할 StringBuilder
	    for (String scoreResult : scoreResults) {
	        String[] parts = scoreResult.split(" : "); // 결과를 날짜와 점수로 분리
	        if (parts.length == 2) { // 분리된 결과가 유효한 경우에만 처리
	            String dateString = parts[0];
	            String scoreString = parts[1];

	            textContent.append(dateString).append("\t").append(scoreString).append("\n");
	        }
	    }

	    textArea.setText(textContent.toString()); // 텍스트 영역에 내용 설정

	}


	
	
	
	
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null); // 초기배경이미지를 넣음
		paintComponents(g); //기존 컴포넌트의 모양에 변화를 주고자 할 때
		this.repaint(); // 메소드 호출
		//this 객체, 자기자신
	}
}
