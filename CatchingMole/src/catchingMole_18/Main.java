package catchingMole_18;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Main extends Frame { // 게임 시작 화면
	//버튼 이미지 저장
	private static final String IMAGE_PATH = "../images/";
	private ImageIcon yesbuttonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "yesButton.png"))); // 네 이미지 클릭
	private ImageIcon yesbuttonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "yesButtonEntered.png"))); // 네 이미지 클릭
	private ImageIcon nobuttonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "noButton.png"))); // 아니오 이미지 클릭
	private ImageIcon nobuttonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "noButtonEntered.png"))); // 아니오 이미지 클릭
	public Main() {
    	//초기화 코드 불러오기
    	initializeComponents();
    }
    
    //초기화 코드 작성
    public void initializeComponents() {
    	//배경 설정
        background = new JLabel(new ImageIcon(Main.class.getResource(IMAGE_PATH + "gameStartScreen.png"))); // 배경이미지
        background.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        add(background);
        
        //네 버튼 설정
        JButton yesButton = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH + "yesButton.png")));
        yesButton.setBounds(370, 480, 198, 67);
        yesButton.setBorderPainted(false); // 버튼 테두리 설정
        yesButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
        yesButton.setFocusPainted(false); //포커스 표시 설정
        yesButton.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				yesButton.setIcon(yesbuttonEnteredImage); //마우스 눌렀을 때 이미지
				yesButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				yesButton.setIcon(yesbuttonImage); //마우스 눌렀을 때 이미지
				yesButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
				//로그인 화면으로 이동
				dispose();
				new LoginScreen();
			}
		});
        background.add(yesButton);
        
        //아니요 버튼 설정
        JButton noButton = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH + "noButton.png")));
        noButton.setBounds(710, 480, 198, 67);
        noButton.setBorderPainted(false); // 버튼 테두리 설정
        noButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
        noButton.setFocusPainted(false); //포커스 표시 설정
        noButton.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				noButton.setIcon(nobuttonEnteredImage); //마우스 눌렀을 때 이미지
				noButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				noButton.setIcon(nobuttonImage); //마우스 눌렀을 때 이미지
				noButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
				System.exit(0); // 게임에서 나가짐
			}
		});
        background.add(noButton);
    }
    
    
    

    public static void main(String[] args) {
        Main frame = new Main();
    }
}
