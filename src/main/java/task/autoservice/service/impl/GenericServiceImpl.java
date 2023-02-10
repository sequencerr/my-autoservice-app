package task.autoservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import task.autoservice.service.GenericService;

public abstract class GenericServiceImpl<T> implements GenericService<T> {
    protected final JpaRepository<T, Long> repository;

    public GenericServiceImpl(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    public T getById(Long id) {
        return repository.getReferenceById(id);
    }

    public T create(T entity) {
        if (repository.exists(Example.of(entity))) {
            throw new RuntimeException(
                    "You cannot create new entity which already exist. entity=" + entity);
        }
        return repository.save(entity);
    }

    public T update(T entity) {
        if (!repository.exists(Example.of(entity))) {
            throw new EntityNotFoundException("Unable to update unexciting entity=" + entity);
        }
        return repository.save(entity);
    }
}
