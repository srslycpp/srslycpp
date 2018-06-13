package pl.srslycpp.myWeb.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.srslycpp.myWeb.Entity.Questions;
import pl.srslycpp.myWeb.Repository.QuestionsRepository;

import java.util.ArrayList;
import java.util.List;



@Service
public class QuestionService {

    @Autowired
    private QuestionsRepository questionsRepository;

//    private List<Questions> questions1 = Arrays.asList(new Questions(1L, "1", "1", "1", "1", "1", "1", "1", 1),
//            new Questions(2L, "1", "1", "1", "1", "1", "1", "1", 1),
//            new Questions(3L, "1", "1", "1", "1", "1", "1", "1", 1),
//            new Questions(4L, "1", "1", "1", "1", "1", "1", "1", 1));

    public List<Questions> allQuestions(){
        List<Questions> questions = new ArrayList<>(questionsRepository.findAll());
        return questions;
    }

    public Questions getQuestion(Long id) {
        Questions oneQuestion = questionsRepository.findById(id).get();
        return oneQuestion;
    }
    public Questions updateQuestion(Questions updateQuestion){
        return questionsRepository.save(updateQuestion);
    }

    public boolean check(String ok, Long id) {

        if (questionsRepository.findById(id).get().getOdpO().equals(ok)) {
            return true;
        }
        return false;
    }

    public Questions addQuestion(Questions questions) {
           return questionsRepository.save(questions);
    }

    public Questions editQuestion (Long id){
        Questions question = questionsRepository.findById(id).get();
        return question;
    }
    public Questions edittQuestion (Questions editQuestions){
        System.out.println("QuestionService getYear "+ editQuestions.getYear());
        System.out.println("QuestionService getGoodA "+ editQuestions.getOdpO());
        System.out.println("QuestionService getQuestion "+ editQuestions.getQuestion());
        System.out.println("QuestionService getId "+ editQuestions.getId());
        System.out.println("QuestionService getCategory "+ editQuestions.getCategory());
        Questions question = questionsRepository.save(editQuestions);
        return question;
    }
    public void deleteQuestion(Long id){
        questionsRepository.deleteById(id);
    }


}
