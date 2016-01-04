package ua.alex.source.webtester.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.alex.source.webtester.dao.QuestionDao;
import ua.alex.source.webtester.dao.TestDao;
import ua.alex.source.webtester.dao.TestResultDao;
import ua.alex.source.webtester.entities.Answer;
import ua.alex.source.webtester.entities.Question;
import ua.alex.source.webtester.entities.Test;
import ua.alex.source.webtester.entities.TestResult;
import ua.alex.source.webtester.forms.QuestionData;
import ua.alex.source.webtester.service.StudentService;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    private TestDao testDao;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private TestResultDao testResultDao;


    @Override
    public List<Test> getTestsForPass(int row, int count) {
        return testDao.findTestForPass(row, count);
    }

    @Override
    public int getTestsCountForPass() {
        return testDao.findTestCountForPass();
    }

    @Override
    public int equalsResult(QuestionData questionForAnswer) {
        Question question = questionDao.getById(questionForAnswer.getIdQuestion());
        List<Answer> answers = question.getAnswers();
        List<Answer> correctAnswer = getCorrectAnswer(answers);
        List<Answer> answered = questionForAnswer.getAnswerList();

        if (answered == null || answered.isEmpty()) {
            return 0;
        }

        boolean isCorrect = CollectionUtils.isEqualCollection(correctAnswer, answered);
        return isCorrect ? 1 : 0;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {RuntimeException.class})
    public void saveResultToDB(TestResult testResult) {
        testResult.setUpdated(new Timestamp(System.currentTimeMillis()));
        testResultDao.save(testResult);
    }

    @Override
    public List<TestResult> getTestResult(long currentIdAccount) {
        return testResultDao.getResult(currentIdAccount);
    }

    @Override
    public List<TestResult> getTestResult(long currentIdAccount, Integer page, Integer count) {
        return testResultDao.getResult(currentIdAccount, page, count);
    }

    @Override
    public int getCountTestResult(long currentIdAccount) {
        return testResultDao.getResultCount(currentIdAccount);
    }

    private List<Answer> getCorrectAnswer(List<Answer> answers) {
        List<Answer> correctAnswer = answers.stream().filter(Answer::isCorrect).collect(Collectors.toList());
        return correctAnswer;
    }
}
