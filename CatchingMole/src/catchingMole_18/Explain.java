package catchingMole_18;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public class Explain extends Frame { // 자신의 기록, 로그아웃
	private static final String IMAGE_PATH = "../images/";
	private ImageIcon nextButtonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "nextButton.png")));// 다음버튼 이미지 기본
	private ImageIcon nextButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "nextButtonEntered.png")));// 다음버튼 이미지 클릭
	private ImageIcon okButtonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "okButton.png")));// ok버튼 이미지 기본
	private ImageIcon okButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "okButtonEntered.png")));// ok버튼 이미지 클릭
	private int clickCount = 1; // 클릭된 횟수를 추적하는 변수
	private ImageIcon explainImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "explain2.png"));
    private ImageIcon explain2Image = new ImageIcon(Main.class.getResource(IMAGE_PATH + "explain2.png"));
    private ImageIcon explain3Image = new ImageIcon(Main.class.getResource(IMAGE_PATH + "explain3.png"));
    private ImageIcon explain4Image = new ImageIcon(Main.class.getResource(IMAGE_PATH + "explain4.png"));

	 public Explain(){
		 initializeComponents();
	 }
	 
	//초기화 코드 작성
	public void initializeComponents() {
		//배경 설정
        background = new JLabel(new ImageIcon(Main.class.getResource(IMAGE_PATH + "explain1Screen.png"))); // 배경이미지
        background.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        add(background);
		
        // ok 버튼
        JButton okButton = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH + "okButton.png")));
        okButton.setVisible(false);
        okButton.setBounds(1030, 570, 171, 58);
        okButton.setBorderPainted(false); // 버튼 테두리 설정
        okButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
        okButton.setFocusPainted(false); //포커스 표시 설정
        okButton.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
	    	@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때	
	    		okButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
	    		okButton.setIcon(okButtonEnteredImage);
	    	}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				okButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
				okButton.setIcon(okButtonImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
				//첫 화면으로 이동
				dispose();
				new Lobby();
			}
		});
	    background.add(okButton);
        
        
        // 설명 이미지 label
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(300, 223, 680, 412); // 이미지 라벨 위치 및 크기 설정
        imageLabel.setVisible(false); // 초기에는 이미지를 보이지 않도록 설정
        // 프레임에 이미지 라벨 추가
        background.add(imageLabel);
        
        
        // 다음 설명 버튼
        JButton nextButton = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH + "nextButton.png")));
        nextButton.setBounds(1100, 370, 36, 41);
        nextButton.setBorderPainted(false); // 버튼 테두리 설정
        nextButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
        nextButton.setFocusPainted(false); //포커스 표시 설정
        nextButton.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
	    	@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때	
	    		nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
	    		nextButton.setIcon(nextButtonEnteredImage);
	    	}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				nextButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
				nextButton.setIcon(nextButtonImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
				// 버튼이 클릭될 때마다 clickCount를 증가시키고 해당 이미지로 설정
                clickCount++;
                if (clickCount == 2) {
                    explainImage = explain2Image;
                } else if (clickCount == 3) {
                    explainImage = explain3Image;
                } else if (clickCount == 4) {
                    explainImage = explain4Image;
                    nextButton.setVisible(false);
                    okButton.setVisible(true);
                }
                // 이미지 라벨에 이미지 설정
                imageLabel.setIcon(explainImage);
                imageLabel.setVisible(true);
			}
		});
	    background.add(nextButton);
	    
	    
        
	    
		
	    
	}
	
	public static void main(String[] args) {
		Explain frame = new Explain();
    }
}
