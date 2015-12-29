package ua.alex.source.webtester.dao;


import ua.alex.source.webtester.entities.TestResult;

import java.util.List;

public interface TestResultDao extends IEntityDao<TestResult> {
    List<TestResult> getResult(long currentIdAccount);
}
