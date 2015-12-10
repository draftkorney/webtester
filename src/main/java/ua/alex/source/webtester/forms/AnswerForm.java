package ua.alex.source.webtester.forms;


import org.hibernate.validator.constraints.NotEmpty;
import ua.alex.source.webtester.entities.Question;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AnswerForm implements IForm {

    private Long idAnswer;

    @NotEmpty
    @Size(min = 2, max = 255)
    private String name;

    private boolean correct;

    @NotNull
    private Question question;

    public Long getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(Long idAnswer) {
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
