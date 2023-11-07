package catchingMole_16;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Result extends Frame{ //로그인
	private ImageIcon loginbuttonImage = (new ImageIcon(Main.class.getResource("../images/loginButton.png"))); // 로그인버튼 이미지 기본
	private ImageIcon loginButtonEnteredImage = (new ImageIcon(Main.class.getResource("../images/loginButtonEntered.png"))); // 로그인버튼 이미지 클릭
	private ImageIcon resetButtonImage = (new ImageIcon(Main.class.getResource("../images/resetButtonBasic.png")));// out버튼 이미지 기본
	private ImageIcon resetButtonEnteredImage = (new ImageIcon(Main.class.getResource("../images/resetButtonEntered.png")));// out버튼 이미지 클릭

	private static int score;

    public Result(int score) {
        this.score = score; // 생성자에서 score 값을 받아서 멤버 변수에 저장
        initializeComponents();
    }

	 
	//초기화 코드 작성
	public void initializeComponents() {
		//배경 설정
        background = new JLabel(new ImageIcon(Main.class.getResource("../images/resultScreen.png"))); // 배경이미지
        background.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        add(background);
        
        // ok 버튼
        JButton okButton = new JButton(new ImageIcon(Main.class.getResource("../images/okButton.png")));
        okButton.setVisible(true);
        okButton.setBounds(1030, 570, 171, 58);
        okButton.setBorderPainted(false); // 버튼 테두리 설정
        okButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
        okButton.setFocusPainted(false); //포커스 표시 설정
        okButton.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
	    	@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때	
	    		okButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
	    		
	    	}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				okButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
				
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
				//첫 화면으로 이동
				dispose();
				new Lobby();
			}
		});
	    background.add(okButton);
	    
	    // 코인 label
        JLabel coinLabel = new JLabel(Integer.toString(score)); // 텍스트필드 초기화
        coinLabel.setFont(new Font("나눔 고딕", Font.BOLD, 60));
        coinLabel.setBounds(610, 350, 200, 50);
    	background.add(coinLabel);
		
	}
	 
	public static void main(String[] args) {
		Result frame = new Result(score);
    }
	
	
	
}
