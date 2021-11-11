package com.taktakci.personality.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTION_TYPE")
public class QuestionType {
    @Id
    @Column(name = "TEXT", nullable = false)
    private String text;
}
