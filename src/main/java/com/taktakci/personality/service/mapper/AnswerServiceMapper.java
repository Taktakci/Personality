package com.taktakci.personality.service.mapper;

import com.taktakci.personality.repository.entity.Answer;
import com.taktakci.personality.service.model.AnswerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class AnswerServiceMapper {

    public static final AnswerServiceMapper INSTANCE = Mappers.getMapper(AnswerServiceMapper.class);

    public abstract Answer toAnswer(AnswerDto answerDto);

    public abstract AnswerDto toAnswerDto(Answer answer);

    public abstract List<AnswerDto> toAnswerDtoList(List<Answer> answerList);
}
