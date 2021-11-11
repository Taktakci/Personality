package com.taktakci.personality.service.mapper;

import com.taktakci.personality.repository.entity.Question;
import com.taktakci.personality.repository.entity.QuestionCategory;
import com.taktakci.personality.repository.entity.QuestionType;
import com.taktakci.personality.service.model.QuestionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

@Mapper
public abstract class QuestionServiceMapper {

    public static final QuestionServiceMapper INSTANCE = Mappers.getMapper(QuestionServiceMapper.class);

    public abstract QuestionDto toQuestionDto(Question question);

    public String mapQuestionCategoryToString(QuestionCategory category) {
        return category.getText();
    }

    public String mapQuestionTypeToString(QuestionType type) {
        return type.getText();
    }

    public List<String> mapCommaSeperatedValues(String value) {
        String[] split = value.split(";");
        return Arrays.asList(split);
    }
}
