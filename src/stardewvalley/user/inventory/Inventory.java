package stardewvalley.user.inventory;

import stardewvalley.entity.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void removeOneItemOfType(Item itemToRemove) {
        if (itemToRemove != null) {
            // Find item
            for (Item item : items) {
                if (item.getClass().equals(itemToRemove.getClass())) {
                    int currentQuantity = item.getQuantity();
                    if (currentQuantity > 1) {
                        // Si hay más de 1, reduce la cantidad en 1
                        item.setQuantity(currentQuantity - 1);
                    } else {
                        // Si solo hay 1, elimina el item del inventario
                        items.remove(item);
                    }
                    return; // Termina el método después de realizar la operación
                }
            }
        } else {
            //Did not find item
            System.out.println("Could not find Item.");
        }
    }


}
