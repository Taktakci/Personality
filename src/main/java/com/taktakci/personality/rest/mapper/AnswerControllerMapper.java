package com.taktakci.personality.rest.mapper;

import com.taktakci.personality.rest.model.AnswerRequest;
import com.taktakci.personality.rest.model.AnswerResponse;
import com.taktakci.personality.service.model.AnswerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class AnswerControllerMapper {

    public static final AnswerControllerMapper INSTANCE = Mappers.getMapper(AnswerControllerMapper.class);

    public abstract AnswerDto toAnswerDto(AnswerRequest request);

    public abstract AnswerResponse toAnswerResponse(AnswerDto answerDto);

    public abstract List<AnswerResponse> toAnswerResponseList(List<AnswerDto> answerDtoList);
}
