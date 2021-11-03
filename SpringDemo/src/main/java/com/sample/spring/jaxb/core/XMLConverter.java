package com.sample.spring.jaxb.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

public class XMLConverter {

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public void convertFromObjectToXML(Object object, String filepath) throws FileNotFoundException, IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filepath);
            getMarshaller().marshal(object, new StreamResult(fos));
        } finally {
            if(fos != null) {
                fos.close();
            }
        }

    }
    
    public Object convertFromXMLtoObject(String xmlFile) throws FileNotFoundException, IOException {
        FileInputStream fis = null;
        
        try {
          fis = new FileInputStream(xmlFile);
          return getUnmarshaller().unmarshal(new StreamSource(fis));
          
        } finally {
            if(fis != null) {
                fis.close();
            }
        }
    }
}
