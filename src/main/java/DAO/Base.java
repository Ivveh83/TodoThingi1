package DAO;

import java.util.Collection;

public interface Base<T> {
    T create(T t);
    Collection<T> findAll();
    T findById(int id);
    T update(T t);
    boolean deleteById(int id);
}
