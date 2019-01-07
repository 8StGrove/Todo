package com.example.todo;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TodoActivity extends AppCompatActivity {

    private String[] mTodos;
    private int mTodoIndex = 0;
    private static final String TODO_INDEX = "todoIndex";
    public static final String TAG = "TodoActivity";

// Rotation problem fix, creates a 'savedInstanceState' and stores the value in a bundle object
// which can later be restored
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(TODO_INDEX, mTodoIndex);
    }

//View the menu option
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // restored here after checking for saved states to restore after a rotation event as as todo index
        if (savedInstanceState != null){
            mTodoIndex = savedInstanceState.getInt(TODO_INDEX, 0);
        }

        // call the super class onCreate to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);

        // set the user interface layout for this Activity
        // the layout file is defined in the project res/layout/activity_todo.xml file
        setContentView(R.layout.activity_todo);

        // initialize member TextView so we can manipulate it later
        final TextView TodoTextView;
        TodoTextView = (TextView) findViewById(R.id.textViewTodo);

        // read the todo array from res/values/strings.xml
        Resources res = getResources();
        mTodos = res.getStringArray(R.array.todo);
        // display the first task from mTodo array in the TodoTextView
        TodoTextView.setText(mTodos[mTodoIndex]);


        Button buttonPrev;
        buttonPrev = (Button) findViewById(R.id.buttonPrev);

        //onClick listerned for the Prev button
        buttonPrev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View V) {
               // mTodoIndex -= 1;
                mTodoIndex = (mTodoIndex - 1) % mTodos.length;
                TodoTextView.setText(mTodos[mTodoIndex]);
            }
        });



        Button buttonNext;
        buttonNext = (Button) findViewById(R.id.buttonNext);

        // OnClick listener for the  Next button
        buttonNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //mTodoIndex += 1;
                mTodoIndex = (mTodoIndex + 1) % mTodos.length;
                TodoTextView.setText(mTodos[mTodoIndex]);

            }
        });


    }
}