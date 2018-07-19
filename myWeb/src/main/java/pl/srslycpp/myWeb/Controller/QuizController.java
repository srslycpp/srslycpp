package pl.srslycpp.myWeb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.srslycpp.myWeb.Entity.Questions;
import pl.srslycpp.myWeb.Service.QuestionService;

@Controller
public class QuizController {

	@Autowired
	private QuestionService questionService;

	private long random;

	private static long random() {

		return (long) Math.random();
	}

	public long getRandom() {
		return random;
	}

	public void setRandom() {
		this.random = 1L+(long) (Math.random() * (questionService.allQuestions().size()));
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/homepage")
	public String homepage() {
		return "homepage";
	}

	@GetMapping("/interestingLinks")
	public String interestingLinks() {
		return "interestingLinks";
	}

	@GetMapping("/projects")
	public String showProjects() {
		return "projects";
	}

	@GetMapping("/projects/quiz/allQuestions")
	public String allQuestions(Model model) {
		model.addAttribute("allQuestions", questionService.allQuestions());
		return "allQuestions";
	}

	@GetMapping({"/projects/quiz/oneQuestion"})
	public String oneQuestion(Model model) {
		setRandom();
		System.out.println(getRandom() + " !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		model.addAttribute("question", questionService.getQuestion(getRandom()));
		return "oneQuestion";
	}

	@GetMapping("/projects/quiz/addQuestion")
	public String addNewQuestion(Model model) {
		model.addAttribute("addQuestion", new Questions());
		System.out.println("addQuestion.getMapping <---------------");

		System.out.println("getMapping <---------------");
		return "addQuestion";
	}

	@GetMapping("/projects/quiz/{id}/editQuestion")
	public String editQuestion(Model model, @PathVariable("id") Long id) {
        model.addAttribute("editQuestion", questionService.edittQuestion(id));
        return "editQuestion";
    }

	@RequestMapping("/projects/quiz/{id}/deleteQuestion")
	public String deleteQuestion(@PathVariable("id")Long id){
		questionService.deleteQuestion(id);
		return "redirect:/projects/quiz/allQuestions";
	}
	@PostMapping
	@RequestMapping("/updateOrSaveQuestion")
	public String updateOrSave(@ModelAttribute Questions question) {
		Questions questionsCommand = questionService.updateQuestionCommand(question);
		return "redirect:/projects/quiz/allQuestions";
	}
}
