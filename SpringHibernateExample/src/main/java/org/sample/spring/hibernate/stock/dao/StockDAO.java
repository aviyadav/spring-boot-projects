package org.sample.spring.hibernate.stock.dao;

import org.sample.spring.hibernate.stock.model.Stock;

public interface StockDAO {
    void save(Stock stock);
    void update(Stock stock);
    void delete(Stock stock);
    Stock findByStockCode(String stockCode);
}
