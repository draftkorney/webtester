package ua.alex.source.webtester.service;


import ua.alex.source.webtester.entities.Test;

import java.util.List;

public interface StudentService {

    List<Test> getTestsForPass(int row, int count);

}
