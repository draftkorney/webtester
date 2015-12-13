package ua.alex.source.webtester.service;


import ua.alex.source.webtester.entities.Test;

import java.util.List;

public interface AdvancedTutorService {

    List<Test> getAll(int row, int count);
 }
