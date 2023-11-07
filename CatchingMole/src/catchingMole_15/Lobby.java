package catchingMole_15;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import catchingMole_16.Result;

public class Lobby extends Frame{ //게임의 로비 화면
	private static final String IMAGE_PATH = "../images/"; //이미지 경로
	private ImageIcon settingbuttonBasicImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "settingButton.png"))); // 설정 이미지 클릭
	private ImageIcon settingbuttonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "settingButtonEntered.png"))); // 설정 이미지 클릭
	private ImageIcon startButtonBasicImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "startButtonBasic.png")));  //게임 시작 이미지 기본
	private ImageIcon startButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "startButtonEntered.png")));// 게임 시작 이미지 클릭
	private ImageIcon explainButtonBasicImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "explainButton.png")));  //게임 설명 이미지 기본
	private ImageIcon explainButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "explainButtonEntered.png")));// 게임 설명 이미지 클릭
	private ImageIcon storeButtonBasicImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "storeButton.png")));  //상점 이미지 기본
	private ImageIcon storeButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "storeButtonEntered.png")));// 상점 이미지 클릭
	private ImageIcon rankingButtonBasicImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "rankingButton.png")));  //상점 이미지 기본
	private ImageIcon rankingButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "rankingButtonEntered.png")));// 상점 이미지 클릭
	private ImageIcon explainImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "molePro.png"));
	
	//버튼 정의
	private JButton settingButton, startButton, explainButton, storeButton, rankingButton; //설정, 게임시작, 게임설명, 상점, 랭킹 버튼
	 private Store store; // Store 클래스의 객체를 저장할 변수 선언
	
	int coin = 5;

    public Lobby() {
    	store = new Store();
        initializeComponents();
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
        background = new JLabel(new ImageIcon(Main.class.getResource(IMAGE_PATH + "lobbyScreen.png"))); // 배경이미지
        background.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        add(background);
        
        // 코인 label
        JLabel coinLabel = new JLabel(Integer.toString(coin)); // 텍스트필드 초기화
        coinLabel.setFont(new Font("나눔 고딕", Font.BOLD, 25));
        coinLabel.setBounds(1000, 75, 150, 30);
    	background.add(coinLabel);
        
        // 설정 버튼
    	settingButton = createButton("settingButton.png", 1100, 68);
    	settingButton.setVisible(true);
        settingButton.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
	    	@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
	    		settingButton.setIcon(settingbuttonEnteredImage);//마우스가 올라갔을 때 이미지
	    		settingButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				settingButton.setIcon(settingbuttonBasicImage);//마우스 나갔을 때 이미지
				settingButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
				//설정 화면으로 이동
				dispose();
				new Setting();
			}
		});
	    background.add(settingButton);
	    
	    
	    //게임 시작 버튼
	    startButton = createButton("startButtonBasic.png", 840, 230);
	    startButton.setVisible(true);
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
	  			//설정 화면으로 이동
				dispose();
				new Game();
	  		}
	  	});
	  	background.add(startButton); // 시작 버튼 설치
	  	
        
	  	//게임 설명 버튼
	  	explainButton = createButton("explainButton.png", 840, 380);
	  	explainButton.setVisible(true);
	  	explainButton.addMouseListener(new MouseAdapter() {
		  	@Override
	  		public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
		  		explainButton.setIcon(explainButtonEnteredImage);//마우스 눌렀을 때 이미지
		  		explainButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스 손모양으로 바뀜
	  		}
	  		@Override
	  		public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
	  			explainButton.setIcon(explainButtonBasicImage); //마우스 기본 이미지로
	  			explainButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
	  		}
	  		@Override
	  		public void mousePressed(MouseEvent e) { //마우스가 클릭했을 때
	  			// 설정 화면 이동
	  			dispose();
				new Explain();
	  				
	  		}
  		});
  		background.add(explainButton); // 시작 버튼 설치
	  	
	  	//상점 버튼
  		storeButton = createButton("storeButton.png", 60, 500);
  		storeButton.setVisible(true);
		storeButton.addMouseListener(new MouseAdapter() {
		  	@Override
	  		public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
		  		storeButton.setIcon(storeButtonEnteredImage);//마우스 눌렀을 때 이미지
		  		storeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스 손모양으로 바뀜
	  		}
	  		@Override
	  		public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
	  			storeButton.setIcon(storeButtonBasicImage); //마우스 기본 이미지로
	  			storeButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
	  		}
	  		@Override
	  		public void mousePressed(MouseEvent e) { //마우스가 클릭했을 때
	  			// 상점 화면 이동
	  			dispose();
				new Store();
	  		}
  		});
	  	background.add(storeButton); // 시작 버튼 설치
	  	
	  	
	  	//랭킹 버튼
	  	rankingButton = createButton("rankingButton.png", 230, 500);
	  	rankingButton.setVisible(true);
	  	rankingButton.addMouseListener(new MouseAdapter() {
		  	@Override
	  		public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
		  		rankingButton.setIcon(rankingButtonEnteredImage);//마우스 눌렀을 때 이미지
		  		rankingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스 손모양으로 바뀜
	  		}
	  		@Override
	  		public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
	  			rankingButton.setIcon(rankingButtonBasicImage); //마우스 기본 이미지로
	  			rankingButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
	  		}
	  		@Override
	  		public void mousePressed(MouseEvent e) { //마우스가 클릭했을 때
	  			//로그인 화면으로 이동
      			dispose();
      			new Rank();
	  		}
  		});
	  	background.add(rankingButton); // 시작 버튼 설치
	  	
	  	// 프로필 이미지 
        JLabel ProLabel = new JLabel();
        ProLabel.setIcon(explainImage);
        ProLabel.setBounds(150, 200, 201, 201); // 이미지 라벨 위치 및 크기 설정
        ProLabel.setVisible(true); // 초기에는 이미지를 보이지 않도록 설정
    
        
        // 프레임에 이미지 라벨 추가
        background.add(ProLabel);
        
        
        // 닉네임 label
        JLabel userLabel = new JLabel("닉네임"); // 텍스트필드 초기화
        userLabel.setFont(new Font("나눔 고딕", Font.BOLD, 25));
        userLabel.setBounds(210, 410, 150, 30);
    	background.add(userLabel);
    	

    	System.out.println("Ee");
	}
	
	public static void main(String[] args) {
		Lobby frame = new Lobby();
    }
	
}
