package com.udacity.sandwichclub;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.databinding.ActivityDetailBinding;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    ActivityDetailBinding activityDetailBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_detail);

        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        setTitle(sandwich.getMainName());
        populateUI(sandwich);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        activityDetailBinding.nameTv.setText(sandwich.getMainName());
        activityDetailBinding.alsoKnownTv.setText(listToString(sandwich.getAlsoKnownAs()));
        activityDetailBinding.descriptionTv.setText(sandwich.getDescription());
        activityDetailBinding.ingredientsTv.setText(listToString(sandwich.getIngredients()));
        activityDetailBinding.placeOfOriginTv.setText(sandwich.getPlaceOfOrigin());
        ImageView imageSandwich = activityDetailBinding.imageIv;
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(imageSandwich);
    }

    private String listToString(List<String> list) {
        if (list.isEmpty() || list.size() == 0) {
            return "";
        }
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            s += list.get(i) + ", ";
        }
        return s.substring(0, s.length() - 2);
    }
}
