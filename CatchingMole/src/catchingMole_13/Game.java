package catchingMole_13;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import catchingMole_12.CatchingMole;

public class Game extends Frame{
	
	 public Game(){
		 initializeComponents();
	 }
	 
	//초기화 코드 작성
	public void initializeComponents() {
		//배경 설정
        background = new JLabel(new ImageIcon(Main.class.getResource("../images/gameStartScreen.png"))); // 배경이미지
        background.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        add(background);
	}
	 
	public static void main(String[] args) {
		Game frame = new Game();
    }
	
	
	
}
