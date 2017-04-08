package ai.thanasakis.uda.physicsquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        RatingBar bar = (RatingBar) findViewById(R.id.ratingBar1);
        bar.setMax(5);
        bar.setNumStars(5);
        bar.setStepSize(1);
        TextView tvAnsweredInfo = (TextView) findViewById(R.id.tvAnsweredInfo);
        TextView t = (TextView) findViewById(R.id.textResult);
        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");
        bar.setRating(score);
        tvAnsweredInfo.setText("You have answered " + score + " / 5 questions  correctly!");
        if (score == 5) {
            t.setText("Score is Excellent !");
        } else if (score == 4) {
            t.setText("Score is Very Good !");
        } else if (score == 3) {
            t.setText("Score is Good !");
        } else if (score == 2) {
            t.setText("Score is Average !");
        } else if (score == 1) {
            t.setText("Score is  Below Average !");
        } else {
            t.setText("Score is Poor ! You need to practice more!");
        }
        Button btnDone = (Button) findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }


        });
    }
}
