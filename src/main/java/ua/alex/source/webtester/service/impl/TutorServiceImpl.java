package ua.alex.source.webtester.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.alex.source.webtester.components.EntityBuilder;
import ua.alex.source.webtester.dao.QuestionDao;
import ua.alex.source.webtester.dao.TestDao;
import ua.alex.source.webtester.entities.Question;
import ua.alex.source.webtester.entities.Test;
import ua.alex.source.webtester.forms.TestForm;
import ua.alex.source.webtester.service.TutorService;
import ua.alex.source.webtester.utils.ReflectionUtils;
import ua.alex.source.webtester.wrappers.QuestionWrapper;

import java.sql.Timestamp;
import java.util.List;


@Service
public class TutorServiceImpl implements TutorService {

    @Autowired
    private TestDao testDao;

    @Autowired
    private QuestionDao questionDao;

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
    public QuestionWrapper getQuestionById(Long idQuestion) {
        Question question = questionDao.getById(idQuestion);
        QuestionWrapper questionWrapper = new QuestionWrapper(question, question.getAnswers());
        return questionWrapper;
    }

    @Override
    public List<Question> getQuestionByTestId(int page, Integer count, long idTest) {
        return questionDao.getQuestionByTestId(page, count, idTest);
    }
}
