package ua.alex.source.webtester.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.alex.source.webtester.dao.TestDao;
import ua.alex.source.webtester.entities.Test;
import ua.alex.source.webtester.service.AdvancedTutorService;

import java.util.List;

@Service
public class AdvancedTutorServiceImpl implements AdvancedTutorService {

    @Autowired
    private TestDao testDao;

    @Override
    public List<Test> getAll(int row, int count) {
        return testDao.findAll(row, count);
    }
}
