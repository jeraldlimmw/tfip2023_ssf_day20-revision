package QuotationApp.day20revision.model;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Item implements Serializable{
    
    @NotNull(message="Item must not be null")
    @NotBlank(message="Must select an item")
    private String itemName;

    @Min(value=1, message="You must add at least 1 item")
    private int quantity;

    public Item() {
    }

    public Item(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
