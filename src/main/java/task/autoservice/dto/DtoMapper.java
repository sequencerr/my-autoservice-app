package task.autoservice.dto;

public interface DtoMapper<T, A extends Record> {
    T toModel(A requestDto);
}
