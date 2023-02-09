package task.autoservice.service;

import task.autoservice.model.IdentifiableEntity;

public interface GenericService<T extends IdentifiableEntity> {
    T getById(Long id);

    T create(T entity);

    T update(T entity);
}
