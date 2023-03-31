package QuotationApp.day20revision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import QuotationApp.day20revision.model.Cart;
import QuotationApp.day20revision.model.Quotation;

@Service
public class QuotationService {

    @Value("${revision20.quotations.url}")
    private String qUrl;

    public Quotation getQuotations(List<String> items) throws Exception{

        RestTemplate template = new RestTemplate();

        RequestEntity<String> req = RequestEntity.post(qUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Cart.toJsonArray(items).toString());

        ResponseEntity<String> resp = template.exchange(req, String.class);
        if(!resp.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Get quotation failed");
        }

        Quotation quotation = Quotation.create(resp.getBody(), items);

        return quotation;
    }
}
