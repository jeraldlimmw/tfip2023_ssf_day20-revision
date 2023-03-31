package B2API.QuotationAPI.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteRepository {
    
    @Autowired
    private RedisTemplate<String, String> template;

    public String getUnitPrice(String item){
        return template.opsForValue().get(item);
    }
}
