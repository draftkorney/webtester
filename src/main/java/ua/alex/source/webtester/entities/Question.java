package ua.alex.source.webtester.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Question extends ManagerEntity {

    @Id
    @SequenceGenerator(name = "QUESTION_IDQUESTION_GENERATOR", sequenceName = "question_seq", allocationSize = 1)
    @GeneratedValue(generator = "QUESTION_IDQUESTION_GENERATOR", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_question", nullable = false, unique = true)
    private long idQuestion;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_test", nullable = false)
    private Test test;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

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

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    @Transient
    public Serializable getId() {
        return getIdQuestion();
    }
}
