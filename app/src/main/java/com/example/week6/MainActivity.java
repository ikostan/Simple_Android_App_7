package com.example.week6;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends Activity {

    private CheckBox box1, box2, box3, box4;
    private Button btnRnd, btnPick;
    private TextView txtResult;
    private Random rnd;
    private CheckBox[] checkArray;
    private int[] arr1, arr2, arr3, arr4;
    private String result, finalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setObjects(); //Create GUI objects

        //Generate Random button event handler
        btnRnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arr1 = setRndGroup(6);
                arr2 = setRndGroup(6);
                arr3 = setRndGroup(6);
                arr4 = setRndGroup(6);

                setText();
            }
        });

        //Pick button event handler
        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isEmpty = false;
                finalResult = "";
                Log.d("main", "1");
                checkArray = new CheckBox[]{box1, box2, box3, box4};
                Log.d("main",checkArray.length + "");
                for(int a = 0; a < checkArray.length; a++){

                    if(checkArray[a].isChecked()){
                        finalResult = finalResult + checkArray[a].getText().toString() + "\n";
                        isEmpty = true;
                    }
                }

                if(isEmpty){

                    txtResult.setText(finalResult);
                }
                else{

                    Toast.makeText(MainActivity.this, "ERROR: Please select at least one of the check-boxes", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Set texts for the checkboxes
    private void setText(){

        result = convertArray(arr1);
        box1.setText(result);

        result = convertArray(arr2);
        box2.setText(result );

        result = convertArray(arr3);
        box3.setText(result );

        result = convertArray(arr4);
        box4.setText(result );
    }

    //Convert array to string
    private String convertArray(int[] arr){

        String str = "";

        str = Arrays.toString(arr);
        str = str.replace("[", "");
        str = str.replace(", ", "-");
        str = str.replace("]", "");

        return str;
    }

    //Generate random numbers
    private int[] setRndGroup(int size){

        int[] array = new int[size];
        rnd = new Random();

        for(int i = 0; i < size; i++){

            int val = 0;

            do{

                val = rnd.nextInt(49) + 1;
            }
            while(Arrays.asList(array).contains(val));

            array[i] = val;
        }

        return array;
    }


    //Create objects (GUI)
    private void setObjects(){
        box1 = (CheckBox) findViewById(R.id.box1);
        box2 = (CheckBox) findViewById(R.id.box2);
        box3 = (CheckBox) findViewById(R.id.box3);
        box4 = (CheckBox) findViewById(R.id.box4);

        btnRnd = (Button) findViewById(R.id.btnRnd);
        btnPick = (Button) findViewById(R.id.btnPick);
        txtResult = (TextView) findViewById(R.id.txtResult);
    }
}
