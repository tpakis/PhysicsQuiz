package ai.thanasakis.uda.physicsquiz;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;



public class QuestionsAndAnswers {

    private String Questions [] = {
            "Which one of the following orbits around the nucleus ?",
            "The atomic mass is which of the following added together ?",
            "An atom which has a charge due to loosing electrons is called ?",
            "Which two elements are combined in order to make the ionic compound ? CaO",
            "When the electron is further from the ________ the greater the electrons energy.",
            "Heat, light or energy can ________ an electron and move it to an orbit further from the nucleus.",
            "The electron has no mass.",
            "To explain the continuous spectrum of hot objects, Planck proposed that light could only be absorbed or emitted in discrete amounts.",
            "Bohr failed to explain why an accelerated electron does not radiate energy.",
            "The nucleus of an atom occupies more than half of the atom's volume.",
            "Which of the following proposed an atom model ?",
            "Which of the following can be found in the nucleus ?",
            "Which of the following have charge ?"
    };

    private String Choices [] [] = {

            { "Neutron", "Electron", "Proton", "Ion"},
            { "Electrons + Neutrons", "Protons + Electrons", "Atomic Number + Protons", "Protons + Neutrons"},
            { "Positron", "Cation", "Proton", "Anion"},
            { "Carbon, Oxygen", "Calcium, Osmium", "Calcium, Oxygen", "Carbon, Osmium"},
            { "","","","" },
            { "","","","" },
            { "True", "False","","" },
            { "True", "False","","" },
            { "True", "False","","" },
            { "True", "False","","" },
            { "Bohr", "Einstein", "Newton","Rutherford" },
            { "Neutron", "Electron", "Proton", "Atom"},
            { "Neutron", "Electron", "Proton", "Photon"},

    };
    private int QuestionType [] = {1,1,1,1,2,2,3,3,3,3,4,4,4};
    private List<Integer> QuestionsPool =  new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12));
    private String CorrectAnswers [] = {"Electron", "Protons + Neutrons", "Cation", "Calcium, Oxygen", "nucleus", "excite", "False","True","True","False","ad","ac","bc"};

    public Question getQuestion(){
        Question quest = new Question();
        int num = randInt(0,QuestionsPool.size()-1);
        num = QuestionsPool.get(num);
        QuestionsPool.remove(QuestionsPool.indexOf(num));
        quest.setQuestionText(Questions[num]);
        quest.setCorrectAnswer(CorrectAnswers[num]);
        quest.setType(QuestionType[num]);
        quest.setOptionA(Choices[num][0]);
        quest.setOptionB(Choices[num][1]);
        quest.setOptionC(Choices[num][2]);
        quest.setOptionD(Choices[num][3]);
        return quest;
    }

    /**
     * Returns a psuedo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimim value
     * @param max Maximim value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {
        // Usually this can be a field rather than a method variable
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
