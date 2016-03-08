package com.shop.dao.impl;

import com.shop.dao.StoreDao;
import com.shop.domain.Store;

/**
 * Created by yj on 15-2-4.
 */
public class StoreDaoImpl extends HibernateDao implements StoreDao{

    @Override
    public Store getStoreById(String storeId) {
        return (Store) getHibernateTemplate().find("from Store s where s.id = ?",storeId);
    }
}
