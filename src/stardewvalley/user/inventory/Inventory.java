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
        if (!items.contains(item)) {
            items.add(item);
            item.setQuantity(1);
        } else {
            item.increaseQuantity(1);
        }
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
                        // Reduce by one the item
                        item.setQuantity(currentQuantity - 1);
                    } else {
                        // If only one, remove the whole item
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

    public void listItems() {
        System.out.println("List of items:");
        for (Item item : items) {
            System.out.println("Item: " + item + ", amount: " + item.getQuantity());
        }
    }


}
