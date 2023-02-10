package task.autoservice.dto;

public interface ModelMapper<T, A extends Record> {
    A toDto(T entity);
}
