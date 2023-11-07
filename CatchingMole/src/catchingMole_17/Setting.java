package catchingMole_17;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import catchingMole_12.Main;

public class Setting extends Frame { // 자신의 기록, 로그아웃
	private static final String IMAGE_PATH = "../images/";
	private ImageIcon logoutButtonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "logoutButton.png"))); // 로그인버튼 이미지 기본
	private ImageIcon logoutButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "logoutButtonEntered.png"))); // 로그인버튼 이미지 클릭
	private ImageIcon resetButtonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "resetButtonBasic.png")));// out버튼 이미지 기본
	private ImageIcon resetButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "resetButtonEntered.png")));// out버튼 이미지 클릭
	
	 public Setting(){
		 initializeComponents();
	 }
	 
	//초기화 코드 작성
	public void initializeComponents() {
		//배경 설정
        background = new JLabel(new ImageIcon(Main.class.getResource(IMAGE_PATH + "settingScreen.png"))); // 배경이미지
        background.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        add(background);
		
        // 로그아웃 버튼
        JButton logoutButton = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH + "logoutButton.png")));
        logoutButton.setBounds(554, 570, 171, 58);
        logoutButton.setBorderPainted(false); // 버튼 테두리 설정
        logoutButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
        logoutButton.setFocusPainted(false); //포커스 표시 설정
        logoutButton.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
	    	@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때	
	    		logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
	    		logoutButton.setIcon(logoutButtonEnteredImage);
	    	}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				logoutButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
				logoutButton.setIcon(logoutButtonImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
				//첫 화면으로 이동
				dispose();
				new SignIn();
			}
		});
	    background.add(logoutButton);
	    
	   //나가기 버튼
	   JButton outButton = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH + "resetButtonBasic.png")));
	   outButton.setBounds(100, 80, 36, 41); //버튼 위치
	   outButton.setBorderPainted(false); // 버튼 테두리 설정
	   outButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
	   outButton.setFocusPainted(false); //포커스 표시 설정
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
		  		dispose();
		  		new Lobby();
		  	}
	  	});
	  	background.add(outButton);
	  	
	  	//최고기록
	  	JLabel bestLabel = new JLabel("최고기록 : 0"); // 텍스트필드 초기화
	  	bestLabel.setFont(new Font("나눔 고딕", Font.BOLD, 20));
	  	bestLabel.setBounds(570, 210, 150, 30);
    	background.add(bestLabel);
	}
	
	public static void main(String[] args) {
		Setting frame = new Setting();
    }
}
