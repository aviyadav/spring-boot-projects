package com.sample.spring.jaxb.core;

import com.sample.spring.jaxb.core.model.Company;
import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private static final String XML_FILE_NAME = "company.xml";

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        XMLConverter converter = context.getBean(XMLConverter.class);

        Company company = new Company();
        company.setId(201);
        company.setCompanyName("Apple");
        company.setCeoName("Tim Cook");
        company.setNumberOfEmployees(35000);
        
        converter.convertFromObjectToXML(company, XML_FILE_NAME);
        System.out.println("Converted Object to XML");
        
        Company company1 = (Company) converter.convertFromXMLtoObject(XML_FILE_NAME);
        System.out.println(company1);
        System.out.println("Converted XML to Object");
    }
}
