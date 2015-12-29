package ua.alex.source.webtester.forms;


import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.entities.Answer;
import ua.alex.source.webtester.entities.Question;

import java.util.ArrayList;
import java.util.List;


public class QuestionData implements IForm {
    private static final long serialVersionUID = -3633827335080843887L;

    private Long idQuestion;
    private String questionName;
    private List<Answer> answerList;
    private int time;


    public QuestionData() {
    }

    public QuestionData(Question question) {
        populate(question);
    }

    private void populate(Question question) {
        this.idQuestion = question.getIdQuestion();
        this.questionName = question.getName();
        this.time = question.getTest().getTimePerQuestion();
        this.answerList = convertAnswers(question.getAnswers());

    }

    private List<Answer> convertAnswers(List<Answer> answers) {
        List<Answer> answerList = new ArrayList<>();
        for (Answer answer : answers) {
            Answer answer1 = new Answer();
            answer1.setIdAnswer(answer.getIdAnswer());
            answer1.setName(answer.getName());
            answerList.add(answer1);
        }

        Answer answer = new Answer();
        answer.setIdAnswer(-1L);
        answer.setName("Don't know");
        answerList.add(answer);

        return answerList;
    }

    public Long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Long idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
