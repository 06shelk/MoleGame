package catchingMole_17;

public class CoinData {
    private int coins;

    public CoinData(int coins) {
        this.coins = coins;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int amount) {
        coins += amount;
    }

    public void subtractCoins(int amount) {
        coins -= amount;
    }
}
