package catchingMole_17;

public class CoinData {
    private int coins;

    public CoinData(int coins) {
        this.coins = coins;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addCoins(int amount) {
        coins += amount;
    }

    public boolean subtractCoins(int amount) {
        if (coins >= amount) {
            coins -= amount;
            return true;
        } else {
            return false; // 코인이 부족하면 false 반환
        }
    }
}
