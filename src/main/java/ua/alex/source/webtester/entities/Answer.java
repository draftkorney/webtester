package ua.alex.source.webtester.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Answer extends ManagerEntity {

    @Id
    @SequenceGenerator(name = "ANSWER_IDANSWER_GENERATOR", sequenceName = "answer_seq",allocationSize = 1)
    @GeneratedValue(generator = "ANSWER_IDANSWER_GENERATOR", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_answer")
    private long idAnswer;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "correct")
    private boolean correct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_question", nullable = false)
    private Question question;


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public long getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(long idAnswer) {
        this.idAnswer = idAnswer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    @Transient
    public Serializable getId() {
        return getIdAnswer();
    }
}
