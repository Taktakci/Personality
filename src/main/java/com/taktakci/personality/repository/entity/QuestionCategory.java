package com.taktakci.personality.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTION_CATEGORY")
public class QuestionCategory {
    @Id
    @Column(name = "TEXT", nullable = false)
    private String text;
}
