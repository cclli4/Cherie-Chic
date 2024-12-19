package com.example.chriechic.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chriechic.R;
import com.example.chriechic.history.HistoryOrderActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ModelCategories> modelCategoriesList = new ArrayList<>();
    List<ModelTrending> modelTrendingList = new ArrayList<>();
    CategoriesAdapter categoriesAdapter;
    TrendingAdapter trendingAdapter;
    ModelCategories modelCategories;
    ModelTrending modelTrending;
    RecyclerView rvCategories, rvTrending;
    CardView cvHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setStatusbar();
        setInitLayout();
        setCategories();
        setTrending();
    }

    private void setInitLayout() {
        cvHistory = findViewById(R.id.cvHistory);
        cvHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryOrderActivity.class);
                startActivity(intent);
            }
        });

        rvCategories = findViewById(R.id.rvCategories);
        rvCategories.setLayoutManager(new GridLayoutManager(this, 3));
        rvCategories.setHasFixedSize(true);

        rvTrending = findViewById(R.id.rvTrending);
        rvTrending.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvTrending.setHasFixedSize(true);
    }

    private void setCategories() {
        modelCategories = new ModelCategories(R.drawable.casual_wear, "Casual Wear");
        modelCategoriesList.add(modelCategories);
        modelCategories = new ModelCategories(R.drawable.formal_wear, "Formal Wear");
        modelCategoriesList.add(modelCategories);
        modelCategories = new ModelCategories(R.drawable.outer_wear, "Outer Wear");
        modelCategoriesList.add(modelCategories);
        modelCategories = new ModelCategories(R.drawable.sport_wear, "Sport Wear");
        modelCategoriesList.add(modelCategories);
        modelCategories = new ModelCategories(R.drawable.foot_wear, "Foot Wear");
        modelCategoriesList.add(modelCategories);
        modelCategories = new ModelCategories(R.drawable.accessories, "Accessories");
        modelCategoriesList.add(modelCategories);

        categoriesAdapter = new CategoriesAdapter(this, modelCategoriesList);
        rvCategories.setAdapter(categoriesAdapter);
    }

    private void setTrending() {
        modelTrending = new ModelTrending(R.drawable.artikel_1,"Drip Check: Fresh Fits for Every Vibe", "2.200 dibaca");
        modelTrendingList.add(modelTrending);
        modelTrending = new ModelTrending(R.drawable.artikel_2,"Fashion on Fleek: The Collection You Need RN", "1.220 dibaca");
        modelTrendingList.add(modelTrending);
        modelTrending = new ModelTrending(R.drawable.artikel_3,"Your Style Glow-Up Starts Here", "345 dibaca");
        modelTrendingList.add(modelTrending);
        modelTrending = new ModelTrending(R.drawable.artikel_4,"Slay the Day with These Must-Have Fits", "590 dibaca");
        modelTrendingList.add(modelTrending);

        trendingAdapter = new TrendingAdapter(this, modelTrendingList);
        rvTrending.setAdapter(trendingAdapter);
    }

    public void setStatusbar() {
        if (Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static void setWindowFlag(@NonNull Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (on) {
            layoutParams.flags |= bits;
        } else {
            layoutParams.flags &= ~bits;
        }
        window.setAttributes(layoutParams);
    }

}
