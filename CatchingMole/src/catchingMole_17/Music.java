package catchingMole_17;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
	
	private Player player; //javazoom 라이브러리
	private boolean isLoop; // 무한반복 설정
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/"+name).toURI());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public int getTime() {
		if (player == null)
			return 0;
		return player.getPosition();
		
	}
	
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt(); //노래 종료(노래 실행하는 프로그램)
	}
	@Override
	public void run() {
	    try {
	        do {
	            fis = new FileInputStream(file);
	            bis = new BufferedInputStream(fis);
	            player = new Player(bis);
	            player.play();
	        } while (isLoop);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	}

	
}
