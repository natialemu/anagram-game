package android.anagramapplication.View;

import android.anagramapplication.R;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void onSubmitClick(View view) {

    }

    public void onGivupClick(View view) {
        Intent intent = new Intent(this, AnswerActivity.class);
        startActivity(intent);
    }
}
