package com.example.fastfood;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categories;
    private int selectedPosition = -1;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public CategoryAdapter(List<Category> categories, OnItemClickListener listener) {
        this.categories = categories;
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);

        LinearLayout container;
        ImageView image;
        TextView name;
        TextView price;
        
        switch (position) {
            case 0:
                container = holder.itemView.findViewById(R.id.chicken_container);
                image = holder.itemView.findViewById(R.id.chicken_image);
                name = holder.itemView.findViewById(R.id.chicken_name);
                price = holder.itemView.findViewById(R.id.chicken_price);
                break;
            case 1:
                container = holder.itemView.findViewById(R.id.pizza_container);
                image = holder.itemView.findViewById(R.id.pizza_image);
                name = holder.itemView.findViewById(R.id.pizza_name);
                price = holder.itemView.findViewById(R.id.pizza_price);
                break;
            case 2:
                container = holder.itemView.findViewById(R.id.sushi_container);
                image = holder.itemView.findViewById(R.id.sushi_image);
                name = holder.itemView.findViewById(R.id.sushi_name);
                price = holder.itemView.findViewById(R.id.sushi_price);
                break;
            default:
                container = holder.itemView.findViewById(R.id.dessert_container);
                image = holder.itemView.findViewById(R.id.dessert_image);
                name = holder.itemView.findViewById(R.id.dessert_name);
                price = holder.itemView.findViewById(R.id.dessert_price);
                break;
        }
        image.setImageResource(category.getImageResource());
        name.setText(category.getName());
        price.setText(category.getPrice());


        if (selectedPosition == position) {
            image.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            name.setTextColor(Color.RED);
            price.setTextColor(Color.RED);
        } else {
            image.clearColorFilter();
            name.setTextColor(Color.GRAY);
            price.setTextColor(Color.GRAY);
        }

        container.setOnClickListener(v -> {
            if (position == selectedPosition) {
                return;
            }

            int previousSelected = selectedPosition;
            selectedPosition = holder.getAdapterPosition();

            if (selectedPosition != 0) {
                Category selectedCategory = categories.get(selectedPosition);
                categories.remove(selectedPosition);
                categories.add(0, selectedCategory);
                selectedPosition = 0;
            }

            notifyItemChanged(previousSelected);
            notifyDataSetChanged();

            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(selectedPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
    public void updateList(List<Category> newList) {
        categories = newList;
        selectedPosition = -1;
        notifyDataSetChanged();
    }
    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        CategoryViewHolder(View itemView) {
            super(itemView);
        }
    }
} 