package hamster;

public class Hamster {
    private int foodNeeded;
    private int greed;

    Hamster(int foodNeeded, int greed) {
        this.foodNeeded = foodNeeded;
        this.greed = greed;
    }

    public int getFoodNeeded() {
        return foodNeeded;
    }

    public void setFoodNeeded(int foodNeeded) {
        this.foodNeeded = foodNeeded;
    }

    public int getGreed() {
        return greed;
    }

    public void setGreed(int greed) {
        this.greed = greed;
    }

    @Override
    public String toString() {
        return "hamster.Hamster{" +
                "foodNeeded=" + foodNeeded +
                ", greed=" + greed +
                '}';
    }
}