package ai.thanasakis.uda.physicsquiz;

import static android.R.attr.id;

/**
 * Created by programbench on 4/4/2017.
 */

public class Question {
    private int QuestionType;
    private String QuestionText;
    private String CorrectAnswer;
    private String OptionA;
    private String OptionB;
    private String OptionC;
    private String OptionD;

    public Question(){
        QuestionType=0;
        QuestionText="";
        CorrectAnswer="";
        OptionA="";
        OptionB="";
        OptionC="";
        OptionD="";
    }

    public Question(String quesTion, String opA, String opB, String opC,String opD,
                    String ansWer) {

        QuestionText = quesTion;
        OptionA = opA;
        OptionB = opB;
        OptionC = opC;
        OptionD = opD;
        CorrectAnswer = ansWer;
    }

    public int getType() {

        return QuestionType;
    }

    public void setType(int QuestionType) {

        this.QuestionType = QuestionType;
    }

    public String getQuestionText() {

        return QuestionText;
    }

    public void setQuestionText(String Question2) {

        this.QuestionText = Question2;
    }

    public String getCorrectAnswer() {

        return CorrectAnswer;
    }

    public void setCorrectAnswer(String Answer2) {

        this.CorrectAnswer = Answer2;

    }

    public String getOptionA() {

        return OptionA;
    }

    public void setOptionA(String optionA) {

        OptionA = optionA;
    }

    public String getOptionB() {

        return OptionB;
    }

    public void setOptionB(String optionB) {

        OptionB = optionB;
    }

    public String getOptionC() {

        return OptionC;
    }

    public void setOptionC(String optionC) {

        OptionC = optionC;
    }

    public String getOptionD() {

        return OptionD;
    }

    public void setOptionD(String optionD) {

        OptionD = optionD;
    }
}
