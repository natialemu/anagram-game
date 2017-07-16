package android.anagramapplication.View;

import android.anagramapplication.Model.AnagramDictionary;
import android.anagramapplication.Model.AnagramDictionaryInterface;
import android.anagramapplication.R;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Scanner;

public class DifficultyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        //WordController controller = new WordControllerImpl();
        //passWordsToController();
        // acces the file in raw and then pass each word to a controller
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
