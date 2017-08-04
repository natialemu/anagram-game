package android.anagramapplication.View;

import android.anagramapplication.Model.AnagramDictionary;
import android.anagramapplication.Model.AnagramDictionaryInterface;
import android.anagramapplication.Model.RecyclerViewItem;
import android.anagramapplication.R;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GameActivity extends AppCompatActivity {
    private AnagramDictionaryInterface anagramDictionary;
    private GameAdapter adapter;
    private RecyclerView recyclerView;
    private List<RecyclerViewItem> recyclerViewItems;

    private boolean fabExpanded = false;
    private FloatingActionButton fabSettings;

    //Linear layout holding the Save submenu
    private LinearLayout layoutFabSubmit;

    //Linear layout holding the Edit submenu
    private LinearLayout layoutFabGiveup;

    private LinearLayout layoutFabReset;


    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.solution_recycler_view);
        recyclerView.setHasFixedSize(false);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerViewItems = new ArrayList<>();

        adapter = new GameAdapter(this,recyclerViewItems);
        recyclerView.setAdapter(adapter);






        anagramDictionary = AnagramDictionary.getDictionary();

        passWordsToController();


        /*ImageView icon = new ImageView(this);
        icon.setImageResource(R.drawable.button_action_selector);
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        ImageView firstFab = new ImageButton(this);
        firstFab.setImageResource(R.drawable.button_sub_action_dark_touch);
        SubActionButton firstBuutton = itemBuilder.setContentView(firstFab).build();

        ImageView secondFab = new ImageButton(this);
        firstFab.setImageResource(R.drawable.button_sub_action_dark_touch);
        SubActionButton secondButton = itemBuilder.setContentView(secondFab).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(firstBuutton)
                .addSubActionView(secondButton)
                .attachTo(actionButton)
                .build();

        firstBuutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmitClick(view);
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onGivupClick(view);
            }
        });*/

        fabSettings = (FloatingActionButton)findViewById(R.id.mainFab);

        layoutFabSubmit = (LinearLayout) findViewById(R.id.layoutFabSubmit);
        layoutFabGiveup = (LinearLayout) findViewById(R.id.layoutFabGiveup);
        layoutFabReset = (LinearLayout) findViewById(R.id.layoutFabReset);

        closeSubMenusFab();

        fabSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(fabExpanded){
                    closeSubMenusFab();
                }else{
                    openSubMenusFab();
                }

            }
        });

        FloatingActionButton submitFab = (FloatingActionButton)findViewById(R.id.fabSubmit);

        FloatingActionButton giveupFab = (FloatingActionButton)findViewById(R.id.fabGiveup);

        FloatingActionButton resetFab = (FloatingActionButton)findViewById(R.id.fabReset);

        submitFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fabExpanded){
                    onSubmitClick(view);
                }
            }
        });
        giveupFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fabExpanded){
                    onGivupClick(view);
                }
            }
        });

        resetFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fabExpanded){
                    reset();
                }
            }


        });

        displayQuestion();





    }

    private void reset() {
        int current = adapter.getItemCount();
        displayQuestion();
        recyclerViewItems.clear();
        adapter.notifyItemRangeRemoved(current,current);
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
        if(id == R.id.game_help){
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }



    private void displayQuestion() {
        TextView textView = (TextView) findViewById(R.id.word_display);
        String word = anagramDictionary.getWordToBeDisplayed();
        textView.setText(word);
        closeSubMenusFab();



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
        //TextView notifyTextView = (TextView)findViewById(R.id.notify_user);
        if(anagramDictionary.checkAnswer(question,answer) && answer.length() > 1 && answerIsNotRepeated(answer)){

            //also check if the user has got all the answers
            //notifyTextView.setText("Correct! ");//turn this into a toast notification
            Toast.makeText(this,"Correct!",Toast.LENGTH_LONG).show();
            //add the solution through the recycler View
            recyclerViewItems.add(new RecyclerViewItem(answer));
            adapter.notifyItemInserted(recyclerViewItems.size()-1);

        }else{
            Toast.makeText(this,"Try again",Toast.LENGTH_LONG).show();

            //notifyTextView.setText("Try again!");//turn this into a toast notification
        }
        closeSubMenusFab();
        //make sure the text is not empty
        //if it is not empty send it to the model for verification
        //if verification passes, notify the user

    }

    private boolean answerIsNotRepeated(String answer) {

        for(RecyclerViewItem item: recyclerViewItems){
            if(item.getSolution().equals(answer)){
                return false;
            }
        }
        return true;
    }



    public void onGivupClick(View view) {
        TextView textView = (TextView)findViewById(R.id.word_display);
        String question = textView.getText().toString();
        List<String> solutions = anagramDictionary.getAllPossibleAnagrams(question);


            addToRecyclerView(solutions);
        closeSubMenusFab();


    }

    private void addToRecyclerView(List<String> solutions) {


        int currentSize = adapter.getItemCount();
        List<RecyclerViewItem> correctItems = new ArrayList<>();
        for(String solution: solutions) {
            if (answerIsNotRepeated(solution)) {
                correctItems.add(new RecyclerViewItem(solution));
                //recyclerViewItems.add(new RecyclerViewItem(s));
            }
        }
        recyclerViewItems.addAll(correctItems);
        adapter.notifyItemRangeInserted(currentSize,correctItems.size());
    }

    private void closeSubMenusFab(){
        layoutFabSubmit.setVisibility(View.INVISIBLE);
        layoutFabGiveup.setVisibility(View.INVISIBLE);
        layoutFabReset.setVisibility(View.INVISIBLE);
        fabSettings.setBackgroundResource(R.drawable.ic_add_black_24dp);
        fabExpanded = false;
    }

    //Opens FAB submenus
    private void openSubMenusFab() {
        layoutFabSubmit.setVisibility(View.VISIBLE);
        layoutFabGiveup.setVisibility(View.VISIBLE);
        layoutFabReset.setVisibility(View.VISIBLE);
        fabSettings.setBackgroundResource(R.drawable.ic_close_black_24dp);
        fabExpanded = true;
    }


}
