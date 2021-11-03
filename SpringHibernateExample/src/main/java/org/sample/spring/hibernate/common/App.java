package org.sample.spring.hibernate.common;

import org.sample.spring.hibernate.stock.bo.StockBO;
import org.sample.spring.hibernate.stock.model.Stock;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
        
        StockBO stockBO = (StockBO) appContext.getBean("stockBO");
        
        /** insert **/
        Stock stock = new Stock();
        stock.setStockCode("7668");
        stock.setStockName("HAIO");
        
        stockBO.save(stock);
        
        /** select **/
    	Stock stock2 = stockBO.findByStockCode("7668");
    	System.out.println(stock2);
    	
    	/** update **/
    	stock2.setStockName("HAIO-1");
    	stockBO.update(stock2);
    	
    	/** delete **/
    	stockBO.delete(stock2);
    	
    	System.out.println("Done");
    }
}
