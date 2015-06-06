package uk.co.ivaylokhr.codeforbulgaria.EmergencyHealthCases;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import uk.co.ivaylokhr.codeforbulgaria.R;
import uk.co.ivaylokhr.codeforbulgaria.SendActivity;

public class HealthCatastrophe extends ActionBarActivity {

    ImageButton back;
    ImageButton forward;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_catastrophe);
        addButtonsAndListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_health_catastrophe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void addButtonsAndListeners() {
        back = (ImageButton) findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();

            }

        });

        forward = (ImageButton) findViewById(R.id.forwardButton);

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthCatastrophe.this, SendActivity.class);
                startActivity(intent);
            }
        });
    }

}
