package org.sample.springbatch;

import org.sample.springbatch.model.ExamResult;
import org.springframework.batch.item.ItemProcessor;

public class ExamResultItemProcessor implements ItemProcessor<ExamResult, ExamResult> {

    @Override
    public ExamResult process(ExamResult result) throws Exception {
        System.out.println("Processing result : " + result);
        
        if(result.getPercentage() < 61) {
            return null;
        }
        
        return result;
    }
}
