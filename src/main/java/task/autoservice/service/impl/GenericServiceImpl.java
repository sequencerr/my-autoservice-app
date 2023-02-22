package task.autoservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.jpa.repository.JpaRepository;
import task.autoservice.service.GenericService;

import java.lang.reflect.Field;

public abstract class GenericServiceImpl<T> implements GenericService<T> {
    private static final String ENTITY_FIELD_ID_NAME = "id";
    protected final JpaRepository<T, Long> repository;

    public GenericServiceImpl(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    public T getById(Long id) {
        return repository.getReferenceById(id);
    }

    public T create(T entity) {
        if (entity instanceof HibernateProxy || getId(entity) != null) {
            throw new RuntimeException(
                    "You cannot create new entity which already exist. entity=" + entity);
        }
        return repository.save(entity);
    }

    public T update(T entity) {
        Long id = getId(entity);
        if (id == null || !repository.existsById(id)) {
            throw new EntityNotFoundException("Unable to update unexciting entity=" + entity);
        }
        return repository.save(entity);
    }

    private Long getId(T entity) {
        try {
            if (entity instanceof HibernateProxy) entity = (T) Hibernate.unproxy(entity);
            Field idField = entity.getClass().getDeclaredField(ENTITY_FIELD_ID_NAME);
            idField.trySetAccessible();
            return (Long) idField.get(entity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
