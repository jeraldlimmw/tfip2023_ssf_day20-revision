package B2API.QuotationAPI.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import B2API.QuotationAPI.model.Quotation;
import B2API.QuotationAPI.model.Quote;
import B2API.QuotationAPI.repository.QuoteRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;

@Service
public class QuoteService {
    
    @Autowired
    private QuoteRepository qRepo;

    public Quote getQuote(List<String> itemList){ 
        List<Quotation> q = new LinkedList<>();
        for (String i : itemList) {
            String unitPriceString = qRepo.getUnitPrice(i);
            if (unitPriceString != null) {
                Quotation qt = new Quotation(i, unitPriceString);
                q.add(qt);
            } else {
                continue;
            }
        }
        Quote quote = new Quote();
        quote.setQuotations(q);
        return quote;
    }

    public List<String> getItemList(String json) throws IOException{
        List<String> itemList = new ArrayList<>();
        if(json != null) {
            try(InputStream is = new ByteArrayInputStream(json.getBytes())) {
                JsonReader r = Json.createReader(is);
                JsonArray a = r.readArray();
                                
                for(int i=0; i < a.size(); i++) {
                    itemList.add(a.getString(i));
                }
            }
        }
        return itemList;
    }
}
