package catchingMole_17;


import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Store extends Frame implements ActionListener {
	 private static final String IMAGE_PATH = "../images/";
	private static CoinData coindata;
	 private int coins = 5; // 초기 코인 수
	 
	 private int item1Count = 0;
	 private int item2Count = 0;
	 private int item3Count = 0;
	 private boolean doubleMolePurchased = false;
	 private boolean loseMolePurchased = false;
	 private boolean rainbowMolePurchased = false;

	 private ImageIcon item1BuyImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "questionItem.png"));
	 private ImageIcon item2BuyImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "hourItem.png"));
	 private ImageIcon item3BuyImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "hammerItem.png"));
	 private ImageIcon doubleMoleBuyImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "moleButtonGameMinus.png"));
	 private ImageIcon loseMoleBuyImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "moleButtonGameDouble.png"));
	 private ImageIcon rainbowMoleBuyImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "rainbowMoleButton.png"));
	 private ImageIcon resetButtonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "resetButtonBasic.png")));// out버튼 이미지 기본
	 private ImageIcon resetButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "resetButtonEntered.png")));// out버튼 이미지 클릭
		
	 private JLabel coinLabel;
	 
	// 코인 감소 이미지 위치
	int[][] coinPositions = { 	//코인 위치를 정리
			{430, 360}, {630, 360}, {830, 360},
	        {425, 555}, {625, 555}, {825, 555}
	};
	 
	 public Store(CoinData coindata){
		 initializeComponents();
	 }
	 
	 
	//초기화 코드 작성
	public void initializeComponents() {
		//배경 설정
        background = new JLabel(new ImageIcon(Main.class.getResource("../images/storeScreen.png"))); // 배경이미지
        background.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        add(background);
        
        coinLabel = new JLabel(Integer.toString(coins));
        coinLabel.setFont(new Font("나눔 고딕", Font.BOLD, 25));
        coinLabel.setBounds(1100, 100, 150, 30); // 레이블의 위치와 크기 설정
        background.add(coinLabel);

        

        // 버튼들의 위치와 크기를 직접 설정
        JButton item1Button = new JButton(item1BuyImage);
        item1Button.setBounds(400, 280, 100, 100);
        item1Button.addActionListener(this);
        item1Button.setBorderPainted(false);
        item1Button.setContentAreaFilled(false);
        item1Button.setFocusPainted(false);
        background.add(item1Button);

        JButton item2Button = new JButton(item2BuyImage);
        item2Button.setBounds(600, 280, 100, 100);
        item2Button.addActionListener(this);
        item2Button.setBorderPainted(false);
        item2Button.setContentAreaFilled(false);
        item2Button.setFocusPainted(false);
        background.add(item2Button);

        JButton item3Button = new JButton(item3BuyImage);
        item3Button.setBounds(800, 280, 100, 100);
        item3Button.addActionListener(this);
        item3Button.setBorderPainted(false);
        item3Button.setContentAreaFilled(false);
        item3Button.setFocusPainted(false);
        background.add(item3Button);

        JButton doubleMoleButton = new JButton(doubleMoleBuyImage);
        doubleMoleButton.setBounds(400, 440, 100, 120);
        doubleMoleButton.addActionListener(this);
        doubleMoleButton.setBorderPainted(false);
        doubleMoleButton.setContentAreaFilled(false);
        doubleMoleButton.setFocusPainted(false);
        background.add(doubleMoleButton);

        JButton loseMoleButton = new JButton(loseMoleBuyImage);
        loseMoleButton.setBounds(600, 440, 100, 120);
        loseMoleButton.addActionListener(this);
        loseMoleButton.setBorderPainted(false);
        loseMoleButton.setContentAreaFilled(false);
        loseMoleButton.setFocusPainted(false);
        background.add(loseMoleButton);

        JButton rainbowMoleButton = new JButton(rainbowMoleBuyImage);
        rainbowMoleButton.setBounds(800, 440, 100, 120);
        rainbowMoleButton.addActionListener(this);
        rainbowMoleButton.setBorderPainted(false);
        rainbowMoleButton.setContentAreaFilled(false);
        rainbowMoleButton.setFocusPainted(false);
        background.add(rainbowMoleButton);
        
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

    	// 코인 라벨 배열
    	JLabel[] coinLabels = new JLabel[coinPositions.length];
  
    	for (int i = 0; i < coinPositions.length; i++) {
			int x = coinPositions[i][0];
	        int y = coinPositions[i][1];
	            
	        // 잔디 이미지 라벨 생성
	        coinLabels[i] = new JLabel("-3"); // 텍스트필드 초기화
	        coinLabels[i].setFont(new Font("나눔 고딕", Font.BOLD, 25));
	        coinLabels[i].setBounds(x, y, 50, 50);
	        background.add(coinLabels[i]);
	    }
    	
    	coinLabels[0].setText("-1"); // 두 번째 코인 라벨의 값을 10으로 변경
    	coinLabels[2].setText("-5"); // 두 번째 코인 라벨의 값을 10으로 변경
    	coinLabels[5].setText("-5"); // 두 번째 코인 라벨의 값을 10으로 변경
        
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton) {
            JButton clickedButton = (JButton) source;
            int requiredCoins = 0;
            ImageIcon selectedItemImage = null;
            boolean showConfirmationDialog = false;

            // 클릭된 버튼에 따라 필요한 코인과 아이템 이미지 설정
            if (clickedButton.getIcon() == item1BuyImage) {
                requiredCoins = 1;
                selectedItemImage = item1BuyImage;
                showConfirmationDialog = true;
            } else if (clickedButton.getIcon() == item2BuyImage) {
                requiredCoins = 3;
                selectedItemImage = item2BuyImage;
                showConfirmationDialog = true;
            } else if (clickedButton.getIcon() == item3BuyImage) {
                requiredCoins = 5;
                selectedItemImage = item3BuyImage;
                showConfirmationDialog = true;
            } else if (clickedButton.getIcon() == doubleMoleBuyImage) {
                if (!doubleMolePurchased) {
                    requiredCoins = 3;
                    selectedItemImage = doubleMoleBuyImage;
                    showConfirmationDialog = true;
                } else {
                    JOptionPane.showMessageDialog(this, "이미 구매한 상품입니다.", "알림", JOptionPane.WARNING_MESSAGE);
                }
            } else if (clickedButton.getIcon() == loseMoleBuyImage && !loseMolePurchased) {
                requiredCoins = 3;
                selectedItemImage = loseMoleBuyImage;
                showConfirmationDialog = true;
            } else if (clickedButton.getIcon() == rainbowMoleBuyImage && !rainbowMolePurchased) {
                requiredCoins = 5;
                selectedItemImage = rainbowMoleBuyImage;
                showConfirmationDialog = true;
            }

            if (showConfirmationDialog) {
                // 확인 창을 표시하여 사용자에게 구매 여부 확인
                int confirmResult = JOptionPane.showConfirmDialog(this,
                        "구매하시겠습니까?\n" + "필요한 코인: " + requiredCoins,
                        "구매 확인", JOptionPane.OK_CANCEL_OPTION);

                if (confirmResult == JOptionPane.OK_OPTION && coins >= requiredCoins) {
                    coins -= requiredCoins;
                    coinLabel.setText(Integer.toString(coins));
                    // 여기에 아이템을 처리하는 코드를 추가할 수 있습니다.
                    if (selectedItemImage == item1BuyImage) {
                        item1Count++;

                    } else if (selectedItemImage == item2BuyImage) {
                        item2Count++;
                    } else if (selectedItemImage == item3BuyImage) {
                        item3Count++;
                    } else if (selectedItemImage == doubleMoleBuyImage) {
                        doubleMolePurchased = true;
                    } else if (selectedItemImage == loseMoleBuyImage) {
                        loseMolePurchased = true;
                    } else if (selectedItemImage == rainbowMoleBuyImage) {
                        rainbowMolePurchased = true;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "코인이 부족하거나 구매가 취소되었습니다.",
                            "알림", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
	 
	public static void main(String[] args) {
		Store frame = new Store(coindata);
    }
	
	
	
}