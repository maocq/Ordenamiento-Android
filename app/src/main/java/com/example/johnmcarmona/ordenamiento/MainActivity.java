package com.example.johnmcarmona.ordenamiento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.johnmcarmona.ordenamiento.ordenamiento.Quicksort;

public class MainActivity extends AppCompatActivity {

    public EditText edt;
    public Button btn;
    private static final int maxSize = 9999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt = (EditText) findViewById(R.id.edtArraySize);
        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int size = Integer.parseInt(edt.getText().toString());
                    if (size > maxSize){
                        Toast.makeText(MainActivity.this, R.string.maximum, Toast.LENGTH_LONG).show();
                        return;
                    }
                    int[] myArray = new int[size];
                    for (int i = 0; i < myArray.length; i++) {
                        myArray[i] = (int) (Math.random() * size + 1);
                    }
                    Intent intent = new Intent(MainActivity.this, OrdenarActivity.class);
                    intent.putExtra("array", myArray);
                    startActivity(intent);

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, R.string.enter_value, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
