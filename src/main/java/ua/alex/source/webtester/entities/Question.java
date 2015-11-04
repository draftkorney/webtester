package ua.alex.source.webtester.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Question extends ManagerEntity {

    @Id
    @SequenceGenerator(name = "QUESTION_IDQUESTION_GENERATOR", sequenceName = "question_seq",allocationSize = 1)
    @GeneratedValue(generator = "QUESTION_IDQUESTION_GENERATOR", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_question", nullable = false, unique = true)
    private long idQuestion;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_test", nullable = false)
    private Test test;

    public void setIdQuestion(Long idQuestion) {
        this.idQuestion = idQuestion;
    }

    public long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(long idQuestion) {
        this.idQuestion = idQuestion;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @Transient
    public Serializable getId() {
        return getIdQuestion();
    }
}
