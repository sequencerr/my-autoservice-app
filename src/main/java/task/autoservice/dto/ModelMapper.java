package task.autoservice.dto;

import task.autoservice.model.IdentifiableEntity;

public interface ModelMapper<T extends IdentifiableEntity, A extends Record> {
    A toDto(T entity);
}
