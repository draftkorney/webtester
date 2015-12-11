package ua.alex.source.webtester.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.alex.source.webtester.components.EntityBuilder;
import ua.alex.source.webtester.dao.AnswerDao;
import ua.alex.source.webtester.dao.QuestionDao;
import ua.alex.source.webtester.dao.TestDao;
import ua.alex.source.webtester.entities.Answer;
import ua.alex.source.webtester.entities.Question;
import ua.alex.source.webtester.entities.Test;
import ua.alex.source.webtester.forms.AnswerForm;
import ua.alex.source.webtester.forms.QuestionForm;
import ua.alex.source.webtester.forms.TestForm;
import ua.alex.source.webtester.service.TutorService;
import ua.alex.source.webtester.utils.ReflectionUtils;

import java.sql.Timestamp;
import java.util.List;


@Service
public class TutorServiceImpl implements TutorService {

    @Autowired
    private TestDao testDao;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private EntityBuilder entityBuilder;

    @Override
    public int countTestsByTutorId(long idAccount) {
        return testDao.getCountTestsByAccountId(idAccount);
    }

    @Override
    public List<Test> getOwnerTests(int page, Integer count, long ownerId) {
        return testDao.getTestByAccountId(page, count, ownerId);
    }

    @Override
    public Test getTestById(Long idTest) {
        return testDao.getById(idTest);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {RuntimeException.class})
    public void saveOrUpdateTest(TestForm testForm) {
        Test test;

        final Long idTest = testForm.getIdTest();

        if (idTest == null) {
            test = entityBuilder.buildTest();
        } else {
            test = testDao.getById(idTest);
            test.setUpdated(new Timestamp(System.currentTimeMillis()));
        }

        ReflectionUtils.copyByFields(test, testForm);
        testDao.save(test);
    }

    @Override
    public void deleteTest(Long idTest) {
        testDao.deleteById(idTest);
    }

    @Override
    public int countQuestionsByTestId(long testId) {
        return questionDao.countQuestionsByTestId(testId);
    }

    @Override
    @Transactional(readOnly = true)
    public Question getQuestionById(Long idQuestion) {
        return questionDao.getById(idQuestion);
    }

    @Override
    public List<Question> getQuestionByTestId(int page, Integer count, long idTest) {
        return questionDao.getQuestionByTestId(page, count, idTest);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {RuntimeException.class})
    public void saveOrUpdateQuestion(QuestionForm questionForm) {

        Question question;

        final Long idQuestion = questionForm.getIdQuestion();

        if (idQuestion == null) {
            question = entityBuilder.buildQuestion();
        } else {
            question = questionDao.getById(idQuestion);
            question.setUpdated(new Timestamp(System.currentTimeMillis()));
        }

        ReflectionUtils.copyByFields(question, questionForm);
        questionDao.save(question);

    }

    @Override
    public Answer getAnswerById(Long idAnswer) {
        return answerDao.getById(idAnswer);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {RuntimeException.class})
    public void saveOrUpdateAnswer(AnswerForm answerForm) {
        Answer answer;

        final Long idAnswer = answerForm.getIdAnswer();

        if (idAnswer == null) {
            answer = entityBuilder.buildAnswer();
        } else {
            answer = answerDao.getById(idAnswer);
            answer.setUpdated(new Timestamp(System.currentTimeMillis()));
        }

        ReflectionUtils.copyByFields(answer, answerForm);

        answerDao.save(answer);
    }

    @Override
    public void deleteQuestion(Long idQuestion) {
        questionDao.deleteById(idQuestion);
    }

    @Override
    public void deleteAnswer(Long idAnswer) {
        answerDao.deleteById(idAnswer);
    }
}
