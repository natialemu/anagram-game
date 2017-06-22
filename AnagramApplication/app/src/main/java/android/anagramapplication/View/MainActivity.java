package android.anagramapplication.View;

import android.anagramapplication.R;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPlayClick(View view) {

        Intent intent = new Intent(this,DifficultyActivity.class);
        startActivity(intent);
        //goes to the game activity
    }

    public void onHelpClick(View view) {
        //goes to the help activity
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }
}
