package QuotationApp.day20revision.model;

import java.io.Serializable;

public class Quote implements Serializable{
    private String itemName;
    private double unitPrice;

    public Quote(String itemName, double unitPrice) {
        this.itemName = itemName;
        this.unitPrice = unitPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
