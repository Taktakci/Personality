package com.taktakci.personality.rest.mapper;

import com.taktakci.personality.rest.model.CategoryResponse;
import com.taktakci.personality.rest.model.QuestionIdListResponse;
import com.taktakci.personality.rest.model.QuestionResponse;
import com.taktakci.personality.service.model.QuestionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class QuestionControllerMapper {

    public static final QuestionControllerMapper INSTANCE = Mappers.getMapper(QuestionControllerMapper.class);

    public abstract QuestionResponse toQuestionResponse(QuestionDto questionDto);

    public CategoryResponse toCategoryResponse(List<String> categoryList) {
        CategoryResponse response = new CategoryResponse();
        response.setCategoryList(categoryList);
        return response;
    }

    public QuestionIdListResponse toQuestionIdListResponse(List<Integer> idList) {
        QuestionIdListResponse response = new QuestionIdListResponse();
        response.setIdList(idList);
        return response;
    }
}
