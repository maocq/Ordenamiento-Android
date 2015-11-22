package com.example.johnmcarmona.ordenamiento;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johnmcarmona.ordenamiento.ordenamiento.Bucket;
import com.example.johnmcarmona.ordenamiento.ordenamiento.Heap;
import com.example.johnmcarmona.ordenamiento.ordenamiento.Merge;
import com.example.johnmcarmona.ordenamiento.ordenamiento.Quicksort;
import com.example.johnmcarmona.ordenamiento.ordenamiento.Shell;

import java.sql.Time;
import java.util.Locale;

public class OrdenarActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener{

    public Button btnQuick, btnMerge, btnShell, btnHeap, btnBucket;
    public TextView txtQuick, txtMerge, txtShell, txtHeap, txtBucket;
    public int[] myArray;
    private static final int MY_DATA_CHECK_CODE = 1;
    private TextToSpeech mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordenar);

        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

        Bundle params = getIntent().getExtras();
        if(params != null){
            myArray = params.getIntArray("array");
        }
        assignItems();
    }

    @Override
    public void onClick(View v) {
        int[] copy = new int[myArray.length];
        System.arraycopy(myArray, 0, copy, 0, myArray.length);
        long start = 0, end = 0, result = 0;

        switch (v.getId()) {
            case R.id.btnQuick:
                start = System.currentTimeMillis();
                Quicksort.quicksort(copy, 0, copy.length - 1);
                end = System.currentTimeMillis();
                result = end - start;
                txtQuick.setText("Quick: " + result + " Milisegundos");
                break;
            case R.id.btnMerge:
                start = System.currentTimeMillis();
                Merge.mergesort(copy, 0, copy.length - 1);
                end = System.currentTimeMillis();
                result = end - start;
                txtMerge.setText("Merge: " + result + " Milisegundos");
                break;
            case R.id.btnShell:
                start = System.currentTimeMillis();
                Shell.shell(copy);
                end = System.currentTimeMillis();
                result = end - start;
                txtShell.setText("Shell: " + result + " Milisegundos");
                break;
            case R.id.btnHeap:
                start = System.currentTimeMillis();
                Heap.sort(copy);
                end = System.currentTimeMillis();
                result = end - start;
                txtHeap.setText("Heap: " + result + " Milisegundos");
                break;
            case R.id.btnBucket:
                start = System.currentTimeMillis();
                Bucket.sort(copy, copy.length);
                end = System.currentTimeMillis();
                result = end - start;
                txtBucket.setText("Bucket: " + result + " Milisegundos");
                break;
            default:
                break;
        }

        String text = result+" mili-segundos";
        mTts.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }

    private void assignItems(){
        btnQuick = (Button) findViewById(R.id.btnQuick);
        btnQuick.setOnClickListener(OrdenarActivity.this);

        btnMerge = (Button) findViewById(R.id.btnMerge);
        btnMerge.setOnClickListener(OrdenarActivity.this);

        btnShell = (Button) findViewById(R.id.btnShell);
        btnShell.setOnClickListener(OrdenarActivity.this);

        btnHeap = (Button) findViewById(R.id.btnHeap);
        btnHeap.setOnClickListener(OrdenarActivity.this);

        btnBucket = (Button) findViewById(R.id.btnBucket);
        btnBucket.setOnClickListener(OrdenarActivity.this);

        txtQuick = (TextView) findViewById(R.id.txtQuick);
        txtMerge = (TextView) findViewById(R.id.txtMerge);
        txtShell = (TextView) findViewById(R.id.txtShell);
        txtHeap = (TextView) findViewById(R.id.txtHeap);
        txtBucket = (TextView) findViewById(R.id.txtBucket);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Validar si se encuentra instalado el TextToSpeech
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                // success, create the TTS instance
                mTts = new TextToSpeech(this, this);
            } else {
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(
                        TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }

    @Override
    public void onInit(int status) {
        //Establecer lenguaje por defecto
        mTts.setLanguage(new Locale(Locale.getDefault().getLanguage()));
    }
}
