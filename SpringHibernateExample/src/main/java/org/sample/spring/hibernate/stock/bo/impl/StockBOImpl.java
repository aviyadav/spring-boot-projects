package org.sample.spring.hibernate.stock.bo.impl;

import org.sample.spring.hibernate.stock.bo.StockBO;
import org.sample.spring.hibernate.stock.dao.StockDAO;
import org.sample.spring.hibernate.stock.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stockBO")
public class StockBOImpl implements StockBO {
    
    @Autowired
    StockDAO stockDao;

    public void setStockDao(StockDAO stockDao) {
        this.stockDao = stockDao;
    }

    @Override
    public void save(Stock stock) {
        stockDao.save(stock);
    }

    @Override
    public void update(Stock stock) {
        stockDao.update(stock);
    }

    @Override
    public void delete(Stock stock) {
        stockDao.delete(stock);
    }

    @Override
    public Stock findByStockCode(String stockCode) {
        return stockDao.findByStockCode(stockCode);
    }

}
