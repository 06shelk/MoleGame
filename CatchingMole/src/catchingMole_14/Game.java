package catchingMole_14;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;


public class Game extends Frame{
	private static final String IMAGE_PATH = "../images/";
	private JProgressBar timerBar; // 타이머 바
	private Timer gameTimer; // 게임 타이머
	private ImageIcon grassImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "grass.png")); //잔디라는 이미지를 읽고 처리, Image객체 넘김
	private ImageIcon resetButtonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "resetButtonBasic.png")));// out버튼 이미지 기본
	private ImageIcon resetButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "resetButtonEntered.png")));// out버튼 이미지 클릭
	
	
	int[][] grassPositions = { 	//잔디 위치를 정리
            {200, 230}, {530, 230}, {850, 230},
            {200, 380}, {530, 380}, {850, 380},
            {200, 530}, {530, 530}, {850, 530}
        };
	
	int[][] molePositions = { 	//두더지 위치를 정리
            {230, 110}, {560, 110}, {880, 110},
            {230, 290}, {560, 290}, {880, 290},
            {230, 460}, {560, 460}, {880, 460}
        };
	
	 public Game(){
		 initializeComponents();
		 displayGrass();
		 setupGameTimer();
	 }
	 
	//초기화 코드 작성
	public void initializeComponents() {
		//배경 설정
        background = new JLabel(new ImageIcon(Main.class.getResource(IMAGE_PATH + "gameScreen.png"))); // 배경이미지
        background.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        add(background);
        
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
      	
      	//망치 아이템 버튼(황금두더지만 나옴)
        JButton hammerItem = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH + "hammerItem.png")));
        hammerItem.setBounds(1130, 45, 36, 52); //버튼 위치
        hammerItem.setBorderPainted(false); // 버튼 테두리 설정
        hammerItem.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
        hammerItem.setFocusPainted(false); //포커스 표시 설정
      	background.add(hammerItem);
      	
      	// 망치 아이템 버튼 label
        JLabel hammerItemLabel = new JLabel("0");
        hammerItemLabel.setBounds(1140, 100, 50, 50); // 이미지 라벨 위치 및 크기 설정
        hammerItemLabel.setFont(new Font("나눔 고딕", Font.BOLD, 20));
        hammerItemLabel.setVisible(true); // 초기에는 이미지를 보이지 않도록 설정
        background.add(hammerItemLabel);
      	
      	//시간 아이템 버튼(시간이 늘어남)
        JButton hourItem = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH + "hourItem.png")));
        hourItem.setBounds(1000, 45, 51, 53); //버튼 위치
        hourItem.setBorderPainted(false); // 버튼 테두리 설정
        hourItem.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
        hourItem.setFocusPainted(false); //포커스 표시 설정
      	background.add(hourItem);
      	
      	// 망치 아이템 버튼 label
        JLabel hourItemLabel = new JLabel("0");
        hourItemLabel.setBounds(1020, 100, 50, 50); // 이미지 라벨 위치 및 크기 설정
        hourItemLabel.setFont(new Font("나눔 고딕", Font.BOLD, 20));
        hourItemLabel.setVisible(true); // 초기에는 이미지를 보이지 않도록 설정
        background.add(hourItemLabel);
      	
      	
      	//시간 아이템 버튼(시간이 늘어남)
        JButton questionItem = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH + "questionItem.png")));
        questionItem.setBounds(870, 45, 68, 47); //버튼 위치
        questionItem.setBorderPainted(false); // 버튼 테두리 설정
        questionItem.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
        questionItem.setFocusPainted(false); //포커스 표시 설정
      	background.add(questionItem);
      	
      	// 망치 아이템 버튼 label
        JLabel questionItemLabel = new JLabel("0");
        questionItemLabel.setBounds(900, 100, 50, 50); // 이미지 라벨 위치 및 크기 설정
        questionItemLabel.setFont(new Font("나눔 고딕", Font.BOLD, 20));
        questionItemLabel.setVisible(true); // 초기에는 이미지를 보이지 않도록 설정
        background.add(questionItemLabel);
                
	}
	
	//잔디 설치 코드 작성
	public void displayGrass() {
		for (int i = 0; i < grassPositions.length; i++) {
			int x = grassPositions[i][0];
	        int y = grassPositions[i][1];
	            
	        // 잔디 이미지 라벨 생성
	        JLabel grassLabel = new JLabel(grassImage);
	        grassLabel.setBounds(x, y, grassImage.getIconWidth(), grassImage.getIconHeight());
	        background.add(grassLabel);
	    }
	}
	
	
	//타이머 설치 코드 작성
	public void setupGameTimer() {
		// 타이머
		timerBar = new JProgressBar(JProgressBar.VERTICAL); // 세로 방향으로 설정
		timerBar.setBounds(1149, 250, 20, 300); // 위치와 크기 조정
		timerBar.setForeground(Color.RED); // 원하는 색상으로 변경
		timerBar.setBorder(new LineBorder(Color.BLACK, 3)); // 테두리 색상과 굵기 설정
		timerBar.setValue(100); // 초기 값 설정 (100%로 시작)
		timerBar.setStringPainted(false); // 백분율 표시 비활성화
		timerBar.setVisible(true);
        background.add(timerBar);
        
	}
	
	 
	public static void main(String[] args) {
		Game frame = new Game();
    }
	
	
	
}
