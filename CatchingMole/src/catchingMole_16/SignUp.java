package catchingMole_16;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import catchingMole_12.Main;

public class SignUp extends Frame{ //회원가입
	private static final String IMAGE_PATH = "../images/";
	private ImageIcon signupButtonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "signupButton.png")));// 로그아웃 이미지 기본
	
	 public SignUp(){
		 initializeComponents();
	 }
	 
	//초기화 코드 작성
	public void initializeComponents() {
		//배경 설정
        background = new JLabel(new ImageIcon(Main.class.getResource(IMAGE_PATH + "signupScreen.png"))); // 배경이미지
        background.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        add(background);
        
    	JTextField idJTextField = new JTextField(10); // 텍스트필드 초기화
    	idJTextField.setBounds(541, 370, 340, 30);
    	background.add(idJTextField);
    	
    	JTextField PWTextField = new JTextField(10); // 텍스트필드 초기화
    	PWTextField.setBounds(541, 270, 340, 30);
    	background.add(PWTextField);
    	
    	JTextField PWCheckTextField = new JTextField(10); // 텍스트필드 초기화
    	PWCheckTextField.setBounds(541, 470, 340, 30);
    	background.add(PWCheckTextField);
    	
    	
    	
		JButton signupButton = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH + "signupButton.png")));
		signupButton.setBounds(541, 550, 198, 67);
		signupButton.setBorderPainted(false); // 버튼 테두리 설정
		signupButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		signupButton.setFocusPainted(false); //포커스 표시 설정
		signupButton.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
	    	@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때	
	    		signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				signupButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
				//로그인 화면으로 이동
				dispose();
				new SignIn();
			}
		});
	    background.add(signupButton);
		
	}
	 
	public static void main(String[] args) {
		SignUp frame = new SignUp();
    }
	
	
	
}
