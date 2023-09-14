package ra.service;

import java.util.List;

public interface IGenericService<T,E> {
    List<T> findAll();
    T save(T t);
    T update(E id);
    T delete(E id);
}
