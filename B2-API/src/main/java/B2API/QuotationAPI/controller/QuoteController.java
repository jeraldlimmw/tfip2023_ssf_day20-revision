package B2API.QuotationAPI.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import B2API.QuotationAPI.service.QuoteService;

@RestController
@RequestMapping(path="/api/quotation",
        consumes=MediaType.APPLICATION_JSON_VALUE, 
        produces=MediaType.APPLICATION_JSON_VALUE)
public class QuoteController {
    @Autowired 
    private QuoteService qSvc;

//     private final String[] ITEMS = {"apple", "orange", "bread", "cheese",
//             "chicken", "mineral_water", "instant_noodles"};
//     private final double[] PRICES = {0.3, 0.5, 2.5, 6.7, 11.2, 0.8, 3.4};

    @PostMapping
    public ResponseEntity<String> createQuotation(@RequestBody String json) 
            throws IOException {
        
        List<String> itemList = qSvc.getItemList(json);
        
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(qSvc.getQuote(itemList).toJSON().toString());
    }
}