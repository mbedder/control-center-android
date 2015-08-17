package io.mbedder.controlcenter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ComponentListAdapter adapter;
        try {
            adapter = ComponentDAO.getListAdapter(this);
        } catch(MalformedPreferencesException e) {
            // TODO(velovix): provide a graceful response to a malformed preferences exception.
            throw new RuntimeException(e);
        }

        ListView listView = (ListView)findViewById(R.id.componentList);
        listView.setAdapter(adapter);

        final FloatingActionButton createComponentFAB = (FloatingActionButton) findViewById(R.id.createComponentFAB);
        createComponentFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment createComponentDialog = new CreateComponentDialog();
                createComponentDialog.show(getSupportFragmentManager(), "createComponent");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
