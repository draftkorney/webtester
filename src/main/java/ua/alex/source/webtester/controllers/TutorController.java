package ua.alex.source.webtester.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.alex.source.webtester.entities.Question;
import ua.alex.source.webtester.entities.Test;
import ua.alex.source.webtester.forms.TestForm;
import ua.alex.source.webtester.security.SecurityUtils;
import ua.alex.source.webtester.utils.PaginationData;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/tutor")
public class TutorController extends AbstractTutorController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "tutor/home";
    }

    @RequestMapping(value = {"/editTest.html", "/createTest.html"}, method = RequestMethod.GET)
    public String editTestPage(@RequestParam Long idTest, Model model) {
        Test test;

        if (idTest == null) {
            test = new Test();
        } else {
            test = tutorService.getTestById(idTest);
        }

        model.addAttribute("test", test);
        return "tutor/actionwithtest";
    }

    @RequestMapping(value = "/home/testslist.html", method = RequestMethod.GET)
    public String testslistPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer count, Model model) {
        int totalCount = tutorService.countTestsByTutorId(SecurityUtils.getCurrentIdAccount());
        PaginationData paginationData = new PaginationData(totalCount, count, page);

        List<Test> testList = tutorService.getOwnerTests(paginationData.getPage(), count, SecurityUtils.getCurrentIdAccount());
        model.addAttribute("testPaginationData", paginationData);
        model.addAttribute("tests", testList);

        return "tutor/testslist";
    }

    @RequestMapping(value = "/home/questions.html", method = RequestMethod.GET)
    public String questions(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "50") Integer count, @RequestParam Long idTest, Model model) {
        int totalCount = tutorService.countQuestionsByTestId(idTest);
        PaginationData paginationData = new PaginationData(totalCount, count, page);

        List<Question> questionList = tutorService.getQuestionByTestId(paginationData.getPage(), count, idTest);
        model.addAttribute("questionPaginationData", paginationData);
        model.addAttribute("questions", questionList);

        return "tutor/questionlist";
    }


    @RequestMapping(value = "/action_with_test", method = RequestMethod.POST)
    public String actionWithTest(@Valid @ModelAttribute TestForm testForm, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "tutor/actionwithtest";
        }

        tutorService.saveOrUpdateTest(testForm);
        return "redirect:/home/testslist.html";
    }

    @RequestMapping(value = "/delete_test", method = RequestMethod.POST)
    public String deleteTest(@RequestParam Long idTest) {
        tutorService.deleteTest(idTest);
        return "redirect:/home/testslist.html";
    }


}
