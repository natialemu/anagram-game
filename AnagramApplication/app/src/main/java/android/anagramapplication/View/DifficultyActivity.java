package android.anagramapplication.View;

import android.anagramapplication.R;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DifficultyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
    }

    public void onEasyClick(View view) {

    }

    public void onMediumClick(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void onHardClick(View view) {
        //Intent intent = new Intent(this, GameActivity.class);

    }
}
