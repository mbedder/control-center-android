package io.mbedder.controlcenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Component> components = new ArrayList<>();
        components.add(new Component("Living Room Light Switch", false, null));
        components.add(new Component("Bathroom Light Switch", true, null));
        components.add(new Component("Old Radio Volume", false, null));
        components.add(new Component("Nuke Switch", false, null));

        ComponentListAdapter adapter = new ComponentListAdapter(this, R.layout.component_list_item, components);

        ListView listView = (ListView)findViewById(R.id.componentList);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
