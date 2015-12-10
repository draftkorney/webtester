package ua.alex.source.webtester.service;


import ua.alex.source.webtester.entities.Question;
import ua.alex.source.webtester.entities.Test;
import ua.alex.source.webtester.forms.TestForm;

import java.util.List;

public interface TutorService {

    int countTestsByTutorId(long idAccount);

    List<Test> getOwnerTests(int page, Integer count, long ownerId);

    List<Question> getQuestionByTestId(int page, Integer count, long idTest);

    Test getTestById(Long idTest);

    void saveOrUpdateTest(TestForm testForm);

    void deleteTest(Long idTest);

    int countQuestionsByTestId(long testId);
}
