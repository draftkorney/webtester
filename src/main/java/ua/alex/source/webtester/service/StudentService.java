package ua.alex.source.webtester.service;


import ua.alex.source.webtester.entities.Test;
import ua.alex.source.webtester.entities.TestResult;
import ua.alex.source.webtester.forms.QuestionData;

import java.util.List;

public interface StudentService {

    List<Test> getTestsForPass(int row, int count);

    int equalsResult(QuestionData questionForAnswer);

    void saveResultToDB(TestResult testResult);

    List<TestResult> getTestResult(long currentIdAccount);
}
