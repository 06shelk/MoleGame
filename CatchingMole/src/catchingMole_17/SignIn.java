package catchingMole_17;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import catchingMole_12.Main;

public class SignIn extends Frame{ //로그인
	private static final String IMAGE_PATH = "../images/";
	private ImageIcon loginbuttonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "loginButton.png"))); // 로그인버튼 이미지 기본
	private ImageIcon loginButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH+"loginButtonEntered.png"))); // 로그인버튼 이미지 클릭
	private ImageIcon resetButtonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH+"resetButtonBasic.png")));// out버튼 이미지 기본
	private ImageIcon resetButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH+"resetButtonEntered.png")));// out버튼 이미지 클릭
	
	 public SignIn(){
		 initializeComponents();
	 }
	 
	//초기화 코드 작성
	public void initializeComponents() {
		//배경 설정
        background = new JLabel(new ImageIcon(Main.class.getResource(IMAGE_PATH+"loginScreen.png"))); // 배경이미지
        background.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        add(background);
        
    	JTextField idJTextField = new JTextField(10); // 텍스트필드 초기화
    	idJTextField.setBounds(541, 370, 340, 30);
    	background.add(idJTextField);
    	
    	JTextField PWTextField = new JTextField(10); // 텍스트필드 초기화
    	PWTextField.setBounds(541, 270, 340, 30);
    	background.add(PWTextField);
    	
    	JLabel signoutLabel = new JLabel("회원가입 하러가기"); // 텍스트필드 초기화
    	signoutLabel.setFont(new Font("나눔 고딕", Font.BOLD, 15));
    	signoutLabel.setBounds(565, 570, 150, 30);
    	signoutLabel.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
    		@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
    			//로그인 화면으로 이동
				dispose();
				new SignUp();
			}
		});
    	background.add(signoutLabel);
    	
		JButton loginbutton = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH+"loginbutton.png")));
		loginbutton.setBounds(541, 480, 198, 67);
		loginbutton.setBorderPainted(false); // 버튼 테두리 설정
		loginbutton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		loginbutton.setFocusPainted(false); //포커스 표시 설정
		loginbutton.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
	    	@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때	
	    		loginbutton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
	    		loginbutton.setIcon(loginButtonEnteredImage);
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				loginbutton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
				loginbutton.setIcon(loginbuttonImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
				//로그인 화면으로 이동
				dispose();
				new Lobby();
			}
		});
	    background.add(loginbutton);    
		
	}
	 
	public static void main(String[] args) {
		SignIn frame = new SignIn();
    }
	
	
	
}
