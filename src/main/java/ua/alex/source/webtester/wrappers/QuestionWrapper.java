package ua.alex.source.webtester.wrappers;

import ua.alex.source.webtester.entities.Answer;
import ua.alex.source.webtester.entities.Question;

import java.util.List;

public class QuestionWrapper {
    private Question question;
    private List<Answer> answers;

    public QuestionWrapper() {
    }

    public QuestionWrapper(Question question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
