package catchingMole_13;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Lobby extends Frame{ //게임의 로비 화면
	private ImageIcon settingbuttonBasicImage = (new ImageIcon(Main.class.getResource("../images/settingButton.png"))); // 설정 이미지 클릭
	private ImageIcon settingbuttonEnteredImage = (new ImageIcon(Main.class.getResource("../images/settingButtonEntered.png"))); // 설정 이미지 클릭
	private ImageIcon startButtonBasicImage = (new ImageIcon(Main.class.getResource("../images/startButtonBasic.png")));  //게임 시작 이미지 기본
	private ImageIcon startButtonEnteredImage = (new ImageIcon(Main.class.getResource("../images/startButtonEntered.png")));// 게임 시작 이미지 클릭
	private ImageIcon explainButtonBasicImage = (new ImageIcon(Main.class.getResource("../images/explainButton.png")));  //게임 설명 이미지 기본
	private ImageIcon explainButtonEnteredImage = (new ImageIcon(Main.class.getResource("../images/explainButtonEntered.png")));// 게임 설명 이미지 클릭
	private ImageIcon storeButtonBasicImage = (new ImageIcon(Main.class.getResource("../images/storeButton.png")));  //상점 이미지 기본
	private ImageIcon storeButtonEnteredImage = (new ImageIcon(Main.class.getResource("../images/storeButtonEntered.png")));// 상점 이미지 클릭
	private ImageIcon rankingButtonBasicImage = (new ImageIcon(Main.class.getResource("../images/rankingButton.png")));  //상점 이미지 기본
	private ImageIcon rankingButtonEnteredImage = (new ImageIcon(Main.class.getResource("../images/rankingButtonEntered.png")));// 상점 이미지 클릭
    private ImageIcon explainImage = new ImageIcon(Main.class.getResource("../images/explain.png"));
	
	 public Lobby(){
		 initializeComponents();
	 }
	 
	//초기화 코드 작성
	public void initializeComponents() {
		//배경 설정
        background = new JLabel(new ImageIcon(Main.class.getResource("../images/lobbyScreen.png"))); // 배경이미지
        background.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        add(background);
        
        // 코인 label
        JLabel coinLabel = new JLabel("0"); // 텍스트필드 초기화
        coinLabel.setFont(new Font("나눔 고딕", Font.BOLD, 25));
        coinLabel.setBounds(1000, 75, 150, 30);
    	background.add(coinLabel);
        
        // 설정 버튼
        JButton settingButton = new JButton(new ImageIcon(Main.class.getResource("../images/settingButton.png")));
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
				//로그인 화면으로 이동
				dispose();
				new Setting();
			}
		});
	    background.add(settingButton);
	    
	    
	    //게임 시작 버튼
	    JButton startButton = new JButton(new ImageIcon(Main.class.getResource("../images/startButtonBasic.png")));
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
	  				
	  		}
	  	});
	  	background.add(startButton); // 시작 버튼 설치
	  	
	  	
	  	// 설명 이미지 label
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(100, 100, 500, 500); // 이미지 라벨 위치 및 크기 설정
        imageLabel.setVisible(false); // 초기에는 이미지를 보이지 않도록 설정
        // 프레임에 이미지 라벨 추가
        background.add(imageLabel);
        
	  	//게임 설명 버튼
	  	JButton explainButton = new JButton(new ImageIcon(Main.class.getResource("../images/explainButton.png")));
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
	  			 // 이미지 라벨에 이미지 설정
                imageLabel.setIcon(explainImage);
                imageLabel.setVisible(true); // 이미지를 보이도록 설정
	  				
	  		}
  		});
  		background.add(explainButton); // 시작 버튼 설치
	  	
	  	//상점 버튼
		JButton storeButton = new JButton(new ImageIcon(Main.class.getResource("../images/storeButton.png")));
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
	  				
	  		}
  		});
	  	background.add(storeButton); // 시작 버튼 설치
	  	
	  	
	  	//상점 버튼
	  	JButton rankingButton = new JButton(new ImageIcon(Main.class.getResource("../images/rankingButton.png")));
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
	  				
	  		}
  		});
	  	background.add(rankingButton); // 시작 버튼 설치
	}
	 
	public static void main(String[] args) {
		Lobby frame = new Lobby();
    }
	
	
	
}
