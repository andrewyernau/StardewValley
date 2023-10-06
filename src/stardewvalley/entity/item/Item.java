package stardewvalley.entity.item;

public class Item {
    private String name;
    private String description;
    private int quantity; // Store value


    public Item(String name) {
        this.name = name;
        this.description = "item description";
        this.quantity = 0; // Initialize item starting at 0;
    }

    // Getters y setters for name, description y quantity

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(int amount) {
        quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        if (quantity == 0) {
            quantity = 0;
        } else if (quantity >= amount) {
            quantity -= amount;
        } else {
            quantity = 0;
        }
    }
}
