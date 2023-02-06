package task.autoservice.dto;

import task.autoservice.model.IdentifiableEntity;

public interface DtoMapper<T extends IdentifiableEntity, A extends Record> {
    T toModel(A requestDto);
}
