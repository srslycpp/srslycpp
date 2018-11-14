package pl.srslycpp.myWeb.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.srslycpp.myWeb.Entity.Questions;
import pl.srslycpp.myWeb.commands.QuestionsCommand;


@Component
public class QuestionsCommandToQuestions implements Converter<QuestionsCommand, Questions> {


    @Synchronized
    @Nullable
    @Override
    public Questions convert(QuestionsCommand source) {
        if (source == null) {
            return null;
        }
        final Questions questions = new Questions();
        questions.setId(source.getId());
        return questions;
    }


}
