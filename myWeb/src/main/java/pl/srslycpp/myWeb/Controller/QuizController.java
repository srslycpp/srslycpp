package pl.srslycpp.myWeb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.srslycpp.myWeb.Entity.Questions;
import pl.srslycpp.myWeb.Service.QuestionService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuizController {

	@Autowired
	private QuestionService questionService;

	private long random;
	//public long random = new RandomDataGenerator().nextLong(1L, 10L);
	//Questions questions = new Questions();

	public static long random() {

		return (long) Math.random();
	}
	public long getRandom() {
		return random;
	}

	public void setRandom() {
		this.random = 1L + (long) (Math.random() * (20L - 1L));
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

//	@PutMapping("/projects/quiz/allQuestions")
//	public String updateQuestions( Model model, @ModelAttribute("question")Questions allQuestion){
//		questionService.updateQuestion(allQuestion);
//		return "allQuestions";
//	}
	@GetMapping({"/projects/quiz/oneQuestion"})
	public String oneQuestion(@ModelAttribute("question") Questions question, Model model) {
		setRandom();
		model.addAttribute("question", questionService.getQuestion(getRandom()).getQuestion());
		model.addAttribute("odpA", questionService.getQuestion(getRandom()).getOdpA());
		model.addAttribute("odpB", questionService.getQuestion(getRandom()).getOdpB());
		model.addAttribute("odpC", questionService.getQuestion(getRandom()).getOdpC());
		model.addAttribute("odpD", questionService.getQuestion(getRandom()).getOdpD());
		System.out.println(getRandom() +" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
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

//ok
//	@GetMapping("/projects/quiz/editQuestion")
//	public String editQuestion(@ModelAttribute("questions") Questions editQuestion,
//								Model model) {
//		model.addAttribute("question", questionService.getQuestion(editQuestion.getId()));
//		return "editQuestion";
//	}
//----------->
	@GetMapping("/projects/quiz/chooseQuestion")
	public String chooseQuestion(@ModelAttribute("questions") Questions id, //Why no Long ? "No primary constructor"
								 @ModelAttribute("XXX") Questions editQuestion,
//								 @ModelAttribute("action") String btnName,
// 								 @ModelAttribute("q") String question,
								 HttpServletRequest request,
								 Model model) throws Exception {

		String btnName = request.getParameter("action");
		//Long id = request.getParameter("id");
		//Long c = id;
		if (id == null || id.equals("")) {
			System.out.println("if id -> |"+id+"| <-");
			System.out.println("if null id from editQuestion >> "+ editQuestion.getId()+" <<>> " );
			System.out.println("if null question from editQuestion >> "+ editQuestion.getQuestion()+" <<");
			return "noId";
		} else {
			if (btnName.equals("Edit")) {

				System.out.println("Edit ID ->> "+id);
				System.out.println("if null id from editQuestion >> "+ editQuestion.getId()+" <<>> " );
				System.out.println("if Q from editQuestion >> "+ editQuestion.getQuestion()+" <<");
				System.out.println("if Category from editQuestion >> "+ editQuestion.getCategory()+" <<");
				System.out.println("if year from editQuestion >> "+ editQuestion.getYear()+" <<");
				System.out.println("if A from editQuestion >> "+ editQuestion.getOdpA()+" <<");
				System.out.println("if OdpO from editQuestion >> "+ editQuestion.getOdpO()+" <<");
				System.out.println("if id from editQuestion >> "+ editQuestion.getId()+" <<");
				//model.addAttribute("question", questionService.getQuestion(editedQuestion.getId()));
				//model.addAttribute("editQuestion", questionService.getQuestion(editQuestion.getId()));
				model.addAttribute("editQuestion", questionService.getQuestion(id.getId()));
				return "editQuestion";

			} else if (btnName.equals("Delete"))
				System.out.println("else if id from editQuestion >> "+ editQuestion.getId()+" <<");
				System.out.println("Delete ID ->> "+id);
				questionService.deleteQuestion(editQuestion.getId());
			return "oneQuestion";
		}
	}
	@PostMapping(value = "/projects/quiz/editQuestion")
	public String editQuestion (@ModelAttribute("editQuestion") Questions editedQuestion){
		System.out.println("getId ?????/ "+ editedQuestion.getId());
		System.out.println("getOdpO <<<<<<<<<<< "+ editedQuestion.getOdpO());
		System.out.println("getYear <<<<<<<<<<< "+ editedQuestion.getYear());
		System.out.println("question.getQuestion() <<<<<<<<<<< "+ editedQuestion.getQuestion());
		System.out.println("getCategory() <<<<<<<<<<< "+ editedQuestion.getCategory());
		questionService.editQuestion(editedQuestion);
		return "allQuestions";
	}

//	@RequestMapping(value = "/showForm", method=RequestMethod.GET)
//	public String showForm(Model model) {
//		Foo foo = new Foo();
//		foo.setBar("bar");
//
//		model.addAttribute("foo", foo);
//  ...
//	}
//
//	@RequestMapping(value = "/processForm", method=RequestMethod.POST)
//	public String processForm(@ModelAttribute(value="foo") Foo foo) {
//  ...
//	}
//	html:
//
//<form action="#" th:action="@{/processForm}" th:object="${foo}" method="post">
//  <input type="text" th:field="*{bar}" />
//  <input type="submit" />
//</form>
//	Foo.java:
//
//	public class Foo {
//		private String bar;
//
//		public String getBar() {
//			return bar;
//		}
//
//		public void setBar(String bar) {
//			this.bar = bar;
//		}
//	}
}