package pl.srslycpp.myWeb.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.srslycpp.myWeb.Entity.Questions;
import pl.srslycpp.myWeb.commands.QuestionsCommand;

import javax.validation.constraints.Null;


@Component
public class QuestionsToQuestionsCommand implements Converter<Questions, QuestionsCommand> {




    @Synchronized
    @Null
    @Override
    public QuestionsCommand convert(Questions source) {

        if (source == null){
            return null;

    }

    final QuestionsCommand questionsCommand = new QuestionsCommand();

    questionsCommand.setId(source.getId());
    questionsCommand.setCategory(source.getCategory());
    questionsCommand.setOdpA(source.getOdpA());
    questionsCommand.setOdpB(source.getOdpB());
    questionsCommand.setOdpC(source.getOdpC());
    questionsCommand.setOdpD(source.getOdpD());
    questionsCommand.setOdpO(source.getOdpO());
    questionsCommand.setQuestion(source.getQuestion());
    questionsCommand.setYear(source.getYear());
    return questionsCommand;
}
}
