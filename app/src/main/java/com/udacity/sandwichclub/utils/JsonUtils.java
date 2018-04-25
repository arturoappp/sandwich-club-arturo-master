package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String KEY_NAME = "name";
    public static final String KEY_MAIN_NAME = "mainName";
    public static final String KEY_ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject sandwich = new JSONObject(json);
            JSONObject name = sandwich.getJSONObject(KEY_NAME);

            String mainName = name.getString(KEY_MAIN_NAME);
            JSONArray alsoKnownAs = name.getJSONArray(KEY_ALSO_KNOWN_AS);

            String placeOfOrigin = sandwich.getString(KEY_PLACE_OF_ORIGIN);
            String description = sandwich.getString(KEY_DESCRIPTION);
            String image = sandwich.getString(KEY_IMAGE);
            JSONArray ingredients = sandwich.getJSONArray(KEY_INGREDIENTS);

            Sandwich sandwichObj = new Sandwich(mainName, jSonArrayToList(alsoKnownAs), placeOfOrigin, description, image, jSonArrayToList(ingredients));

            return sandwichObj;

        } catch (JSONException e) {
            return null;
        }
    }

    private static List<String> jSonArrayToList(JSONArray jsonArray) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            for (int i = 0, l = jsonArray.length(); i < l; i++) {
                list.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
        }
        return list;
    }
}
