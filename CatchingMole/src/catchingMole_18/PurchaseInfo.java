// PurchaseInfo.java
package catchingMole_18;

public class PurchaseInfo {
    public int item1Count;
    private int item2Count;
    private int item3Count;
    private boolean doubleMolePurchased;
    private boolean loseMolePurchased;
    private boolean rainbowMolePurchased;

    public PurchaseInfo() {
        // 초기값 설정 (원하는 초기값으로 설정 가능)
        item1Count = 0;
        item2Count = 0;
        item3Count = 0;
        doubleMolePurchased = false;
        loseMolePurchased = false;
        rainbowMolePurchased = false;
    }

    // Getter 및 Setter 메서드
    // 필요한 경우 구매 정보를 반환하거나 설정하는 메서드를 추가할 수 있습니다.
    
    public int getItem1Count() {
        return item1Count;
    }

    public void setItem1Count(int item1Count) {
        this.item1Count = item1Count;
    }

    // item2Count, item3Count 및 다른 필드에 대한 Getter 및 Setter도 추가할 수 있습니다.
}
