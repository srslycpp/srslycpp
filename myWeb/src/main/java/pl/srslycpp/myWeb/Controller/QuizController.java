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

	public long getRandom() {
		return random;
	}

	public void setRandom() {
		this.random = 1L + (long) (Math.random() * (20L - 1L));
	}

	private long random;
	//public long random = new RandomDataGenerator().nextLong(1L, 10L);



	Questions questions = new Questions();

	public static long random() {

		return (long) Math.random();
	}
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/homepage")
	public String homepage() {
		return "homepage";
	}
	@GetMapping("/interestingLings")
	public String interestingLinks() {
		return "interestingLinks";
	}

	@GetMapping("/projects")
	public String showProjects(){
		return "projects";
	}

	@GetMapping("/projects/quiz/allQuestions")
	public String allQuestions( Model model){
		model.addAttribute("questions", questionService.allQuestions());
		return "allQuestions";
	}

	@PutMapping("/projects/quiz/allQuestions")
	public String updateQuestions( Model model, @ModelAttribute("question")Questions updatedQuestion){
		questionService.updateQuestion(updatedQuestion);
		return "allQuestions";
	}
	@GetMapping({"/projects/quiz/oneQuestion"})
	public String startUpPage(@ModelAttribute("question") Questions questions, Model model) {
		setRandom();
		model.addAttribute("question", questionService.getQuestion(getRandom()).getQuestion());
		model.addAttribute("odpA", questionService.getQuestion(getRandom()).getOdpA());
		model.addAttribute("odpB", questionService.getQuestion(getRandom()).getOdpB());
		model.addAttribute("odpC", questionService.getQuestion(getRandom()).getOdpC());
		model.addAttribute("odpD", questionService.getQuestion(getRandom()).getOdpD());
		System.out.println(getRandom() +" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		//model.addAttribute("result", questionService.check(null, null));
		// model.addAttribute(questionsService.check(questions.getGoodAnswer()));
		// Service service = new Service();
		// service.questionsGenerator();
		return "oneQuestion";
	}

	@GetMapping("/projects/quiz/addQuestion")
	public String addNewQuestion (Model model){
		model.addAttribute("addQuestion", new Questions());
		System.out.println("addQuestion.getMapping <---------------");

		System.out.println("getMapping <---------------");
		return "addQuestion";
	}
	@PostMapping("/projects/quiz/addQuestion")
	public String addingNewQuestion(@ModelAttribute("addQuestion") Questions addQuestion){
		System.out.println(addQuestion.getCategory()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(addQuestion.getOdpO()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(addQuestion.getOdpA()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("addQuestion.postMapping <---------------");
		System.out.println("postMapping <---------------");
		questionService.addQuestion(addQuestion);
		return "addQuestion";
	}

//in progress
	@GetMapping("/projects/quiz/editQuestion")
	public String editQuestion(@ModelAttribute("questions") Questions editQuestion,
								Model model) {
		model.addAttribute("question", questionService.getQuestion(editQuestion.getId()));
		return "editQuestion";
	}
//----------->
	@PostMapping(value = "/projects/quiz/editQuestion")
	public String editQuestion (@ModelAttribute("question") Questions editedQuestion){
			System.out.println("getId <<<<<<<<<<< "+ editedQuestion.getId());
		System.out.println("getOdpO <<<<<<<<<<< "+ editedQuestion.getOdpO());
		System.out.println("getYear <<<<<<<<<<< "+ editedQuestion.getYear());
		System.out.println("question.getQuestion() <<<<<<<<<<< "+ editedQuestion.getQuestion());
		System.out.println("getCategory() <<<<<<<<<<< "+ editedQuestion.getCategory());
		questionService.edittQuestion(editedQuestion);
		return "allQuestions";
	}
	//??????
	@GetMapping(value = "/projects/quiz/edittQuestion")
	public String edittQuestion (@ModelAttribute("question") Questions editedQuestion){
		System.out.println("getId ?????/ "+ editedQuestion.getId());
		System.out.println("getOdpO <<<<<<<<<<< "+ editedQuestion.getOdpO());
		System.out.println("getYear <<<<<<<<<<< "+ editedQuestion.getYear());
		System.out.println("question.getQuestion() <<<<<<<<<<< "+ editedQuestion.getQuestion());
		System.out.println("getCategory() <<<<<<<<<<< "+ editedQuestion.getCategory());
		questionService.edittQuestion(editedQuestion);
		return "allQuestions";
	}
	///?????????
	@PostMapping("/projects/quiz/editQuestion/editQuestion")
	public String editttQuestion (@ModelAttribute("editQuestion")Questions editQuestion){
		questionService.edittQuestion(editQuestion);
		return "editQuestion";
	}
	@DeleteMapping(value = "delete" )
	public String deleteQuestion(@ModelAttribute("id")Long id ){
		questionService.deleteQuestion(id);
		return "allQuestions";
	}

	@GetMapping("/projects/quiz/deleteQuestion")
	public String getDeleteQuestion(@ModelAttribute("question") Questions deleteQuestion, Model model){
		model.addAttribute("question", deleteQuestion);
		return "deleteQuestion";
	}
	@PostMapping("/projects/quiz/deleteQuestion")
	public String postDeleteQuestion(@RequestParam("id") Long id, @ModelAttribute("question") Questions deleteQuestion, Model model){
		model.addAttribute(deleteQuestion);
		return "deleteQuestion";
	}

}