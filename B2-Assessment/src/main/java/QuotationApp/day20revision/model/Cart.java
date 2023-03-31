package QuotationApp.day20revision.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;

public class Cart implements Serializable{
    
    private List<Item> contents;

    public Cart() {
        this.contents = new LinkedList<>();
    }

    public List<Item> getContents() {
        return contents;
    }

    public void setContents(List<Item> contents) {
        this.contents = contents;
    }

    public void addItem(Item i) {
        for(Item x : contents) {
            if (x.getItemName().equalsIgnoreCase(i.getItemName())){
                x.setQuantity(x.getQuantity() + i.getQuantity());
                return;
            }
        }
        this.contents.add(i);
    }

    public List<String> getItemList(){
        List<String> items = new ArrayList<>();
        for (Item x : contents) {
            items.add(x.getItemName());
        }
        return items;
    }

    public static JsonArray toJsonArray(List<String> items) {
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (String i : items) {
            arrBuilder.add(i);
        }
        return arrBuilder.build();
    }
}
