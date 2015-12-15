package ua.alex.source.webtester.forms;


import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;
import ua.alex.source.webtester.entities.Question;


public class AnswerForm implements IForm {

    private Long idAnswer;

    @NotNull(errorCode = "form.answer.name.empty")
    @NotBlank(errorCode = "form.answer.name.empty")
    @Length(min = 2, max = 255, errorCode = "form.answer.name.invalid.length")
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
