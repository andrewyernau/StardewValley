package stardewvalley.user.inventory;

public class Money {
    private float amount;

    public Money(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void add(float value) {
        amount += value;
    }

    public void subtract(float value) {
        amount -= value;
    }

    @Override
    public String toString() {
        return String.format("%.2f", amount); // Formatear la cantidad con 2 decimales
    }
}
