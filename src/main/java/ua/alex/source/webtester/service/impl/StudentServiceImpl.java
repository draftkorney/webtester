package ua.alex.source.webtester.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.alex.source.webtester.dao.TestDao;
import ua.alex.source.webtester.entities.Test;
import ua.alex.source.webtester.service.StudentService;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    private TestDao testDao;

    @Override
    public List<Test> getTestsForPass(int row, int count) {
        return testDao.findAll(row, count);
    }
}
