package com.cyanelix.railwatch;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cyanelix.railwatch.microtypes.EditTextId;
import com.cyanelix.railwatch.microtypes.IntentKey;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.cyanelix.railwatch.R.layout.activity_main);
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        putEditTextValueInIntent(intent, IntentKey.FROM_STATION, EditTextId.FROM_STATION);
        putEditTextValueInIntent(intent, IntentKey.TO_STATION, EditTextId.TO_STATION);
        startActivity(intent);
    }

    private void putEditTextValueInIntent(Intent intent, IntentKey intentKey, EditTextId editTextId) {
        intent.putExtra(intentKey.getKey(), getEditTextValue(editTextId));
    }

    @NonNull
    private String getEditTextValue(EditTextId editTextId) {
        EditText editText = (EditText) findViewById(editTextId.getId());
        return editText.getText().toString();
    }
}
