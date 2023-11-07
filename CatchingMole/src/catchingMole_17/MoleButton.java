package catchingMole_17;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MoleButton extends JButton {
    public MoleButton(ImageIcon icon, int x, int y) {
        super(icon);
        setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setVisible(false);
    }

    public void showMole() {
        setVisible(true);
    }

    public void hideMole() {
        setVisible(false);
    }
}
