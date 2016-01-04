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

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


@Controller
public class StudentController extends AbstractController {

    private static final String CURRENT_QUESTION = "currentQuestion";
    private static final String PREVIOUS_QUESTION = "previousQuestion";
    private static final String TEST_IS_STARTED = "testStarted";
    private static final String TEST_RESULT = "testResult";
    private static final String QUESTIONS = "questions";

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
        paginationData = new PaginationData(studentService.getTestsCountForPass(), count, page);

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
    public String pagePass(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer count, Model model) {
        PaginationData paginationData;
        paginationData = new PaginationData(studentService.getCountTestResult(SecurityUtils.getCurrentIdAccount()), count, page);

        List<TestResult> testResults = studentService.getTestResult(SecurityUtils.getCurrentIdAccount(), page, count);
        model.addAttribute("testResults", testResults);
        model.addAttribute("testResultPaginationData", paginationData);

        return "student/showresult";
    }

    @RequestMapping(value = "/start_pass_test", method = RequestMethod.GET)
    public String startPassTest(@RequestParam Long idTest, Model model, HttpSession session) {
        Test test = tutorService.getTestById(idTest);
        List<Question> questions = tutorService.getQuestionByTestIdWithAnswers(idTest);

        session.setAttribute(CURRENT_QUESTION, 1);
        session.setAttribute(PREVIOUS_QUESTION, 0);
        session.setAttribute(TEST_IS_STARTED, true);
        session.setAttribute(QUESTIONS, questions);

        TestResult testResult = new TestResult();
        testResult.setAccount(new Account(SecurityUtils.getCurrentIdAccount()));
        testResult.setAllCount(questions.size());
        testResult.setTestName(test.getName());
        testResult.setCreated(new Timestamp(System.currentTimeMillis()));
        session.setAttribute(TEST_RESULT, testResult);

        return "redirect:/show_next_question";
    }

    @RequestMapping(value = "/show_next_question", method = RequestMethod.GET)
    public String nextQuestion(@CookieValue(value = "time", defaultValue = "-2") Integer timeCookie, Model model, HttpSession session) {

        boolean testIsStarted = (boolean) session.getAttribute(TEST_IS_STARTED);

        if (!testIsStarted) {
            return "redirect:/home/tests.html";
        }

        List<Question> questions = (List<Question>) session.getAttribute(QUESTIONS);
        Integer currentQuestion = (Integer) session.getAttribute(CURRENT_QUESTION);
        Integer previousQuestion = (Integer) session.getAttribute(PREVIOUS_QUESTION);


        if (currentQuestion > questions.size()) {
            studentService.saveResultToDB((TestResult) session.getAttribute(TEST_RESULT));
            session.removeAttribute(TEST_RESULT);
            session.removeAttribute(TEST_IS_STARTED);
            session.removeAttribute(CURRENT_QUESTION);
            session.removeAttribute(PREVIOUS_QUESTION);
            session.removeAttribute(QUESTIONS);
            return "redirect:/show_result.html";
        }

        Question question = questions.get(currentQuestion - 1);
        QuestionData questionData = new QuestionData(question);

        if (Objects.equals(currentQuestion, previousQuestion)) {
            int currentTime = timeCookie < 0 ? questionData.getTime() : timeCookie;

            if (currentTime > questionData.getTime()) {
                currentTime = 2;
            }

            questionData.setCurrentTime(currentTime);
        }

        model.addAttribute("questionForAnswer", questionData);
        model.addAttribute("question", question);
        session.setAttribute(PREVIOUS_QUESTION, currentQuestion);
        return "student/showquestion";
    }

    @RequestMapping(value = "/answered", method = RequestMethod.POST)
    public String saveAnswerFromStudent(@ModelAttribute QuestionData questionForAnswer, Model model, HttpSession session) throws TestResultSessionInvalid {
        saveTestResultToSession(questionForAnswer, session);
        Integer currentQuestion = (Integer) session.getAttribute(CURRENT_QUESTION);
        session.setAttribute(CURRENT_QUESTION, ++currentQuestion);
        return "redirect:/show_next_question";
    }

    private void saveTestResultToSession(QuestionData questionForAnswer, HttpSession session) throws TestResultSessionInvalid {
        int correct = studentService.equalsResult(questionForAnswer);

        TestResult testResult = (TestResult) session.getAttribute(TEST_RESULT);
        if (testResult == null) {
            throw new TestResultSessionInvalid("Session does not contain test result");
        }
        testResult.setCorrectCount(testResult.getCorrectCount() + correct);
        session.setAttribute(TEST_RESULT, testResult);
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
