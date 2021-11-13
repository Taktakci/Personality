package com.taktakci.personality.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ANSWER")
@Getter
@Setter
public class Answer {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name="QUESTION_ID")
    private Integer questionId;

    @Column(name="USER_ID")
    private String userId;

    @Column(name="ANSWER_TEXT")
    private String answerText;

    @Column(name="COND_QUEST_ID")
    private Integer conditionalQuestionId;
}
