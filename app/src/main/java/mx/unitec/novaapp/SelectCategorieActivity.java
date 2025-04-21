package mx.unitec.novaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SelectCategorieActivity extends AppCompatActivity {

    private LinearLayout moreCategoryLayout;
    private LinearLayout extraCategoriesLayout;
    private ImageView actionImageView, dramaImageView, familyImageView;
    private LinearLayout actionCategoryLayout, dramaCategoryLayout, familyCategoryLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_view);  // Asegúrate de tener este layout

        actionImageView = findViewById(R.id.actionImageView);
        dramaImageView = findViewById(R.id.dramaImageView);
        familyImageView = findViewById(R.id.familyImageView);

        moreCategoryLayout = findViewById(R.id.moreCategoryLayout);
        extraCategoriesLayout = findViewById(R.id.extraCategoriesLayout);

        // Acciones al hacer clic en las categorías
        actionCategoryLayout = findViewById(R.id.actionCategoryLayout);
        dramaCategoryLayout = findViewById(R.id.dramaCategoryLayout);
        familyCategoryLayout = findViewById(R.id.familyCategoryLayout);

        actionCategoryLayout.setOnClickListener(v -> openCategory("Action"));
        dramaCategoryLayout.setOnClickListener(v -> openCategory("Drama"));
        familyCategoryLayout.setOnClickListener(v -> openCategory("Family"));

        moreCategoryLayout.setOnClickListener(v -> {
            extraCategoriesLayout.setVisibility(View.VISIBLE);
            moreCategoryLayout.setVisibility(View.GONE);
        });
    }

    private void openCategory(String category) {
        Intent intent = new Intent(SelectCategorieActivity.this, CategoryCatalogActivity.class);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }
}
