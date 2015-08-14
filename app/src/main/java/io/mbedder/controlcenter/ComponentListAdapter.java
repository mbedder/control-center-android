package io.mbedder.controlcenter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * An adapter for a ListView that contains components with switches and their user-facing name.
 */
public class ComponentListAdapter extends ArrayAdapter<Component> {

    Context context;
    int layoutResourceId;
    ArrayList<Component> data = null;

    private class ComponentHolder {
        Switch s;
        TextView tv;
    }

    public ComponentListAdapter(Context context, int layoutResourceId, ArrayList<Component> data) {
        super(context, layoutResourceId, data);

        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ComponentHolder holder;

        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ComponentHolder();
            holder.s = (Switch)row.findViewById(R.id.componentRowSwitch);
            holder.tv = (TextView)row.findViewById(R.id.componentRowText);

            row.setTag(holder);
        } else {
            holder = (ComponentHolder)row.getTag();
        }

        Component component = data.get(position);
        holder.tv.setText(component.name);
        holder.s.setChecked(component.state);

        return row;
    }

}
