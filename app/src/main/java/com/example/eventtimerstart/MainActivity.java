package com.example.eventtimerstart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.example.eventtimerstart.Rider;

import org.eclipse.paho.android.service.MqttAndroidClient;

//TODO: Create Menu
//TODO: Add list function to the menu
//TODO: Add edit function to menu (with password?)
//TODO: Create Finish program

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Global Variables
    Button[] btn = new Button[13];
    EditText userInput;

    Context context = getApplicationContext();
    MqttHelper mqttHelper;
    MqttAndroidClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Register the buttons
        btn[0] = findViewById(R.id.button0);
        btn[1] = findViewById(R.id.button1);
        btn[2] = findViewById(R.id.button2);
        btn[3] = findViewById(R.id.button3);
        btn[4] = findViewById(R.id.button4);
        btn[5] = findViewById(R.id.button5);
        btn[6] = findViewById(R.id.button6);
        btn[7] = findViewById(R.id.button7);
        btn[8] = findViewById(R.id.button8);
        btn[9] = findViewById(R.id.button9);
        btn[10] = findViewById(R.id.buttonBack);
        btn[11] = findViewById(R.id.buttonClear);
        btn[12] = findViewById(R.id.buttonStart);

        //Setup on click listener
        for(int i = 0; i < 13; i++){
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
            case R.id.buttonClear:
                clearNumber(userInput);
                break;
            case R.id.buttonStart:
                enterNumber(userInput);
                clearNumber(userInput);
                break;
            case R.id.buttonBack:
                goBackAChar(userInput);
                break;
        }
    }

    public void addToArray(String number) {
        userInput = findViewById(R.id.numberEntered);
        userInput.append(number);
    }

    public void clearNumber(EditText input){
        int sLen = input.length();

        if(sLen > 0) {
          String selection = input.getText().toString();
          String result = input.getText().toString().replace(selection, "");
          input.setText(result);
          input.setSelection(input.getText().length());
          userInput = input;
        }
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

    public void enterNumber(EditText input){
        Calendar now = Calendar.getInstance();
        long startTime = now.getTimeInMillis();
        if(input.length() > 0) {

            showTimeNumber(input.getText().toString(), now);
            Rider rider = saveRiderData(input.getText().toString(), startTime);
            insertRider(rider);
            //TODO: Encrypt data
            //TODO: Send data over wifi to server
            String msg = createMessageString(rider);
            mqttHelper.publishMessage(client, msg, 1, "start");
        } else {
            numberError();
        }
    }

    public void showTimeNumber(String number, Calendar now){
        Context context = getApplicationContext();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss:SS", Locale.getDefault());
        Date startTime = now.getTime();
        CharSequence text = "Rider: " + number + " Start Time: " + format.format(startTime);
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public Rider saveRiderData (String number, long startTime){
        int num = Integer.parseInt(number);
        return new Rider(num, startTime, 0);
    }

    private void insertRider(Rider rider){

        RiderDbHelper mDbHelper = new RiderDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RiderContract.RiderEntry.COLUMN_RIDER_NUM, rider.getRiderNumber());
        values.put(RiderContract.RiderEntry.COLUMN_RIDER_START, rider.getStartTime());
        values.put(RiderContract.RiderEntry.COLUMN_RIDER_FINISH, 0);

        long newRowId = db.insert(RiderContract.RiderEntry.TABLE_NAME, null, values);
        if(newRowId == -1) {
            Toast.makeText(this, "Error Saving Rider Data", Toast.LENGTH_SHORT).show();
        }
    }

    public void numberError(){
        // this does not work I am going to have to do this another way.
        // pop up a window to enter a rider number using the internal number pad
        Context context = getApplicationContext();
        CharSequence text = "Please enter a rider number";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private String createMessageString (Rider rider) {
        return rider.toString();
    }
}
