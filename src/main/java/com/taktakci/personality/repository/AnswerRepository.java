package com.taktakci.personality.repository;

import com.taktakci.personality.repository.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    @Query("SELECT t FROM Answer t WHERE t.userId = ?1 AND t.questionId = ?2")
    Answer findByQuestionIdAndUserId(String userId, Integer questionId);

    @Query("SELECT t FROM Answer t WHERE t.userId = ?1")
    List<Answer> getUserAnswers(String userId);
}
