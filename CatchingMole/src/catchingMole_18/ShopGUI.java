package catchingMole_18;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ShopGUI extends JFrame implements ActionListener {
    private static final String IMAGE_PATH = "../images/";
    private int coins = 5; // 초기 코인 수
    
    private boolean doubleMolePurchased = false;
    private boolean loseMolePurchased = false;
    private boolean rainbowMolePurchased = false;

    private ImageIcon item1BuyImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "questionItem.png"));
    private ImageIcon item2BuyImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "hourItem.png"));
    private ImageIcon item3BuyImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "hammerItem.png"));
    private ImageIcon doubleMoleBuyImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "moleButtonGameMinus.png"));
    private ImageIcon loseMoleBuyImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "moleButtonGameDouble.png"));
    private ImageIcon rainbowMoleBuyImage = new ImageIcon(Main.class.getResource(IMAGE_PATH + "rainbowMoleButton.png"));

    private JLabel coinLabel;

    public ShopGUI() {
        
        
        setLayout(null); // null 레이아웃 설정

        coinLabel = new JLabel("남은 코인: " + coins);
        coinLabel.setBounds(20, 20, 100, 30); // 레이블의 위치와 크기 설정
        add(coinLabel);

        // 버튼들의 위치와 크기를 직접 설정
        JButton item1Button = new JButton(item1BuyImage);
        item1Button.setBounds(20, 70, 100, 100);
        item1Button.addActionListener(this);
        add(item1Button);

        JButton item2Button = new JButton(item2BuyImage);
        item2Button.setBounds(150, 70, 100, 100);
        item2Button.addActionListener(this);
        add(item2Button);

        JButton item3Button = new JButton(item3BuyImage);
        item3Button.setBounds(280, 70, 100, 100);
        item3Button.addActionListener(this);
        add(item3Button);

        JButton doubleMoleButton = new JButton(doubleMoleBuyImage);
        doubleMoleButton.setBounds(20, 200, 100, 100);
        doubleMoleButton.addActionListener(this);
        add(doubleMoleButton);

        JButton loseMoleButton = new JButton(loseMoleBuyImage);
        loseMoleButton.setBounds(150, 200, 100, 100);
        loseMoleButton.addActionListener(this);
        add(loseMoleButton);

        JButton rainbowMoleButton = new JButton(rainbowMoleBuyImage);
        rainbowMoleButton.setBounds(280, 200, 100, 100);
        rainbowMoleButton.addActionListener(this);
        add(rainbowMoleButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 350); // 프레임 크기 설정
        setVisible(true); // 프레임 표시
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
                    coinLabel.setText("남은 코인: " + coins);
                    // 여기에 아이템을 처리하는 코드를 추가할 수 있습니다.
                    if (selectedItemImage == doubleMoleBuyImage) {
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
    	ShopGUI frame = new ShopGUI();
    }
}
