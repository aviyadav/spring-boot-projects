package org.sample.spring.hibernate.stock.dao.impl;

import java.util.List;
import org.sample.spring.hibernate.stock.dao.StockDAO;
import org.sample.spring.hibernate.stock.model.Stock;
import org.sample.spring.hibernate.util.CustomHibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("stockDAO")
public class StockDAOImpl extends CustomHibernateDaoSupport implements StockDAO {

    @Override
    public void save(Stock stock) {
        getHibernateTemplate().save(stock);
    }

    @Override
    public void update(Stock stock) {
        getHibernateTemplate().update(stock);
    }

    @Override
    public void delete(Stock stock) {
        getHibernateTemplate().delete(stock);
    }

    @Override
    public Stock findByStockCode(String stockCode) {
        List list = getHibernateTemplate().find("from Stock where stockCode=?", stockCode);
        return (Stock) list.get(0);
    }

}
