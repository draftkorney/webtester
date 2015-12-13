package ua.alex.source.webtester.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.alex.source.webtester.ApplicationConstants;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.entities.Answer;
import ua.alex.source.webtester.entities.Question;
import ua.alex.source.webtester.entities.Test;
import ua.alex.source.webtester.forms.AnswerForm;
import ua.alex.source.webtester.forms.QuestionForm;
import ua.alex.source.webtester.forms.TestForm;
import ua.alex.source.webtester.security.SecurityUtils;
import ua.alex.source.webtester.utils.PaginationData;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(value = {"/tutor", "/advanced_tutor"})
public class TutorController extends AbstractTutorController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "tutor/home";
    }

    @RequestMapping(value = "/home/testslist.html", method = RequestMethod.GET)
    public String testslistPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer count, Model model) {
        PaginationData paginationData;
        List<Test> testList;

        if (isAdvancedTutor()) {
            testList = advancedTutorService.getAll(page, count);
            paginationData = new PaginationData(testList.size(), count, page);
        } else {
            int totalCount = tutorService.countTestsByTutorId(SecurityUtils.getCurrentIdAccount());
            paginationData = new PaginationData(totalCount, count, page);

            testList = tutorService.getOwnerTests(paginationData.getPage(), count, SecurityUtils.getCurrentIdAccount());
        }

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
        model.addAttribute("idTest", idTest);

        return "tutor/questionlist";
    }

    @RequestMapping(value = {"/editTest.html", "/createTest.html"}, method = RequestMethod.GET)
    public String editTestPage(@RequestParam(required = false) Long idTest, Model model) {
        Test test;

        if (idTest == null) {
            test = new Test();
            Account account = new Account();
            account.setIdAccount(SecurityUtils.getCurrentIdAccount());
            test.setAccount(account);
        } else {
            test = tutorService.getTestById(idTest);
        }

        model.addAttribute("testForm", test);
        return "tutor/actionwithtest";
    }

    @RequestMapping(value = {"/editQuestion.html", "/addQuestion.html"}, method = RequestMethod.GET)
    public String editQuestionPage(@RequestParam(required = false) Long idQuestion, @RequestParam Long idTest, Model model) {
        Question question;

        if (idQuestion == null) {
            question = initQuestion(idTest);
        } else {
            question = tutorService.getQuestionById(idQuestion);
        }

        model.addAttribute("questionForm", question);
        return "tutor/actionwithquestion";
    }

    @RequestMapping(value = {"/editAnswer.html", "/addAnswer.html"}, method = RequestMethod.GET)
    public String editAnswerPage(@RequestParam(required = false) Long idAnswer, @RequestParam Long idQuestion,
                                 @RequestParam Long idTest, Model model) {
        Answer answer;

        if (idAnswer == null) {
            answer = initAnswer(idQuestion, idTest);
        } else {
            answer = tutorService.getAnswerById(idAnswer);
        }

        model.addAttribute("answerForm", answer);
        return "tutor/actionwithanswer";
    }

    @RequestMapping(value = "/action_with_test", method = RequestMethod.POST)
    public String actionWithTest(@Valid @ModelAttribute TestForm testForm, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "tutor/actionwithtest";
        }

        tutorService.saveOrUpdateTest(testForm);
        return "redirect:/tutor/home/testslist.html";
    }

    @RequestMapping(value = "/action_with_question", method = RequestMethod.POST)
    public String actionWithQuestion(@Valid @ModelAttribute QuestionForm questionForm, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "tutor/actionwithquestion";
        }

        tutorService.saveOrUpdateQuestion(questionForm);
        return "redirect:/tutor/home/questions.html?idTest=" + questionForm.getTest().getIdTest();
    }

    @RequestMapping(value = "/action_with_answer", method = RequestMethod.POST)
    public String actionWithQuestion(@Valid @ModelAttribute AnswerForm answerForm, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "tutor/actionwithanswer";
        }

        tutorService.saveOrUpdateAnswer(answerForm);
        return "redirect:/tutor/home/questions.html?idTest=" + answerForm.getQuestion().getTest().getIdTest();
    }

    @RequestMapping(value = "/delete_test", method = RequestMethod.POST)
    public String deleteTest(@RequestParam Long idTest) {
        tutorService.deleteTest(idTest);
        return "redirect:/tutor/home/testslist.html";
    }

    @RequestMapping(value = "/delete_question", method = RequestMethod.POST)
    public String deleteQuestion(@RequestParam Long idQuestion, @RequestParam Long idTest) {
        tutorService.deleteQuestion(idQuestion);
        return "redirect:/tutor/home/questions.html?idTest=" + idTest;
    }

    @RequestMapping(value = "/delete_answer", method = RequestMethod.POST)
    public String deleteAnswer(@RequestParam Long idAnswer, @RequestParam Long idTest) {
        tutorService.deleteAnswer(idAnswer);
        return "redirect:/tutor/home/questions.html?idTest=" + idTest;
    }

    private Question initQuestion(Long idTest) {
        Question question = new Question();
        Test test = new Test();
        test.setIdTest(idTest);
        question.setTest(test);
        return question;
    }

    private Answer initAnswer(Long idQuestion, Long idTest) {
        Answer answer = new Answer();
        Question question = initQuestion(idTest);
        question.setIdQuestion(idQuestion);
        answer.setQuestion(question);
        return answer;
    }

    private boolean isAdvancedTutor() {
        return SecurityUtils.getCurrentAccount().getRoles().contains(ApplicationConstants.ADVANCED_TUTOR_ROLE);
    }

}
