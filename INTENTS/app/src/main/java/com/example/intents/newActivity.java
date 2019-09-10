package com.example.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class newActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
    }

    public void lastactivity(View view){
        Intent i = new Intent(this, lastactivity.class);
        i.putExtra("Value1", "This value one for ActivityTwo ");
        i.putExtra("Value2", "This value two ActivityTwo");
        startActivity(i);
    }
}
