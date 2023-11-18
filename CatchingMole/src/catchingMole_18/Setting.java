package catchingMole_18;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import catchingMole_12.Main;

public class Setting extends Frame { // 자신의 기록, 로그아웃
	private static final String IMAGE_PATH = "../images/";
	private ImageIcon logoutButtonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "logoutButton.png"))); // 로그인버튼 이미지 기본
	private ImageIcon logoutButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "logoutButtonEntered.png"))); // 로그인버튼 이미지 클릭
	private ImageIcon resetButtonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "resetButtonBasic.png")));// out버튼 이미지 기본
	private ImageIcon resetButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "resetButtonEntered.png")));// out버튼 이미지 클릭
	JTextArea textArea;
	JLabel bestLabel;
	
	 public Setting(){
		 initializeComponents();
		 loadTextAreaContentFromFile();
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
				new LoginScreen();
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
	  	bestLabel = new JLabel("최고기록 : 0"); // 텍스트필드 초기화
	  	bestLabel.setFont(new Font("나눔 고딕", Font.BOLD, 20));
	  	bestLabel.setBounds(570, 210, 150, 30);
    	background.add(bestLabel);
    	
    	textArea = new JTextArea();
    	textArea.setFont(new Font("나눔고딕", Font.BOLD, 24));

    	// JScrollPane를 사용하여 JTextArea를 감싸서 스크롤 가능하게 만듦
    	JScrollPane scrollPane = new JScrollPane(textArea);
    	scrollPane.setBounds(400, 250, 480, 250); // JScrollPane의 위치 및 크기 설정
    	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // 수직 스크롤바 설정
    	scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // 수평 스크롤바 설정
    	background.add(scrollPane); // JScrollPane를 컨테이너에 추가

    	// JTextArea의 위치 및 크기 설정 및 읽기 전용으로 설정할 필요 없음
    	// textArea.setBounds(0, 0, 480, 250); // 주석 처리 또는 삭제
    	// textArea.setEditable(false); // 주석 처리 또는 삭제

    	// Display the score results with dates
    	StringBuilder textContent = new StringBuilder(); // 표시할 텍스트 내용을 저장할 StringBuilder
    	textArea.setEditable(false);
    	textArea.setText(textContent.toString()); // 텍스트 영역에 내용 설정
	}
	
	private void loadTextAreaContentFromFile() {
	    try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))) {
	        StringBuilder content = new StringBuilder();
	        String line;
	        int highestScore = Integer.MIN_VALUE; // 초기값을 가장 작은 값으로 설정
	        String loggedInUsername = LoginScreen.loggedInUsername; // 현재 로그인한 사용자 이름

	        boolean recordFound = false; // 기록이 발견되었는지 여부를 나타내는 플래그

	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(":");
	            if (parts.length == 3) { // 수정된 부분: 사용자 이름이 추가되었으므로 3이어야 함
	                String username = parts[1].trim();
	                String scoreString = parts[2].trim();
	                int score = Integer.parseInt(scoreString);

	                // 현재 로그인한 사용자와 기록의 사용자 이름이 일치하는 경우
	                if (loggedInUsername.equals(username)) {
	                    content.append(line).append("\n");
	                    recordFound = true; // 기록이 발견되었음을 표시
	                    
	                    // 현재까지의 최고 점수보다 큰 경우 갱신
		                if (score > highestScore) {
		                    highestScore = score;
		                }
	                }

	                
	            }
	        }

	        if (!recordFound) {
	            content.append("결과가 없습니다.\n");
	        }

	        textArea.setText(content.toString());
	        bestLabel.setText("최고기록: " + (recordFound ? highestScore : 0));
	    } catch (IOException | NumberFormatException e) {
	        e.printStackTrace();
	    }
	}

	
	public static void main(String[] args) {
		Setting frame = new Setting();
    }
}
