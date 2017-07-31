package android.anagramapplication.View;

import android.anagramapplication.Model.AnagramDictionary;
import android.anagramapplication.Model.AnagramDictionaryInterface;
import android.anagramapplication.R;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Scanner;

public class GameActivity extends AppCompatActivity {
    private AnagramDictionaryInterface anagramDictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        anagramDictionary = AnagramDictionary.getDictionary();

        passWordsToController();
        displayQuestion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void displayQuestion() {
        TextView textView = (TextView) findViewById(R.id.word_display);
        String word = anagramDictionary.getWordToBeDisplayed();
        textView.setText(word);



    }

    private void passWordsToController() {
        Scanner scan  = new Scanner(getResources().openRawResource(R.raw.words));
        String wordToBePassed;
        while(scan.hasNextLine()){
            wordToBePassed = scan.nextLine().trim();
            anagramDictionary.mapWord(wordToBePassed);
            if(!scan.hasNextLine()){
                break;
            }

        }
    }

    public void onSubmitClick(View view) {
        TextView textView = (TextView)findViewById(R.id.word_display);
        String question = textView.getText().toString();
        EditText editText= (EditText) findViewById(R.id.user_answer);
        String answer = editText.getText().toString();
        TextView notifyTextView = (TextView)findViewById(R.id.notify_user);
        if(anagramDictionary.checkAnswer(question,answer) && answer.length() > 1){

            //also check if the user has got all the answers
            notifyTextView.setText("Correct! ");

        }else{
            notifyTextView.setText("Try again!");
        }
        //make sure the text is not empty
        //if it is not empty send it to the model for verification
        //if verification passes, notify the user

    }

    public void onGivupClick(View view) {
        Intent intent = new Intent(this, AnswerActivity.class);
        startActivity(intent);
    }
}
