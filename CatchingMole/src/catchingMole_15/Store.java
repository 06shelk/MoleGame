package catchingMole_15;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Store extends Frame{ //상점
	private static final String IMAGE_PATH = "../images/";
	
	// 버튼 이미지
	private ImageIcon resetButtonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "resetButtonBasic.png")));// out버튼 이미지 기본
	private ImageIcon resetButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "resetButtonEntered.png")));// out버튼 이미지 클릭
	private ImageIcon storeOkButtonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "storeOkButton.png"))); // 상점 구매 버튼 이미지 기본
	private ImageIcon storeOkButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "storeOkButtonEntered.png"))); // 상점 구매 버튼 이미지 기본
	private ImageIcon okButtonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "okButton.png")));// 다음버튼 이미지 기본
	private ImageIcon okButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "okButtonEntered.png")));// 다음버튼 이미지 클릭
	
	private ImageIcon storeCancelButtonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "storeCancelButton.png"))); // 상점 구매 버튼 이미지 기본
	private ImageIcon storeCancelButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "storeCancelButtonEntered.png"))); // 상점 구매 버튼 이미지 기본
	
	
	
	//구매하시겠습니까? 이미지
	private ImageIcon item1BuyImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "item1Buy.png"));
	private ImageIcon item2BuyImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "item2Buy.png"));
	private ImageIcon item3BuyImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "item3Buy.png"));
	private ImageIcon doubleMoleBuyImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "doubleMoleBuy.png"));
	private ImageIcon loseMoleBuyImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "loseMoleBuy.png"));
	private ImageIcon rainbowMoleBuyImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "rainbowMoleBuy.png"));
	
	private ImageIcon soldoutImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "sold.png"));
	private ImageIcon coinlackImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "coinlack.png"));
	
	//버튼 선언
	private JButton storeOkButton, storeCancelButton; // 구매 확인 버튼 선언
	private JButton questionItem, hourItem, hammerItem, molelosing, moleDouble, moleRainbow; // 게임 아이템 선언
	private JButton outButton, okButton; //나가기 버튼
	
	//프로필 두더지
	public boolean itemPro1,itemPro2, itemPro3 = false;
	
	int coin = 5;
	
	// 코인 감소 이미지 위치
	int[][] coinPositions = { 	//코인 위치를 정리
			{430, 355}, {630, 355}, {830, 355},
            {425, 540}, {625, 540}, {825, 540}
	};
	
	// 판매완료 이미지 위치
	int[][] soldoutPositions = { 	//코인 위치를 정리
			{400, 360}, {600, 360}, {800, 360},
			{395, 545}, {595, 545}, {795, 545}
	};
	
	
	 public Store(){
		 initializeComponents();
	 }
	 
	 //JButton 공통되는 부분 제외
	 private JButton createButton(String imageName, int x, int y) {
		 JButton button = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH + imageName)));
		 button.setBounds(x, y, button.getIcon().getIconWidth(), button.getIcon().getIconHeight());
		 button.setBorderPainted(false);
		 button.setContentAreaFilled(false);
		 button.setFocusPainted(false);
		 return button;
	}
	 
	//초기화 코드 작성
	public void initializeComponents() {
		//배경 설정
        background = new JLabel(new ImageIcon(Main.class.getResource(IMAGE_PATH + "storeScreen.png"))); // 배경이미지
        background.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        add(background);
        
        // 현재 사용자 코인 label
        JLabel itemcoinLabel = new JLabel(Integer.toString(coin)); // 텍스트필드 초기화
        itemcoinLabel.setFont(new Font("나눔 고딕", Font.BOLD, 25));
        itemcoinLabel.setBounds(1100, 100, 150, 30);
    	background.add(itemcoinLabel);
        
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
    	
    	//soldout
    	JLabel[] soldoutLabels = new JLabel[coinPositions.length];
    	  
    	for (int i = 0; i < soldoutPositions.length; i++) {
			int x = soldoutPositions[i][0];
	        int y = soldoutPositions[i][1];
	        // soldout 이미지 라벨 생성
	        soldoutLabels[i] = new JLabel(soldoutImage); // 텍스트필드 초기화
	        soldoutLabels[i].setVisible(false);
	        soldoutLabels[i].setFont(new Font("나눔 고딕", Font.BOLD, 25));
	        soldoutLabels[i].setBounds(x, y, 100, 48);
	        background.add(soldoutLabels[i]);
	    }
    	
    	
    	
    	// 구매 의견 띄우기
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 1280, 720); // 이미지 라벨 위치 및 크기 설정
        imageLabel.setVisible(true);
        
    	
        //나가기 버튼
        outButton = createButton("resetButtonBasic.png", 100, 80);
        outButton.setVisible(true);
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
	    
	    //나가기 버튼
        okButton = createButton("okButton.png", 563, 550);
        
        okButton.setVisible(false);
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
				imageLabel.setVisible(false);
				okButton.setVisible(false);
			}
		});
	    background.add(okButton);
	    background.setComponentZOrder(okButton, 4);
	    
	    // 구매 확인 버튼
	    storeOkButton = createButton("storeOkButton.png", 340, 500);
	    storeOkButton.setVisible(false);
	    storeCancelButton = createButton("storeCancelButton.png", 800, 500);
	    storeCancelButton.setVisible(false);
	    
	    //질문 아이템 버튼(모든 두더지 나옴)
	    questionItem = createButton("questionItem.png", 420, 300);
	    questionItem.setVisible(true);
	    
	    //시간 아이템 버튼(시간이 늘어남)
        hourItem = createButton("hourItem.png", 620, 300);
        hourItem.setVisible(true);
        
        //망치 아이템 버튼(황금두더지만 나옴)
        hammerItem = createButton("hammerItem.png", 820, 300);
        hammerItem.setVisible(true);
       
        // 벌칙 두더지
        molelosing = createButton("moleButtonGameMinus.png", 400, 420);
        molelosing.setVisible(true);
        
        // 황금 두더지
        moleDouble = createButton("moleButtonGameDouble.png", 600, 420);
        moleDouble.setVisible(true);
        
        // 알록달록 두더지
        moleRainbow = createButton("rainbowMoleButton.png", 800, 420);
        moleRainbow.setVisible(true);
        
        
        //이벤트 리스너
        questionItem.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
	    	@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때	
	    		questionItem.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				questionItem.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
		        imageLabel.setIcon(item1BuyImage);
		        // 프레임에 이미지 라벨 추가
		        background.add(imageLabel);
		        background.setComponentZOrder(imageLabel, 0);
		        background.setComponentZOrder(storeOkButton, 0);
		        background.setComponentZOrder(storeCancelButton, 0);
		        imageLabel.setVisible(true);
		        storeOkButton.setVisible(true); // 구매 버튼 보이게
		        storeCancelButton.setVisible(true); // 구매 버튼 보이게
			}
		});
      	background.add(questionItem);
   	
      	
        hourItem.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
	    	@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때	
	    		hourItem.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				hourItem.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
		        imageLabel.setIcon(item2BuyImage);
		        // 프레임에 이미지 라벨 추가
		        background.add(imageLabel);
		        background.setComponentZOrder(imageLabel, 0);
		        background.setComponentZOrder(storeOkButton, 0);
		        background.setComponentZOrder(storeCancelButton, 0);
		        imageLabel.setVisible(true);
		        storeOkButton.setVisible(true); // 구매 버튼 보이게
		        storeCancelButton.setVisible(true); // 구매 버튼 보이게
			}
		});
      	background.add(hourItem);
      	
      	//망치 아이템 버튼(황금두더지만 나옴)
        hammerItem.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
	    	@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때	
	    		hammerItem.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				hammerItem.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
		        imageLabel.setIcon(item3BuyImage);
		        // 프레임에 이미지 라벨 추가
		        background.add(imageLabel);
		        background.setComponentZOrder(imageLabel, 0);
		        background.setComponentZOrder(storeOkButton, 0);
		        background.setComponentZOrder(storeCancelButton, 0);
		        imageLabel.setVisible(true);
		        storeOkButton.setVisible(true); // 구매 버튼 보이게
		        storeCancelButton.setVisible(true); // 구매 버튼 보이게
			}
		});
      	background.add(hammerItem);
      	
       	
      	molelosing.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
	    	@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때	
	    		molelosing.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				molelosing.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
		        imageLabel.setIcon(loseMoleBuyImage);
		        // 프레임에 이미지 라벨 추가
		        background.add(imageLabel);
		        background.setComponentZOrder(imageLabel, 0);
		        background.setComponentZOrder(storeOkButton, 0);
		        background.setComponentZOrder(storeCancelButton, 0);
		        imageLabel.setVisible(true);
		        storeOkButton.setVisible(true); // 구매 버튼 보이게
		        storeCancelButton.setVisible(true); // 구매 버튼 보이게
		        
		        itemPro1 = true;
			}
		});
       	background.add(molelosing);
       	
      	
      	moleDouble.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
	    	@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때	
	    		moleDouble.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				moleDouble.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
		        imageLabel.setIcon(doubleMoleBuyImage);
		        // 프레임에 이미지 라벨 추가
		        background.add(imageLabel);
		        background.setComponentZOrder(imageLabel, 0);
		        background.setComponentZOrder(storeOkButton, 0);
		        background.setComponentZOrder(storeCancelButton, 0);
		        imageLabel.setVisible(true);
		        storeOkButton.setVisible(true); // 구매 버튼 보이게
		        storeCancelButton.setVisible(true); // 구매 버튼 보이게

		        itemPro2 = true;
			}
		});
       	background.add(moleDouble);
       	
       	
       	moleRainbow.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
	    	@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때	
	    		moleRainbow.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				moleRainbow.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
				// 구매 의견 띄우기
				imageLabel.setIcon(rainbowMoleBuyImage);
		        // 프레임에 이미지 라벨 추가
		        background.add(imageLabel);
		        background.setComponentZOrder(imageLabel, 0);
		        background.setComponentZOrder(storeOkButton, 0);
		        background.setComponentZOrder(storeCancelButton, 0);
		        imageLabel.setVisible(true);
		        storeOkButton.setVisible(true); // 구매 버튼 보이게
		        storeCancelButton.setVisible(true); // 구매 버튼 보이게
		        
		        itemPro3 = true;
			}
		});
       	background.add(moleRainbow);
		
       	// storeOkButton 이벤트리스너
       	storeOkButton.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
	    	@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때	
	    		storeOkButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
	    		storeOkButton.setIcon(storeOkButtonEnteredImage);
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				storeOkButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
				storeOkButton.setIcon(storeOkButtonImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
				imageLabel.setVisible(false);
		        storeOkButton.setVisible(false); // 구매 버튼 보이게
		        storeCancelButton.setVisible(false); // 구매 버튼 보이기
		        
		        if (itemPro3 && coin >= 5) {
		            coin = coin - 5;
		            soldoutLabels[5].setVisible(true);
		            background.setComponentZOrder(soldoutLabels[5], 2);
		            itemcoinLabel.setText(Integer.toString(coin));
		        } else if(itemPro3 && coin < 5) {
		        	imageLabel.setIcon(coinlackImage);
		        	imageLabel.setVisible(true);
		        	okButton.setVisible(true);
		        } else if(itemPro2 && coin >= 3) {
		        	coin = coin - 3;
		            soldoutLabels[4].setVisible(true);
		            background.setComponentZOrder(soldoutLabels[4], 2);
		            itemcoinLabel.setText(Integer.toString(coin));
		        } else if(itemPro3 && coin < 3) {
		        	imageLabel.setIcon(coinlackImage);
		        	imageLabel.setVisible(true);
		        	okButton.setVisible(true);
		        }
			}
		});
       	background.add(storeOkButton);
       	
       	// cancel버튼 이벤트리스너
       	storeCancelButton.addMouseListener(new MouseAdapter() { // 닫기 버튼 이벤트 
	    	@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때	
	    		storeCancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양으로 바뀜
	    		storeCancelButton.setIcon(storeCancelButtonEnteredImage);
			}
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 나갔을 때
				storeCancelButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
				storeCancelButton.setIcon(storeCancelButtonImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {//마우스를 눌렀을 때
				imageLabel.setVisible(false);
		        storeOkButton.setVisible(false); // 구매 버튼 안보이게
		        storeCancelButton.setVisible(false); // 구매 버튼 안보이게
			}
		});
       	background.add(storeCancelButton);
       	
	}
	 
	public static void main(String[] args) {
		Store frame = new Store();
    }

}
