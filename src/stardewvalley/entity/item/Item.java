package stardewvalley.entity.item;

public class Item {
    private String name;
    private String description;
    private int quantity; // Agrega un campo para almacenar la cantidad

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.quantity = 0; // Inicializa la cantidad en 0 al crear un nuevo objeto Item
    }

    // Getters y setters para name, description y quantity

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
}
