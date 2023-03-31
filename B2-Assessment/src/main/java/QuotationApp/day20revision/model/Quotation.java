package QuotationApp.day20revision.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Quotation implements Serializable{

    private String quoteId;
    private List<Quote> quotations;

    public String getQuoteId() {
        return quoteId;
    }
    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }
    public List<Quote> getQuotations() {
        return quotations;
    }
    public void setQuotations(List<Quote> quotations) {
        this.quotations = quotations;
    }
   
    public static Quotation create(String json, List<String> items) throws Exception {
        Quotation quotation = new Quotation();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();

            quotation.setQuoteId(o.getString("quoteId"));
            List<Quote> quoteList = new ArrayList<>();

            JsonArray j = o.getJsonArray("quotations");
            for(int i = 0; i < j.size(); i++) {
                JsonObject jo = j.getJsonObject(i);
                Quote q = new Quote(items.get(i), Double.parseDouble(jo.getString(items.get(i))));
                quoteList.add(q);
            }
            quotation.setQuotations(quoteList);

        return quotation;
        }
    }

}
