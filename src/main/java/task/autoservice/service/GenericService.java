package task.autoservice.service;

import task.autoservice.model.IdentifiableEntity;

import java.util.List;

public interface GenericService<T extends IdentifiableEntity> {
    T getById(Long id);

    List<T> findAllById(List<Long> ids);

    T create(T entity);

    T update(T entity);
}
