package catchingMole_17;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends Frame{
	private static final String IMAGE_PATH = "../images/";
	private JButton hammerItem, hourItem, questionItem, outButton;
	private JPanel panel;
	private JLabel jLscore;
	
	private TimerBar timerBar;
	private Thread threadBar;
	
	private TimerNum timerNum;
	private Thread threadNum;
	
	private ImageIcon grassImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "grass.png")); //잔디라는 이미지를 읽고 처리, Image객체 넘김
	private ImageIcon resetButtonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "resetButtonBasic.png")));// out버튼 이미지 기본
	private ImageIcon resetButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "resetButtonEntered.png")));// out버튼 이미지 클릭
	
	private JButton moleGameBasicButton, moleGameDoubleButton, moleGameMinusButton;
	private ImageIcon moleButtonBasicImage = (new ImageIcon(Main.class.getResource("../images/moleButtonBasic.png")));// 두더지 이미지 기본
	private ImageIcon moleButtonEnteredImage = (new ImageIcon(Main.class.getResource("../images/moleButtonEntered.png")));// 두더지 이미지 클릭
	private ImageIcon moleButtonGameDoubleImage = (new ImageIcon(Main.class.getResource("../images/moleButtonGameDouble.png")));// 황금 두더지 이미지 기본
	private ImageIcon moleButtonGameDoubleClickImage = (new ImageIcon(Main.class.getResource("../images/moleButtonGameDoubleClick.png")));// 황금 두더지 이미지 기본
	private ImageIcon moleButtonGameMinusImage = (new ImageIcon(Main.class.getResource("../images/moleButtonGameMinus.png")));// 황금 두더지 이미지 기본
	private ImageIcon moleButtonGameMinusClickImage = (new ImageIcon(Main.class.getResource("../images/moleButtonGameMinusClick.png")));// 황금 두더지 이미지 기본

	private Timer disappearanceTimer; //두더지 없애는 시간
	private int basicButtonCount = 0;
    private int doubleButtonCount = 0;
    private int minusButtonCount = 0;
	
    JLabel hammerItemLabel;
    
	int width = 75, height = 75;
	int x = 200, y = 150;
	
	int second;
	
	private Timer moleTimer;
	private int moleDisplayDuration = 2000; // 두더지가 보여지는 시간 (밀리초 단위)
	
	private ArrayList<Integer> availablePositions = new ArrayList<>();
	private ArrayList<JButton> moleButtons = new ArrayList<>();
	int score;
	
	public int item1Count = 0;

	
	private Music introMusic; // 멤버 변수로 선언
	
	int[][] grassPositions = { 	//잔디 위치를 정리
            {200, 230}, {530, 230}, {850, 230},
            {200, 380}, {530, 380}, {850, 380},
            {200, 530}, {530, 530}, {850, 530}
        };
	
	int[][] molePositions = { 	//두더지 위치를 정리
            {230, 135}, {560, 135}, {880, 135},
            {230, 290}, {560, 290}, {880, 290},
            {230, 445}, {560, 445}, {880, 445}
    };
	
	 public Game(){
		 initializeComponents();
		 displayGrass();
		 setupGameTimer();
		 displayMole();
		 introMusic = new Music("backgroundMusic1.mp3", true); // 생성자에서 초기화
		 introMusic.start();
	 }
	 
	//JButton 공통되는 부분 제외
	 private JButton createButton(String imageName, int x, int y) {
		JButton button = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH + imageName)));
		button.setBounds(x, y, button.getIcon().getIconWidth(), button.getIcon().getIconHeight());
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		return button;
	}	 
	 
	//초기화 코드 작성
	public void initializeComponents() {
		//배경 설정
        background = new JLabel(new ImageIcon(Main.class.getResource(IMAGE_PATH + "gameScreen.png"))); // 배경이미지
        background.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        add(background);
        
        //점수
        score = 0;
        
        //나가기 버튼
        outButton = createButton("resetButtonBasic.png", 100, 80);
        outButton.setVisible(true);
      	outButton.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
      	    @Override
      		public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때	
      	    	outButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
      	    	outButton.setIcon(resetButtonEnteredImage);
      		}
      		@Override
      		public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
      			outButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
      			outButton.setIcon(resetButtonImage);
      		}
      		@Override
      		public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
      			//로그인 화면으로 이동
      			clickmusic();
      			dispose();
      			new Lobby();
      		}
      	});
      	background.add(outButton);
      	
      	jLscore = new JLabel(Integer.toString(score)); // 라벨 생성
      	jLscore.setVisible(true);  // 가시성 설정
		jLscore.setFont(new Font("나눔고딕", Font.BOLD, 30));
		jLscore.setBounds(1163, 95, 100, 200);
		background.add(jLscore);
      	
      	//망치 아이템 버튼(황금두더지만 나옴)
      	hammerItem = createButton("hammerItem.png", 1130, 45);
      	hammerItem.setVisible(true);
      	background.add(hammerItem);
      	
      	// 망치 아이템 버튼 label
        JLabel hammerItemLabel = new JLabel("0");
        hammerItemLabel.setBounds(1140, 100, 50, 50); // 이미지 라벨 위치 및 크기 설정
        hammerItemLabel.setFont(new Font("나눔 고딕", Font.BOLD, 20));
        hammerItemLabel.setVisible(true); // 초기에는 이미지를 보이지 않도록 설정
        background.add(hammerItemLabel);
      	
      	//시간 아이템 버튼(시간이 늘어남)
        hourItem = createButton("hourItem.png", 1000, 45);
        hourItem.setVisible(true);
      	background.add(hourItem);
      	
      	// 시간 아이템 버튼 label
        JLabel hourItemLabel = new JLabel("0");
        hourItemLabel.setBounds(1020, 100, 50, 50); // 이미지 라벨 위치 및 크기 설정
        hourItemLabel.setFont(new Font("나눔 고딕", Font.BOLD, 20));
        hourItemLabel.setVisible(true); // 초기에는 이미지를 보이지 않도록 설정
        background.add(hourItemLabel);
      	
      	
      	//질문 아이템 버튼(시간이 늘어남)
        questionItem = createButton("questionItem.png", 870, 45);
        questionItem.setVisible(true);
      	background.add(questionItem);
      	
      	// 질문 아이템 버튼 label
        JLabel questionItemLabel = new JLabel("0");
        questionItemLabel.setBounds(900, 100, 50, 50); // 이미지 라벨 위치 및 크기 설정
        questionItemLabel.setFont(new Font("나눔 고딕", Font.BOLD, 20));
        questionItemLabel.setVisible(true); // 초기에는 이미지를 보이지 않도록 설정
        background.add(questionItemLabel);
                
	}
	
	//잔디 설치 코드 작성
	public void displayGrass() {
		for (int i = 0; i < grassPositions.length; i++) {
			int x = grassPositions[i][0];
	        int y = grassPositions[i][1];
	            
	        // 잔디 이미지 라벨 생성
	        JLabel grassLabel = new JLabel(grassImage);
	        grassLabel.setBounds(x, y, grassImage.getIconWidth(), grassImage.getIconHeight());
	        background.add(grassLabel);
	    }
	}
	
	public void displayMole() {
	    Random random = new Random();
	    int basicButtonIndex = random.nextInt(molePositions.length);
	    int doubleButtonIndex = random.nextInt(molePositions.length);
	    int minusButtonIndex = random.nextInt(molePositions.length);

	    // 두더지 버튼
	    moleGameBasicButton = createButton("moleButtonBasic.png", molePositions[basicButtonIndex][0], molePositions[basicButtonIndex][1]);
	    moleGameBasicButton.setVisible(true);
	    moleGameBasicButton.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) { // 마우스가 클릭했을 때
	            score = score + 2; // 점수 증가
	            jLscore.setText(Integer.toString(score)); // 화면에 점수 표시 갱신
	            // 두더지를 다시 랜덤한 위치에 표시
	            moleGameBasicButton.setVisible(false); // 두더지 가시성 숨기기
	            hitmusic();
	        }
	    });
	    background.add(moleGameBasicButton);

	    // 황금 두더지 버튼
	    moleGameDoubleButton = createButton("moleButtonGameDouble.png", molePositions[doubleButtonIndex][0], molePositions[doubleButtonIndex][1]);
	    moleGameDoubleButton.setVisible(true);
	    moleGameDoubleButton.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) { // 마우스가 클릭했을 때
	            score = score + 4; // 점수 증가
	            jLscore.setText(Integer.toString(score)); // 화면에 점수 표시 갱신
	            moleGameDoubleButton.setVisible(false); // 두더지 가시성 숨기기
	            hitmusic();
	        }
	    });
	    background.add(moleGameDoubleButton);

	    // 벌칙 두더지 버튼
	    moleGameMinusButton = createButton("moleButtonGameMinus.png", molePositions[minusButtonIndex][0], molePositions[minusButtonIndex][1]);
	    moleGameMinusButton.setVisible(true);
	    moleGameMinusButton.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) { // 마우스가 클릭했을 때
	            score = score - 3; // 점수 감소
	            jLscore.setText(Integer.toString(score)); // 화면에 점수 표시 갱신
	            // 두더지를 다시 랜덤한 위치에 표시
	            int newIndex = random.nextInt(molePositions.length);
	            moleGameMinusButton.setBounds(molePositions[newIndex][0], molePositions[newIndex][1], 90, 120);
	            moleGameMinusButton.setVisible(false); // 두더지 가시성 숨기기
	            hitmusic();
	        }
	    });
	    background.add(moleGameMinusButton);
	    
	    startDisappearanceTimer(1500); // 1초 후에 두더지 버튼 숨기기
	}

	public void startDisappearanceTimer(int delay) {
	    if (disappearanceTimer != null) {
	        disappearanceTimer.cancel(); // 이전에 실행중인 TimerTask 취소
	    }

	    disappearanceTimer = new Timer();
	    disappearanceTimer.schedule(new TimerTask() {
	        @Override
	        public void run() {
	            hideMoleButtons();
	            displayMole(); // 두더지를 다시 나타내기
	        }
	    }, delay);
	}

	public void hideMoleButtons() {
	    moleGameBasicButton.setVisible(false);
	    moleGameDoubleButton.setVisible(false);
	    moleGameMinusButton.setVisible(false);
	}
	
	//타이머 설치 코드 작성
	public void setupGameTimer() {
		int second = 10;
		
		// 타이머
        panel = new JPanel();
		panel.setLayout(null);
		
		timerBar = new TimerBar(second);
		threadBar = new Thread(timerBar);
		threadBar.start();
		background.add(timerBar);
		
		timerNum = new TimerNum(second, this);
		threadNum = new Thread(timerNum);
		threadNum.start();
		background.add(timerNum);
	}
	
	public void nextScreen() {
	    Result resultFrame = new Result(score); // 현재 게임의 점수를 전달하여 Result 객체 생성
	    resultFrame.setVisible(true); // 결과 창 보이기
	    introMusic.close(); // 노래 멈추기
	    this.dispose(); // 현재 게임 창 닫기
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
	
	


	public static void main(String[] args) {
		Game frame = new Game();
    }
	
	
	
}
