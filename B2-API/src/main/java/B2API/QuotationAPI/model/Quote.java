package B2API.QuotationAPI.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class Quote implements Serializable{
    
    private String quoteId;
    private List<Quotation> quotations;

    public Quote(){
        this.quoteId = generateId(8);
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public List<Quotation> getQuotations() {
        return quotations;
    }

    public void setQuotations(List<Quotation> quotations) {
        this.quotations = quotations;
    }

    public String generateId(int numChar){
        String orderId = UUID.randomUUID().toString().substring(0, numChar);
        return orderId;
    }

    public JsonObject toJSON(){
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Quotation q : this.getQuotations()) {
            arrBuilder.add(q.toJSON());
        }
        
        return Json.createObjectBuilder()
            .add("quoteId", this.getQuoteId())
            .add("quotations", arrBuilder)
            .build();
    }

}
