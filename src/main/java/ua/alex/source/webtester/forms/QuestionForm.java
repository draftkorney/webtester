package ua.alex.source.webtester.forms;

import org.hibernate.validator.constraints.NotEmpty;
import ua.alex.source.webtester.entities.Test;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class QuestionForm implements IForm {

    private Long idQuestion;

    @NotEmpty
    @Size(min = 6, max = 255)
    private String name;

    @NotNull
    private Test test;

    public QuestionForm() {
    }

    public QuestionForm(Long idQuestion, String name, Test test) {
        this.idQuestion = idQuestion;
        this.name = name;
        this.test = test;
    }

    public Long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Long idQuestion) {
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
}
