package ua.alex.source.webtester.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.alex.source.webtester.entities.Answer;
import ua.alex.source.webtester.entities.Question;
import ua.alex.source.webtester.entities.Test;
import ua.alex.source.webtester.service.StudentService;
import ua.alex.source.webtester.service.TutorService;
import ua.alex.source.webtester.utils.PaginationData;

import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = "/passTest.html", method = RequestMethod.GET)
    public String pagePass(@RequestParam Long idTest, Model model) {
        Test test = tutorService.getTestById(idTest);
        model.addAttribute("test", test);
        return "student/passtest";
    }

    @RequestMapping(value = "/start_pass_test", method = RequestMethod.GET)
    public String startPassTest(@RequestParam Long idTest, Model model, HttpSession session) {
        Test test = tutorService.getTestById(idTest);
        List<Question> questions = tutorService.getQuestionByTestId(idTest);

        session.setAttribute("currentQuestion", 1);
        session.setAttribute("test", test);
        session.setAttribute("question", questions);
        return "redirect:/show_next_question";
    }

    @RequestMapping(value = "/show_next_question", method = RequestMethod.GET)
    public String nextQuestion(Model model, HttpSession session) {

        Test test = (Test) session.getAttribute("test");

        if (test == null) {
            return "redirect:/home/tests.html";
        }

        List<Question> questions = (List<Question>) session.getAttribute("question");
        Integer currentQuestion = (Integer) session.getAttribute("currentQuestion");

        if (currentQuestion == null) {
            currentQuestion = 1;
        }

        Question question = questions.get(currentQuestion - 1);
        addDefaultAnswer(question);
        model.addAttribute("question", question);
        model.addAttribute("test", test);
        return "student/showquestion";
    }

    private void addDefaultAnswer(Question question) {
        Answer answer = new Answer();
        answer.setCorrect(false);
        answer.setName("Don't know");
        question.getAnswers().add(answer);
    }

    @RequestMapping(value = "/answered", method = RequestMethod.POST)
    public String saveAnswerFromStudent(@ModelAttribute Question question, Model model, HttpSession session) {

        return "redirect:/show_next_question";
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
