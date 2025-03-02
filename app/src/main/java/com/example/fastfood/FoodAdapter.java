package com.example.fastfood;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<FoodItem> foodItems;
    private int selectedPosition = -1;

    public FoodAdapter(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        view.setBackgroundColor(Color.TRANSPARENT);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        FoodItem foodItem = foodItems.get(position);
        holder.foodImage.setImageResource(foodItem.getImageResource());
        holder.foodName.setText(foodItem.getName());
        holder.foodPrice.setText(foodItem.getPrice());
        holder.ratingBar.setRating(foodItem.getRating());


        if (position == selectedPosition) {
            holder.foodName.setTextColor(Color.RED);
            holder.foodPrice.setTextColor(Color.RED);
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        } else {
            holder.foodName.setTextColor(Color.GRAY);
            holder.foodPrice.setTextColor(Color.GRAY);
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.itemView.setOnClickListener(v -> {
            if (position == selectedPosition) {
                return;
            }

            int previousSelected = selectedPosition;
            if (previousSelected != -1) {
                notifyItemChanged(previousSelected);
            }

            selectedPosition = position;
            Animation scaleUp = AnimationUtils.loadAnimation(v.getContext(), R.anim.scale_up);
            v.startAnimation(scaleUp);
            if (selectedPosition != 0) {
                FoodItem selected = foodItems.remove(position);
                foodItems.add(0, selected);
                notifyItemMoved(position, 0);
                selectedPosition = 0;
            }

            notifyItemChanged(0);
        });
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }
    public void updateList(List<FoodItem> newList) {
        foodItems = newList;
        selectedPosition = -1;
        notifyDataSetChanged();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView foodName;
        TextView foodPrice;
        RatingBar ratingBar;

        FoodViewHolder(View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.foodImage);
            foodName = itemView.findViewById(R.id.foodName);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }
} 