package pl.srslycpp.myWeb.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.srslycpp.myWeb.Entity.Questions;
import pl.srslycpp.myWeb.QuestionRepository.QuestionsRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class QuestionsBootstrap implements ApplicationListener<ContextRefreshedEvent> {



    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        questionsRepository.saveAll(getQuestions());
    }

    private final QuestionsRepository questionsRepository;

    public QuestionsBootstrap(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    private List<Questions> getQuestions() {


        List<Questions> questions = new ArrayList<>(2);

        Questions questionOne = new Questions();

        questionOne.setQuestion("asdfhdsfgsdfgsd");
        questionOne.setOdpA("a");
        questionOne.setOdpB("b");
        questionOne.setOdpC("c");
        questionOne.setOdpD("d");
        questionOne.setOdpO("o");
        questionOne.setCategory("programowanie");
        questionOne.setYear("2000");

        questions.add(questionOne);

        Questions questionTwo = new Questions();

        questionTwo.setQuestion("sadfsdhdfghljljhkl");
        questionTwo.setOdpA("d");
        questionTwo.setOdpB("c");
        questionTwo.setOdpC("b");
        questionTwo.setOdpD("a");
        questionTwo.setOdpO("0");
        questionTwo.setCategory("programowanie");
        questionTwo.setYear("2002");

        questions.add(questionTwo);

        return questions;
    }
}
