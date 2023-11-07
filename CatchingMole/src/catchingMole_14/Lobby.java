package catchingMole_14;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class Lobby extends Frame{ //게임의 로비 화면
	private static final String IMAGE_PATH = "../images/";
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
	
	public Lobby(){
		 initializeComponents();
	 }
	 
	//초기화 코드 작성
	public void initializeComponents() {
		//배경 설정
        background = new JLabel(new ImageIcon(Main.class.getResource(IMAGE_PATH + "lobbyScreen.png"))); // 배경이미지
        background.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        add(background);
        
        // 코인 label
        JLabel coinLabel = new JLabel("0"); // 텍스트필드 초기화
        coinLabel.setFont(new Font("나눔 고딕", Font.BOLD, 25));
        coinLabel.setBounds(1000, 75, 150, 30);
    	background.add(coinLabel);
        
        // 설정 버튼
        JButton settingButton = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH + "settingButton.png")));
        settingButton.setBounds(1100, 68, 43, 43);
        settingButton.setBorderPainted(false); // 버튼 테두리 설정
        settingButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
        settingButton.setFocusPainted(false); //포커스 표시 설정
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
	    JButton startButton = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH + "startButtonBasic.png")));
	  	startButton.setBounds(840, 230, 310, 110); //버튼 위치
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
	  			//설정 화면으로 이동
				dispose();
				new Game();
	  		}
	  	});
	  	background.add(startButton); // 시작 버튼 설치
	  	
        
	  	//게임 설명 버튼
	  	JButton explainButton = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH + "explainButton.png")));
	  	explainButton.setBounds(840, 380, 310, 110); //버튼 위치
	  	explainButton.setBorderPainted(false); // 버튼 테두리 설정 
	  	explainButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
	  	explainButton.setFocusPainted(false); //포커스 표시 설정
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
		JButton storeButton = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH + "storeButton.png")));
		storeButton.setBounds(60, 500, 128, 119); //버튼 위치
		storeButton.setBorderPainted(false); // 버튼 테두리 설정 
		storeButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		storeButton.setFocusPainted(false); //포커스 표시 설정
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
	  	
	  	
	  	//상점 버튼
	  	JButton rankingButton = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH + "rankingButton.png")));
	  	rankingButton.setBounds(230, 500, 128, 119); //버튼 위치
	  	rankingButton.setBorderPainted(false); // 버튼 테두리 설정 
	  	rankingButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
	  	rankingButton.setFocusPainted(false); //포커스 표시 설정
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
    	
    	//BGM 체크박스
    	JCheckBox bgmCheck = new JCheckBox("BGM");
    	bgmCheck.setBounds(1050, 550, 150, 150);
    	bgmCheck.setFont(new Font("Arial", Font.BOLD, 24)); // 글꼴 설정
    	bgmCheck.setBackground(new Color(175, 212, 133)); // 원하는 배경색상으로 변경
    	bgmCheck.setFocusPainted(false);
    	bgmCheck.setVisible(true);
    	background.add(bgmCheck);
	}
	 
	public static void main(String[] args) {
		Lobby frame = new Lobby();
    }
	
	
	
}
