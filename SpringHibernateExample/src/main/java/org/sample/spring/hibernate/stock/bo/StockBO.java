package org.sample.spring.hibernate.stock.bo;

import org.sample.spring.hibernate.stock.model.Stock;

public interface StockBO {
    void save(Stock stock);
    void update(Stock stock);
    void delete(Stock stock);
    Stock findByStockCode(String stockCode);
}
