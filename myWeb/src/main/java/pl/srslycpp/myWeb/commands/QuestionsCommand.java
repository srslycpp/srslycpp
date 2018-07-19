package pl.srslycpp.myWeb.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionsCommand {

    private Long id;


    private String question;
    private String odpA;
    private String odpB;
    private String odpC;
    private String odpD;
    private String odpO;
    private String category;
    private String year;
}
