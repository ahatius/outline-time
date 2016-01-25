package ch.outline.time.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ch.outline.time.android.R;
import ch.outline.time.android.controllers.TimeController;

public class MainActivity extends AppCompatActivity {
    TimeController tc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tc = new TimeController(this);

        this.tc.updateCurrentBalanceField();

        Button stampButton = (Button)findViewById(R.id.stampButton);
        stampButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.tc.addTime();
            }
        });
    }

    @Override
    protected void onRestart() {
        MainActivity.this.tc.updateCurrentBalanceField();
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
