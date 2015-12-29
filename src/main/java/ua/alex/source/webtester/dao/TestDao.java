package ua.alex.source.webtester.dao;

import ua.alex.source.webtester.entities.Test;

import java.util.List;

public interface TestDao extends IEntityDao<Test> {

    int getCountTestsByAccountId(Long idAccount);

    List<Test> getTestByAccountId(int row, int count, Long idAccount);


    List<Test> findTestForPass(int row, int count);
}
