package ai.thanasakis.uda.physicsquiz;

import android.app.Fragment;
import android.os.Bundle;

import static android.R.attr.data;

/**
 * Created by programbench on 4/8/2017.
 */

public class RetainedFragment extends Fragment {

    // data object we want to retain
    private Question savedQuestion;
    private QuestionsAndAnswers  savedPool;
    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    public void setData(Question data1, QuestionsAndAnswers data2) {
        this.savedPool = data2;
        this.savedQuestion =data1;
    }

    public Question getQuestion() {
        return savedQuestion;
    }

    public QuestionsAndAnswers getQuestionAndAnswers() {
        return savedPool;
    }
}