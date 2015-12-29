package ua.alex.source.webtester.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.alex.source.webtester.entities.*;
import ua.alex.source.webtester.exceptions.TestResultSessionInvalid;
import ua.alex.source.webtester.forms.QuestionData;
import ua.alex.source.webtester.security.SecurityUtils;
import ua.alex.source.webtester.service.StudentService;
import ua.alex.source.webtester.service.TutorService;
import ua.alex.source.webtester.utils.PaginationData;
import ua.alex.source.webtester.utils.SpringUtil;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;


@Controller
public class StudentController extends AbstractController {

    @Autowired
    protected StudentService studentService;

    @Autowired
    private TutorService tutorService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Answer.class, new QuestionEditor());
    }

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

    @RequestMapping(value = "/show_result.html", method = RequestMethod.GET)
    public String pagePass(Model model) {
        List<TestResult> testResults = studentService.getTestResult(SecurityUtils.getCurrentIdAccount());
        model.addAttribute("testResults", testResults);
        return "student/showresult";
    }

    @RequestMapping(value = "/start_pass_test", method = RequestMethod.GET)
    public String startPassTest(@RequestParam Long idTest, Model model, HttpSession session) {
        Test test = tutorService.getTestById(idTest);
        List<Question> questions = tutorService.getQuestionByTestIdWithAnswers(idTest);

        session.setAttribute("currentQuestion", 1);
        session.setAttribute("test", test);
        session.setAttribute("question", questions);

        TestResult testResult = (TestResult) session.getAttribute("testResult");
        if (testResult == null) {
            testResult = new TestResult();
            testResult.setAccount(new Account(SecurityUtils.getCurrentIdAccount()));
            testResult.setAllCount(questions.size());
            testResult.setTestName(test.getName());
            testResult.setCreated(new Timestamp(System.currentTimeMillis()));
            session.setAttribute("testResult", testResult);
        }

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

        if (currentQuestion > questions.size()) {
            studentService.saveResultToDB((TestResult) session.getAttribute("testResult"));
            session.removeAttribute("testResult");
            return "redirect:/show_result.html";
        }

        Question question = questions.get(currentQuestion - 1);
        QuestionData questionData = new QuestionData(question);
        model.addAttribute("questionForAnswer", questionData);
        model.addAttribute("question", question);
        return "student/showquestion";
    }

    @RequestMapping(value = "/answered", method = RequestMethod.POST)
    public String saveAnswerFromStudent(@ModelAttribute QuestionData questionForAnswer, Model model, HttpSession session) throws TestResultSessionInvalid {
        saveTestResultToSession(questionForAnswer, session);
        Integer currentQuestion = (Integer) session.getAttribute("currentQuestion");
        session.setAttribute("currentQuestion", ++currentQuestion);
        return "redirect:/show_next_question";
    }

    private void saveTestResultToSession(QuestionData questionForAnswer, HttpSession session) throws TestResultSessionInvalid {
        int correct = studentService.equalsResult(questionForAnswer);

        TestResult testResult = (TestResult) session.getAttribute("testResult");
        if (testResult == null) {
            throw new TestResultSessionInvalid("Session does not contain test result");
        }
        testResult.setCorrectCount(testResult.getCorrectCount() + correct);
        session.setAttribute("testResult", testResult);
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
