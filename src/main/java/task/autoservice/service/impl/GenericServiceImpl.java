package task.autoservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import task.autoservice.model.IdentifiableEntity;
import task.autoservice.service.GenericService;

public abstract class GenericServiceImpl<T extends IdentifiableEntity>
        implements GenericService<T> {
    protected final JpaRepository<T, Long> repository;

    public GenericServiceImpl(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    public T getById(Long id) {
        return repository.getReferenceById(id);
    }

    public T create(T entity) {
        if (entity.getId() != null) {
            throw new EntityNotFoundException(
                    "Unable to create already exciting entity with id=" + entity.getId()
                            + ", with name=" + entity.getClass().getSimpleName());
        }
        return repository.save(entity);
    }

    public T update(T entity) {
        if (entity.getId() == null || !repository.existsById(entity.getId())) {
            throw new EntityNotFoundException(
                    "Unable to update unexciting entity by id=" + entity.getId()
                            + ", with name=" + entity.getClass().getSimpleName());
        }
        return repository.save(entity);
    }
}
