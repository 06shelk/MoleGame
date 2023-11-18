package catchingMole_18;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Rank extends Frame {
    private static final String IMAGE_PATH = "../images/";
    private ImageIcon resetButtonImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "resetButtonBasic.png")));
    private ImageIcon resetButtonEnteredImage = (new ImageIcon(Main.class.getResource(IMAGE_PATH + "resetButtonEntered.png")));
    JTextArea textArea;

    public Rank() {
        initializeComponents();
        loadTextAreaContentFromFile();
    }

    public void initializeComponents() {
        background = new JLabel(new ImageIcon(Main.class.getResource(IMAGE_PATH + "rankingScreen.png")));
        background.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        add(background);

        JButton outButton = new JButton(new ImageIcon(Main.class.getResource(IMAGE_PATH + "resetButtonBasic.png")));
        outButton.setBounds(100, 80, 36, 41);
        outButton.setBorderPainted(false);
        outButton.setContentAreaFilled(false);
        outButton.setFocusPainted(false);
        outButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                outButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                outButton.setIcon(resetButtonEnteredImage);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                outButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                outButton.setIcon(resetButtonImage);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
                new Lobby();
            }
        });
        background.add(outButton);

        textArea = new JTextArea();
        textArea.setFont(new Font("나눔고딕", Font.BOLD, 24));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(400, 250, 480, 250);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        background.add(scrollPane);

        StringBuilder textContent = new StringBuilder();
        textArea.setEditable(false);
        textArea.setText(textContent.toString());
    }

    private void loadTextAreaContentFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))) {
            StringBuilder content = new StringBuilder();
            String line;

            Map<String, Integer> userScores = new HashMap<>();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 3) {
                    String username = parts[1].trim();
                    String scoreString = parts[2].trim();
                    int score = Integer.parseInt(scoreString);

                    // 각 사용자별 가장 높은 기록 저장
                    userScores.putIfAbsent(username, 0);
                    userScores.put(username, Math.max(userScores.get(username), score));
                }
            }

            // 사용자별 등수 부여
            Map<String, Integer> sortedUserScores = sortByValue(userScores);
            int rank = 1;
            for (Map.Entry<String, Integer> entry : sortedUserScores.entrySet()) {
                String username = entry.getKey();
                int highestScore = entry.getValue();
                content.append(rank).append("등\n").append(username).append(", ").append(highestScore).append("\n\n");
                rank++;
            }

            if (content.length() == 0) {
                content.append("결과가 없습니다.\n");
            }

            textArea.setText(content.toString());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // 사용자별로 등수 부여를 위한 유틸리티 메서드
    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        map.entrySet().stream()
                .sorted(Map.Entry.<K, V>comparingByValue().reversed())
                .forEachOrdered(entry -> result.put(entry.getKey(), entry.getValue()));
        return result;
    }


    public static void main(String[] args) {
        Rank frame = new Rank();
    }
}
