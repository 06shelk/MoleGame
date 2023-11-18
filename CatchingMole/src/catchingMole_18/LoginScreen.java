package catchingMole_18;

import javax.swing.*;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class LoginScreen extends Frame {
	private static final String IMAGE_PATH = "../images/";
	private ImageIcon loginbuttonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "loginButton.png"))); // 로그인버튼 이미지 기본
	private ImageIcon loginButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH+"loginButtonEntered.png"))); // 로그인버튼 이미지 클릭
	private ImageIcon resetButtonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH+"resetButtonBasic.png")));// out버튼 이미지 기본
	private ImageIcon resetButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH+"resetButtonEntered.png")));// out버튼 이미지 클릭
	private ImageIcon signupButtonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "signupButton.png"))); // 로그인버튼 이미지 기본
	private ImageIcon signupButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH+"signupButtonEntered.png"))); // 로그인버튼 이미지 클릭
	
    private JTextField usernameField;
    private JPasswordField passwordField;
    public static String loggedInUsername; // 스태틱 변수로 사용자 이름 저장
    private static int coins = 0;
    private static final String LOGGED_IN_STATUS_FILE = "../test1/loggedInStatus.txt";

    public LoginScreen() {
    	
    	//배경 설정
        background = new JLabel(new ImageIcon(Main.class.getResource(IMAGE_PATH+"loginScreen.png"))); // 배경이미지
        background.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        add(background);
        placeComponents();
        JButton loginButton = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH+"loginbutton.png")));
        loginButton.setBorderPainted(false); // 버튼 테두리 설정
        loginButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
        loginButton.setFocusPainted(false); //포커스 표시 설정
		
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticate(username, password)) {
                    if (isFirstLogin()) {
                        coins = 10;
                        saveLoginStatus();
                        JOptionPane.showMessageDialog(null, "처음 로그인하셨습니다. 10개의 coins를 받았습니다.");
                    } else {
                        JOptionPane.showMessageDialog(null, "로그인 성공!");
                    }

                    LoginScreen.loggedInUsername = username;
                    dispose();
                    new Lobby();

                } else {
                    JOptionPane.showMessageDialog(null, "로그인 실패. 다시 시도하세요.");
                }
            }
        });

        background.add(loginButton);
        
        loginButton.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
	    	@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때	
	    		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
	    		loginButton.setIcon(loginButtonEnteredImage);
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				loginButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
				loginButton.setIcon(loginbuttonImage);
			}
			
		});
        
        
        
        
        
        JButton signupButton = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH+"signupButton.png")));
        signupButton.setBorderPainted(false); // 버튼 테두리 설정
		signupButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정
		signupButton.setFocusPainted(false); //포커스 표시 설정
		
		signupButton.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
	   
		});
		
		signupButton.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
	    	@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때	
	    		signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
	    		signupButton.setIcon(signupButtonEnteredImage);
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				signupButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
				signupButton.setIcon(signupButtonImage);
			}
			
		});
        
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (signup(username, password)) {
                    JOptionPane.showMessageDialog(null, "회원가입 성공!");
                } else {
                    JOptionPane.showMessageDialog(null, "회원가입 실패. 다시 시도하세요.");
                }
            }
        });
        background.add(signupButton);

        // 로그인 버튼 위치 및 크기 설정
        loginButton.setBounds(420, 480, 198, 67);
        // 회원가입 버튼 위치 및 크기 설정
        signupButton.setBounds(690, 480, 198, 67);

    }

    private void placeComponents() {
        usernameField = new JTextField(20);
        usernameField.setBounds(541, 270, 400, 30);
        background.add(usernameField);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(541, 370, 400, 30);
        background.add(passwordField);
    }

    private boolean authenticate(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("../test2/1.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(",");
                if (userInfo.length == 2 && userInfo[0].equals(username) && userInfo[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean signup(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "사용자 이름과 비밀번호를 입력하세요.");
            return false;
        }

        if (username.equals(password)) {
            JOptionPane.showMessageDialog(null, "사용자 이름과 비밀번호는 같을 수 없습니다.");
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("../test2/1.txt", true))) {
            // 파일에 새로운 사용자 정보 추가
            bw.write(username + "," + password);
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "회원가입 중 오류가 발생했습니다.");
        }
        return false;
    }
    
    private boolean isFirstLogin() {
        try (BufferedReader br = new BufferedReader(new FileReader(LOGGED_IN_STATUS_FILE))) {
            String line = br.readLine();
            return line == null || line.equals("0"); // 파일이 없거나 0이면 처음 로그인
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void saveLoginStatus() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOGGED_IN_STATUS_FILE))) {
            bw.write("1"); // 로그인 상태를 1로 표시
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	LoginScreen frame = new LoginScreen();
    }
}
