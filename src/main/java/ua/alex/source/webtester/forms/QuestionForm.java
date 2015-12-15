package ua.alex.source.webtester.forms;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;
import ua.alex.source.webtester.entities.Test;


public class QuestionForm implements IForm {

    private Long idQuestion;

    @NotNull(errorCode = "form.question.name.empty")
    @NotBlank(errorCode = "form.question.name.empty")
    @Length(min = 6, max = 60, errorCode = "form.question.name.length")
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
