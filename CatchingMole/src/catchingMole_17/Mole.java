package catchingMole_17;

public class Mole {
	 private String type;
	 private int score;
	 private String imagePath;
	 private int appearanceTime; // 두더지가 등장하는 시간 간격 (예: 2000ms)
	 private int x;
	 private int y;
	 private int width;
	 private int height;
	 
	 public int getX() {
	        return x;
	    }

	    public int getY() {
	        return y;
	    }

	    public int getWidth() {
	        return width;
	    }

	    public int getHeight() {
	        return height;
	    }

	    public String getImagePath() {
	        return imagePath;
	    }
	 
	 
	 public Mole(String type, int score, String imagePath, int appearanceTime) {
		 this.type = type;
	     this.score = score;
	     this.imagePath = imagePath;
	     this.appearanceTime = appearanceTime;
	 }
	    
	 public class DoubleMole extends Mole {
	     public DoubleMole() {
	         super("Double Mole", 4, "moleButtonGameDouble.png", 3000); // 4점, 3초 간격으로 등장
	     }
	 }

	 public class BasicMole extends Mole {
	     public BasicMole() {
	         super("Basic Mole", 2, "moleButtonGameBasic.png", 2000); // 2점, 2초 간격으로 등장
	     }
	 }

	 public class LoseMole extends Mole {
	     public LoseMole() {
	         super("Lose Mole", -3, "moleButtonGameMinus.png", 5000); // -3점, 5초 간격으로 등장
	     }
	 }

}
