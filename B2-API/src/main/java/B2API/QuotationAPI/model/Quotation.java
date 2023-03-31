package B2API.QuotationAPI.model;


import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

public class Quotation implements Serializable{
    private String name;
    private String price;

    public Quotation(String name, String price){
        this.name = name;
        this.price = price;
    }

    public JsonObjectBuilder toJSON(){
        return Json.createObjectBuilder().add(this.name, this.price);
    }
}
