package io.mbedder.controlcenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ComponentDAO {

    private static final String JSON_COMPONENT_LIST = "components";
    private static final String JSON_COMPONENT_LIST_NAME = "name";
    private static final String JSON_COMPONENT_LIST_STATE = "state";
    private static final String JSON_COMPONENT_LIST_OFF_POS = "offPos";
    private static final String JSON_COMPONENT_LIST_ON_POS = "onPos";

    static private ComponentListAdapter adapter;

    static ArrayList<Component> parseJSON(String data) throws JSONException {
        JSONObject mainObj = new JSONObject(data);

        JSONArray componentObjs = mainObj.getJSONArray(JSON_COMPONENT_LIST);

        ArrayList<Component> components = new ArrayList<>(componentObjs.length());
        for(int i=0; i<componentObjs.length(); i++) {
            JSONObject componentObj = componentObjs.getJSONObject(i);

            components.add(new Component(componentObj.getString(JSON_COMPONENT_LIST_NAME),
                    componentObj.getBoolean(JSON_COMPONENT_LIST_STATE),
                    componentObj.getInt(JSON_COMPONENT_LIST_OFF_POS),
                    componentObj.getInt(JSON_COMPONENT_LIST_ON_POS)));
        }

        return components;
    }

    static ArrayList<Component> getComponents(Activity activity) throws MalformedPreferencesException {
        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);

        // Check if the component JSON already exists
        if(preferences.contains(activity.getString(R.string.preferencesComponents))) {
            String componentJSON;
            try {
                componentJSON = preferences.getString(activity.getString(R.string.preferencesComponents), null);
            } catch(ClassCastException e) {
                throw new MalformedPreferencesException(activity.getString(R.string.preferencesComponents) + " is of the wrong type");
            }

            try {
                return parseJSON(componentJSON);
            } catch(JSONException e) {
                throw new MalformedPreferencesException(activity.getString(R.string.preferencesComponents) + " JSON is malformed: " + e);
            }
        } else {
            // Return an empty set if no components were found
            return new ArrayList<>();
        }
    }

    static void addComponent(Component component, Activity activity) throws MalformedPreferencesException, JSONException {
        JSONObject mainObj = new JSONObject();

        ArrayList<Component> components = getComponents(activity);
        components.add(component);
        JSONArray componentsJSON = new JSONArray();
        for(Component val : components) {
            JSONObject valJSON = new JSONObject();

            valJSON.put(JSON_COMPONENT_LIST_NAME, val.name);
            valJSON.put(JSON_COMPONENT_LIST_STATE, val.state);
            valJSON.put(JSON_COMPONENT_LIST_OFF_POS, val.positions.get(false));
            valJSON.put(JSON_COMPONENT_LIST_ON_POS, val.positions.get(true));

            componentsJSON.put(valJSON);
        }

        mainObj.put(JSON_COMPONENT_LIST, componentsJSON);

        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(activity.getString(R.string.preferencesComponents), mainObj.toString());
        editor.apply();

        ComponentListAdapter adapter = getListAdapter(activity);
        adapter.add(component);
        adapter.notifyDataSetChanged();
    }

    static ComponentListAdapter getListAdapter(Activity activity) throws MalformedPreferencesException {
        if(adapter == null) {
            adapter = new ComponentListAdapter(activity, R.layout.component_list_item, getComponents(activity));
        }

        return adapter;
    }

}
