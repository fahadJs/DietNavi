package com.example.dietapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NutritionAdapter extends RecyclerView.Adapter<NutritionAdapter.NutritionViewHolder> {

    private List<NutritionItem> mDataset;
    private SharedPreferences sharedPreferences;
    private Intent navigateIntent;
    private Context context;
    public String urlNew = constants.getUrl();

    // Provide a reference to the views for each data item
    public static class NutritionViewHolder extends RecyclerView.ViewHolder {
        public TextView textView, serving, calories, protein, carb, sugar, fiber, fat, sodium, cholesterol;
        public Button saveButton;

        public NutritionViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.name);
            saveButton = v.findViewById(R.id.saveButton);
            serving = v.findViewById(R.id.textViewServingValue);
            calories = v.findViewById(R.id.textViewCaloriesValue);
            protein = v.findViewById(R.id.textViewProteinValue);
            carb = v.findViewById(R.id.textViewCarbValue);
            sugar = v.findViewById(R.id.textViewSugarValue);
            fiber = v.findViewById(R.id.textViewFiberValue);
            fat = v.findViewById(R.id.textViewFatValue);
            sodium = v.findViewById(R.id.textViewSodiumValue);
            cholesterol = v.findViewById(R.id.textViewCholestrolValue);
        }
    }

    // Provide a suitable constructor
    public NutritionAdapter(Context context, List<NutritionItem> myDataset, SharedPreferences sharedPreferences, Intent navigateIntent) {
        mDataset = myDataset;
        this.sharedPreferences = sharedPreferences;
        this.navigateIntent = navigateIntent;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NutritionAdapter.NutritionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        NutritionViewHolder vh = new NutritionViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(NutritionViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final NutritionItem item = mDataset.get(position);

        holder.textView.setText(item.getName());

        holder.serving.setText(String.valueOf(item.getServingSize()));
        holder.calories.setText(String.valueOf(item.getCalories()));
        holder.protein.setText(String.valueOf(item.getProtein()));
        holder.carb.setText(String.valueOf(item.getCarbohydratesTotal()));
        holder.sugar.setText(String.valueOf(item.getSugar()));
        holder.fiber.setText(String.valueOf(item.getFiber()));
        holder.fat.setText(String.valueOf(item.getFatTotal()));
        holder.sodium.setText(String.valueOf(item.getSodium()));
        holder.cholesterol.setText(String.valueOf(item.getCholesterol()));


        holder.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click here
                // You can make a POST request to your API to save the item into the database
                saveItemToDatabase(item);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void saveItemToDatabase(NutritionItem item) {

        String userId = sharedPreferences.getString("userId", "");
        String currentDate = Utils.getCurrentDate();

        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlNew) // Replace this with your API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create service interface
        ApiService service = retrofit.create(ApiService.class); // Replace ApiService with your actual service interface

        // Create request body
//        RequestBody requestBody = new FormBody.Builder()
//                .add("name", item.getName())
//                .add("calories", String.valueOf(item.getCalories()))
//                // Add other parameters as needed
//                .build();

        // Create JSON request body
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", userId);
            jsonObject.put("date", currentDate);
            jsonObject.put("name", item.getName());
            jsonObject.put("serving", item.getServingSize());
            jsonObject.put("calories", item.getCalories());
            jsonObject.put("protein", item.getProtein());
            jsonObject.put("carbs", item.getCarbohydratesTotal());
            jsonObject.put("sugar", item.getSugar());
            jsonObject.put("fiber", item.getFiber());
            jsonObject.put("fat", item.getFatTotal());
            jsonObject.put("sodium", item.getSodium());
            jsonObject.put("cholesterol", item.getCholesterol());
            // Add other parameters as needed
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        // Make POST request
        Call<ResponseBody> call = service.saveItem(requestBody); // Replace saveItem with your actual API method
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    // Display a message or perform any other actions
                    navigateIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(navigateIntent);
                    ((SearchActivity) context).finish();
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Handle failure
            }
        });
    }

}


