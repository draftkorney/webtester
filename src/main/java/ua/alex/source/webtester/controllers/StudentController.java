package ua.alex.source.webtester.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.alex.source.webtester.entities.Question;
import ua.alex.source.webtester.entities.Test;
import ua.alex.source.webtester.security.SecurityUtils;
import ua.alex.source.webtester.service.StudentService;
import ua.alex.source.webtester.service.TutorService;
import ua.alex.source.webtester.utils.PaginationData;

import java.util.List;


@Controller
public class StudentController extends AbstractController {

    @Autowired
    protected StudentService studentService;

    @Autowired
    private TutorService tutorService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "student/home";
    }

    @RequestMapping(value = "/home/tests.html", method = RequestMethod.GET)
    public String testsPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer count, Model model) {
        PaginationData paginationData;
        List<Test> testList;

        testList = studentService.getTestsForPass(page, count);
        paginationData = new PaginationData(testList.size(), count, page);

        model.addAttribute("testToPassPaginationData", paginationData);
        model.addAttribute("tests", testList);

        return "student/tests";
    }


/*    @ModelAttribute
    public void setTest(@RequestParam Long idTest, Model model){
        System.out.println(idTest);
    }*/

    @RequestMapping(value = "/passTest.html", method = RequestMethod.GET)
    public String pagePass(@RequestParam Long idTest, Model model) {
        Test test = tutorService.getTestById(idTest);
        model.addAttribute("test", test);
        return "student/passtest";
    }

    @RequestMapping(value = "/start_pass_test", method = RequestMethod.GET)
    public String startPassTest(@RequestParam Long idTest, Model model) {
        Test test = tutorService.getTestById(idTest);
        model.addAttribute("test", test);
        return "student/passtest";
    }

    @RequestMapping(value = "/passTestOffline.html", method = RequestMethod.GET)
    public String pagePassOffline(@RequestParam Long idTest, Model model) {
        Test test;
        List<Question> questions = tutorService.getQuestionByTestId(idTest);

        if (questions.isEmpty()) {
            test = tutorService.getTestById(idTest);
        } else {
            test = questions.get(0).getTest();
        }

        model.addAttribute("test", test);
        model.addAttribute("questions", questions);
        return "student/passtestoffline";
    }
}
