package org.sample.spring.batch.reader;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class QueryReader implements ItemReader<Map<String, Object>> {
    
    private String query;
    
    private final int dumbDataCount = ThreadLocalRandom.current().nextInt(5);
    private int currentRecord = 0;

    @Override
    public Map<String, Object> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        
        if(currentRecord < dumbDataCount) {
            final Map<String, Object> map = new HashMap<>();
            map.put("query", query);
            map.put("currentRecord", currentRecord);
            
            currentRecord++;
            
            return map;
        }
        
        return null;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
