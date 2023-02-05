package task.autoservice.dto.mapper;

import org.springframework.stereotype.Component;
import task.autoservice.dto.DtoMapper;
import task.autoservice.dto.ModelMapper;
import task.autoservice.dto.request.DetailRequestDto;
import task.autoservice.dto.response.DetailResponseDto;
import task.autoservice.model.Detail;

@Component
public class DetailMapper implements
        DtoMapper<Detail, DetailRequestDto>,
        ModelMapper<Detail, DetailResponseDto> {
    public Detail toModel(DetailRequestDto requestDto) {
        return toModel(null, requestDto);
    }

    @Override
    public Detail toModel(Long id, DetailRequestDto requestDto) {
        Detail detail = new Detail();
        detail.setId(id);
        detail.setName(requestDto.name());
        detail.setPrice(requestDto.price());
        return detail;
    }

    @Override
    public DetailResponseDto toDto(Detail detail) {
        return new DetailResponseDto(
                detail.getId(),
                detail.getName(),
                detail.getPrice());
    }
}
