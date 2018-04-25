package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject sandwich = new JSONObject(json);
            JSONObject name = sandwich.getJSONObject("name");

            String mainName = name.getString("mainName");
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");

            String placeOfOrigin = sandwich.getString("placeOfOrigin");
            String description = sandwich.getString("description");
            String image = sandwich.getString("image");
            JSONArray ingredients = sandwich.getJSONArray("ingredients");

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
