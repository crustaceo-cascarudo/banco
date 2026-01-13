package com.fpmislata.banco.persistence.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T>{
    T insert(T jpaEntity);
    T update(T jpaEntity);
    void delete(Long id);
    long count();
    
}
