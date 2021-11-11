package com.taktakci.personality.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTION_TYPE")
@Getter
@Setter
public class QuestionType {
    @Id
    @Column(name = "TEXT", nullable = false)
    private String text;
}
