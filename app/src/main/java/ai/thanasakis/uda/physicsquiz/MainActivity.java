package ai.thanasakis.uda.physicsquiz;

import android.app.FragmentManager;
import android.content.Intent;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    private static final String STATE_SCORE = "playerscore";
    private static final String STATE_QUESTION = "questionnumber";
    private static final String BUTTON_TEXT = "buttontext";
    private static final String TAG_RETAINED_FRAGMENT = "RetainedFragment";
    // UI Items
    RadioButton RadioButton1;
    RadioButton RadioButton2;
    RadioButton RadioButton3;
    RadioButton RadioButton4;
    CheckBox Checkbox1;
    CheckBox Checkbox2;
    CheckBox Checkbox3;
    CheckBox Checkbox4;
    EditText EditAnswer;
    Button ButtonNext;
    LinearLayout LayoutRadio;
    LinearLayout LayoutCheck;
    LinearLayout LayoutEdit;
    TextView QuestionText;
    TextView QuestionType;
    RadioGroup RadioGroup1;
    // End UI Items
    boolean answered = false;
    Question CurrentQuestion;
    QuestionsAndAnswers QuestionPool = new QuestionsAndAnswers();
    int QuestionNumber = 1;
    int Score = 0;
    private RetainedFragment mRetainedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getFragmentManager();
        mRetainedFragment = (RetainedFragment) fm.findFragmentByTag(TAG_RETAINED_FRAGMENT);
        // create the fragment and data the first time
        if (mRetainedFragment == null) {
            // add the fragment
            mRetainedFragment = new RetainedFragment();
            fm.beginTransaction().add(mRetainedFragment, TAG_RETAINED_FRAGMENT).commit();
            // load data from a data source or perform any calculation
        } else {
            CurrentQuestion = mRetainedFragment.getQuestion();
            QuestionPool = mRetainedFragment.getQuestionAndAnswers();
        }
        RadioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        RadioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        RadioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        Checkbox1 = (CheckBox) findViewById(R.id.checkBox1);
        Checkbox2 = (CheckBox) findViewById(R.id.checkBox2);
        Checkbox3 = (CheckBox) findViewById(R.id.checkBox3);
        Checkbox4 = (CheckBox) findViewById(R.id.checkBox4);
        EditAnswer = (EditText) findViewById(R.id.EditAnswer);
        ButtonNext = (Button) findViewById(R.id.buttonNext);
        LayoutRadio = (LinearLayout) findViewById(R.id.LayoutRadio);
        LayoutCheck = (LinearLayout) findViewById(R.id.LayoutCheck);
        LayoutEdit = (LinearLayout) findViewById(R.id.LayoutEdit);
        QuestionText = (TextView) findViewById(R.id.textQuestion);
        QuestionType = (TextView) findViewById(R.id.textViewQuestionType);
        RadioGroup1 = (RadioGroup) findViewById(R.id.QuestionRadioGroup);
        if (savedInstanceState != null) {
            Score = savedInstanceState.getInt(STATE_SCORE);
            QuestionNumber = savedInstanceState.getInt(STATE_QUESTION);
            ButtonNext.setText(savedInstanceState.getString(BUTTON_TEXT));
        } else {
            CurrentQuestion = QuestionPool.getQuestion();
            mRetainedFragment.setData(CurrentQuestion, QuestionPool);
            ButtonNext.setText("Next");
        }
        displayQuestion();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // perform other onPause related actions
        // this means that this activity will not be recreated now, user is leaving it
        // or the activity is otherwise finishing
        if (isFinishing()) {
            FragmentManager fm = getFragmentManager();
            // we will not need this fragment anymore, this may also be a good place to signal
            // to the retained fragment object to perform its own cleanup.
            fm.beginTransaction().remove(mRetainedFragment).commit();
        } else {
            mRetainedFragment.setData(CurrentQuestion, QuestionPool);
        }
    }

    public void onClicκNext(View v) {
        Score = Score + checkScore();
        if (!answered) {
            Toast.makeText(getApplicationContext(), "Please Answer the Question First!", Toast.LENGTH_LONG).show();
        } else {
            if (QuestionNumber == 5) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                Bundle b = new Bundle();
                b.putInt("score", Score); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
                finish();
            } else {
                if (QuestionNumber == 4) {
                    ButtonNext.setText("Finish!");
                }
                CurrentQuestion = QuestionPool.getQuestion();
                QuestionNumber++;
                displayQuestion();
            }
        }
    }

    public int checkScore() {
        int a = 0;
        answered = false;
        switch (CurrentQuestion.getType()) {
            case 1:
                if (RadioButton1.isChecked()) {
                    answered = true;
                    if (RadioButton1.getText() == CurrentQuestion.getCorrectAnswer()) {
                        a = 1;
                    }
                } else if (RadioButton2.isChecked()) {
                    answered = true;
                    if (RadioButton2.getText() == CurrentQuestion.getCorrectAnswer()) {
                        a = 1;
                    }
                } else if (RadioButton3.isChecked()) {
                    answered = true;
                    if (RadioButton3.getText() == CurrentQuestion.getCorrectAnswer()) {
                        a = 1;
                    }
                } else if (RadioButton4.isChecked()) {
                    answered = true;
                    if (RadioButton4.getText() == CurrentQuestion.getCorrectAnswer()) {
                        a = 1;
                    }
                }
                break;
            case 2:
                answered = !(EditAnswer.getText().toString().isEmpty());
                if (EditAnswer.getText().toString().equalsIgnoreCase(CurrentQuestion.getCorrectAnswer())) {
                    a = 1;
                }
                break;
            case 3:
                if (RadioButton1.isChecked()) {
                    answered = true;
                    if (RadioButton1.getText() == CurrentQuestion.getCorrectAnswer()) {
                        a = 1;
                    }
                } else if (RadioButton2.isChecked()) {
                    answered = true;
                    if (RadioButton2.getText() == CurrentQuestion.getCorrectAnswer()) {
                        a = 1;
                    }
                }
                break;
            case 4:
                String b = "";
                if (Checkbox1.isChecked()) {
                    answered = true;
                    b = b + Checkbox1.getTag();
                }
                if (Checkbox2.isChecked()) {
                    answered = true;
                    b = b + Checkbox2.getTag();
                }
                if (Checkbox3.isChecked()) {
                    answered = true;
                    b = b + Checkbox3.getTag();
                }
                if (Checkbox4.isChecked()) {
                    answered = true;
                    b = b + Checkbox4.getTag();
                }
                if (b.equals(CurrentQuestion.getCorrectAnswer())) {
                    a = 1;
                }
                break;
        }
        return a;
    }

    /**
     * Displays the score of the teams.
     * TeamNum accepts 2 values 1 is the home team 2 the guest team.
     */
    public void displayQuestion() {
        QuestionText.setText(CurrentQuestion.getQuestionText());
        switch (CurrentQuestion.getType()) {
            case 1:
                LayoutRadio.setVisibility(View.VISIBLE);
                LayoutEdit.setVisibility(View.INVISIBLE);
                LayoutCheck.setVisibility(View.INVISIBLE);
                RadioButton3.setVisibility(View.VISIBLE);
                RadioButton4.setVisibility(View.VISIBLE);
                QuestionType.setText("Select one");
                RadioButton1.setText(CurrentQuestion.getOptionA());
                RadioButton2.setText(CurrentQuestion.getOptionB());
                RadioButton3.setText(CurrentQuestion.getOptionC());
                RadioButton4.setText(CurrentQuestion.getOptionD());
                RadioGroup1.clearCheck();
                break;
            case 2:
                LayoutEdit.setVisibility(View.VISIBLE);
                LayoutRadio.setVisibility(View.INVISIBLE);
                LayoutCheck.setVisibility(View.INVISIBLE);
                EditAnswer.setText("");
                QuestionType.setText("Fill the gap");
                break;
            case 3:
                LayoutRadio.setVisibility(View.VISIBLE);
                LayoutEdit.setVisibility(View.INVISIBLE);
                LayoutCheck.setVisibility(View.INVISIBLE);
                RadioButton1.setText(CurrentQuestion.getOptionA());
                RadioButton2.setText(CurrentQuestion.getOptionB());
                RadioButton3.setVisibility(View.INVISIBLE);
                RadioButton4.setVisibility(View.INVISIBLE);
                QuestionType.setText("True or False?");
                RadioGroup1.clearCheck();
                break;
            case 4:
                LayoutCheck.setVisibility(View.VISIBLE);
                LayoutEdit.setVisibility(View.INVISIBLE);
                LayoutRadio.setVisibility(View.INVISIBLE);
                Checkbox1.setText(CurrentQuestion.getOptionA());
                Checkbox2.setText(CurrentQuestion.getOptionB());
                Checkbox3.setText(CurrentQuestion.getOptionC());
                Checkbox4.setText(CurrentQuestion.getOptionD());
                QuestionType.setText("Check every correct answer:");
                Checkbox1.setChecked(false);
                Checkbox2.setChecked(false);
                Checkbox3.setChecked(false);
                Checkbox4.setChecked(false);
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt(STATE_SCORE, Score);
        savedInstanceState.putInt(STATE_QUESTION, QuestionNumber);
        savedInstanceState.putString(BUTTON_TEXT, ButtonNext.getText().toString());
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        // Restore state members from saved instance
        super.onRestoreInstanceState(savedInstanceState);
    }
}
