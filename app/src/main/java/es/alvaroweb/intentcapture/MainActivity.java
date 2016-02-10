package es.alvaroweb.intentcapture;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    StringBuilder message;
    TextView intentText;
    private final String empty = "...empty\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        message = new StringBuilder();
        intent = getIntent();
        intentText = (TextView)findViewById(R.id.intent_text);
        getIntentInfo();
        intentText.setText(message.toString());
    }

    public void getAllExtras(){
        final String FORMAT_LINE = "key:%s value:%s (%s)";
        Bundle extras = intent.getExtras();
        if(extras == null || extras.isEmpty()) {
            message.append(empty);
            return;
        }

        for (String key : extras.keySet()) {
            Object value = extras.get(key);
            message.append(String.format(FORMAT_LINE,
                    key, value.toString(), value.getClass().getSimpleName()) + "\n");
        }
    }

    public void getAllCategories(){
        Set<String> categories = intent.getCategories();
        if(categories == null){
            message.append(empty);
            return;
        }

        for(String key : categories){
            message.append(key+"\n");
        }
    }

    public void getIntentInfo(){
        final String FORMAT_LINE = "%s: %s\n";
        message.append("INTENT INFORMATION\n");
        message.append(String.format(FORMAT_LINE, "Action", intent.getAction()));
        message.append(String.format(FORMAT_LINE, "Data", intent.getDataString()));
        message.append(String.format(FORMAT_LINE, "Data scheme", intent.getScheme()));
        message.append(String.format(FORMAT_LINE, "Type", intent.getType()));

        message.append("\nCATEGORIES\n");
        getAllCategories();

        message.append("\nEXTRA\n");
        getAllExtras();
    }




}
