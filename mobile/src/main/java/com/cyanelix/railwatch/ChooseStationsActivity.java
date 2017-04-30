package com.cyanelix.railwatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.cyanelix.railwatch.microtypes.EditTextId;

public class ChooseStationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.cyanelix.railwatch.R.layout.activity_choose_stations);
    }

    public void sendMessage(View view) {


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @NonNull
    private String getEditTextValue(EditTextId editTextId) {
        EditText editText = (EditText) findViewById(editTextId.getId());
        return editText.getText().toString();
    }
}
