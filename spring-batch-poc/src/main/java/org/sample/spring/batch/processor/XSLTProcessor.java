package org.sample.spring.batch.processor;

import org.springframework.batch.item.ItemProcessor;

public class XSLTProcessor implements ItemProcessor<String, String> {
    
    private String xsltFile;

    @Override
    public String process(String item) throws Exception {
        return item + "_" + xsltFile;
    }

    public void setXsltFile(String xsltFile) {
        this.xsltFile = xsltFile;
    }
}
