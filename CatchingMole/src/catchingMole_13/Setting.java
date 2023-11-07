package catchingMole_13;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Setting extends Frame { // 자신의 기록, 로그아웃
	private ImageIcon logoutButtonImage = (new ImageIcon(Main.class.getResource("../images/logoutButton.png"))); // 로그인버튼 이미지 기본
	private ImageIcon logoutButtonEnteredImage = (new ImageIcon(Main.class.getResource("../images/logoutButtonEntered.png"))); // 로그인버튼 이미지 클릭
	
	 public Setting(){
		 initializeComponents();
	 }
	 
	//초기화 코드 작성
	public void initializeComponents() {
		//배경 설정
        background = new JLabel(new ImageIcon(Main.class.getResource("../images/settingScreen.png"))); // 배경이미지
        background.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        add(background);
		
        // 로그아웃 버튼
        JButton logoutButton = new JButton(new ImageIcon(Main.class.getResource("../images/logoutButton.png")));
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
				new Main();
			}
		});
	    background.add(logoutButton);
	}
	
	public static void main(String[] args) {
		Setting frame = new Setting();
    }
}
