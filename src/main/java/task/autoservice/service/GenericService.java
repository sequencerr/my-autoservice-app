package task.autoservice.service;

public interface GenericService<T> {
    T getById(Long id);

    T create(T entity);

    T update(T entity);
}
