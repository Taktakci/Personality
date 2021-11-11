package com.taktakci.personality.repository;

import com.taktakci.personality.repository.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query("SELECT t.text FROM QuestionCategory t ORDER BY t.text asc")
    List<String> findCategoryList();

    @Query("SELECT t.id FROM Question t WHERE t.category.text = ?1 AND t.isConditional = false ORDER BY t.id asc")
    List<Integer> findByCategoryId(String categoryId);
}
