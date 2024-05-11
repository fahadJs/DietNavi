package com.example.dietapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<SuggestedApiResponse.Recipe> recipes;
    private Context context;

    public RecipeAdapter(Context context) {
        this.context = context;
    }

    public void setRecipes(List<SuggestedApiResponse.Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggest_layout, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        SuggestedApiResponse.Recipe recipe = recipes.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipes == null ? 0 : recipes.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        private ImageView recipeImage;
        private TextView recipeLabel;
        private TextView calories;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.recipe_image);
            recipeLabel = itemView.findViewById(R.id.recipe_label);
            calories = itemView.findViewById(R.id.calories);
        }

        public void bind(SuggestedApiResponse.Recipe recipe) {
            // Load image using Picasso or any other image loading library
            Picasso.get().load(recipe.getImage()).into(recipeImage);
            recipeLabel.setText(recipe.getLabel());
            calories.setText(String.valueOf(recipe.getCalories()));
        }
    }
}

