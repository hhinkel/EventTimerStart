package com.example.eventtimerstart;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Global Variables
    Button btn[] = new Button[12];
    EditText userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Register the buttons
        btn[0] = (Button) findViewById(R.id.button0);
        btn[1] = (Button) findViewById(R.id.button1);
        btn[2] = (Button) findViewById(R.id.button2);
        btn[3] = (Button) findViewById(R.id.button3);
        btn[4] = (Button) findViewById(R.id.button4);
        btn[5] = (Button) findViewById(R.id.button5);
        btn[6] = (Button) findViewById(R.id.button6);
        btn[7] = (Button) findViewById(R.id.button7);
        btn[8] = (Button) findViewById(R.id.button8);
        btn[9] = (Button) findViewById(R.id.button9);
        btn[10] = (Button) findViewById(R.id.buttonBack);
        btn[11] = (Button) findViewById(R.id.buttonEnter);

        //Setup on click listener
        for(int i = 0; i < 12; i++){
            btn[i].setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button0:
                addToArray("0");
                break;
            case R.id.button1:
                addToArray("1");
                break;
            case R.id.button2:
                addToArray("2");
                break;
            case R.id.button3:
                addToArray("3");
                break;
            case R.id.button4:
                addToArray("4");
                break;
            case R.id.button5:
                addToArray("5");
                break;
            case R.id.button6:
                addToArray("6");
                break;
            case R.id.button7:
                addToArray("7");
                break;
            case R.id.button8:
                addToArray("8");
                break;
            case R.id.button9:
                addToArray("9");
                break;
            case R.id.buttonEnter:
                break;
            case R.id.buttonBack:
                goBackAChar(userInput);
                break;


        }
    }

    public void addToArray(String number) {
        userInput = (EditText) findViewById(R.id.numberEntered);
        userInput.append(number);
    }

    public void goBackAChar(EditText input) {
        int sLen = input.length();

        if(sLen > 0) {
            String selection = input.getText().toString().substring(sLen - 1, sLen);
            String result = input.getText().toString().replace(selection, "");
            input.setText(result);
            input.setSelection(input.getText().length());
            userInput = input;
        }
    }
}
