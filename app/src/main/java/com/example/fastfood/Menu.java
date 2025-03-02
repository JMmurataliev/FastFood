package com.example.fastfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;

public class Menu extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;
    private RecyclerView foodItemsRecyclerView;
    private CategoryAdapter categoryAdapter;
    private FoodAdapter foodAdapter;
    private EditText searchEditText;
    private List<Category> categoryList;
    private List<FoodItem> foodItemList;
    private ImageButton menuButton;
    private ImageView avatarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initViews();
        setupRecyclerViews();
        loadData();
        setupSearch();
        setupClickListeners();
    }

    private void initViews() {
        categoryRecyclerView = findViewById(R.id.recyclerview1);
        foodItemsRecyclerView = findViewById(R.id.foodItemsRecyclerView);
        searchEditText = findViewById(R.id.search);
        menuButton = findViewById(R.id.menu_button);
        avatarView = findViewById(R.id.avatar);
    }

    private void setupClickListeners() {
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu.this, "Menu clicked", Toast.LENGTH_SHORT).show();
            }
        });

        avatarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu.this, "Profile clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecyclerViews() {
        LinearLayoutManager categoryLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(categoryLayoutManager);
        categoryRecyclerView.setHasFixedSize(true);
        categoryRecyclerView.setNestedScrollingEnabled(true);
        
        int categorySpacing = getResources().getDimensionPixelSize(R.dimen.item_spacing);
        categoryRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull android.graphics.Rect outRect, @NonNull View view, 
                                     @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.right = categorySpacing;
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.left = categorySpacing;
                }
            }
        });
        
        categoryList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(categoryList, position -> {
            filterFoodByCategory(categoryList.get(position).getName());
        });
        categoryRecyclerView.setAdapter(categoryAdapter);

        LinearLayoutManager foodLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        foodItemsRecyclerView.setLayoutManager(foodLayoutManager);
        foodItemsRecyclerView.setHasFixedSize(true);
        foodItemsRecyclerView.setNestedScrollingEnabled(true);
        
        int spacing = getResources().getDimensionPixelSize(R.dimen.item_spacing);
        foodItemsRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull android.graphics.Rect outRect, @NonNull View view, 
                                     @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.right = spacing;
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.left = spacing;
                }
            }
        });
        
        foodItemList = new ArrayList<>();
        foodAdapter = new FoodAdapter(foodItemList, (item, position) -> {
            foodItemsRecyclerView.smoothScrollToPosition(0);
        });
        foodItemsRecyclerView.setAdapter(foodAdapter);
    }

    private void loadData() {
        categoryList.add(new Category("Chicken", R.drawable.chiken, "600сом"));
        categoryList.add(new Category("Pizza", R.drawable.pizza, "500сом"));
        categoryList.add(new Category("Shaurma", R.drawable.shaurma, "200сом"));
        categoryList.add(new Category("Burger", R.drawable.burger1, "300сом"));
        categoryList.add(new Category("Sushi", R.drawable.sushi_california, "800сом"));
        categoryList.add(new Category("Dessert", R.drawable.dessert_icecream, "150сом"));
        categoryAdapter.notifyDataSetChanged();

        foodItemList.add(new FoodItem("Burger", "200сом", R.drawable.burger1, 4.5f));
        foodItemList.add(new FoodItem("Combo Star", "640сом", R.drawable.ss, 4.5f));
        foodItemList.add(new FoodItem("Pizza XL", "800сом", R.drawable.pizza, 4.8f));
        foodItemList.add(new FoodItem("Ice Cream", "150сом", R.drawable.dessert_icecream, 4.3f));
        foodAdapter.notifyDataSetChanged();
    }

    private void setupSearch() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString().trim();
                if (searchText.isEmpty()) {
                    foodAdapter.updateList(foodItemList);
                } else {
                    foodAdapter.selectItemByName(searchText);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterFood(String query) {
        List<FoodItem> filteredList = new ArrayList<>();
        if (query.isEmpty()) {
            foodAdapter.updateList(foodItemList);
            return;
        }
        for (FoodItem item : foodItemList) {
            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        foodAdapter.updateList(filteredList);
        if (!filteredList.isEmpty()) {
            foodItemsRecyclerView.scrollToPosition(0);
        }
    }

    private void filterFoodByCategory(String category) {
        List<FoodItem> filteredList = new ArrayList<>();
        for (FoodItem item : foodItemList) {
            if (item.getName().toLowerCase().contains(category.toLowerCase())) {
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()) {
            foodAdapter.updateList(foodItemList);
        } else {
            foodAdapter.updateList(filteredList);
        }
        foodItemsRecyclerView.scrollToPosition(0);
    }
} 