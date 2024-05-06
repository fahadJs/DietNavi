package com.example.dietapplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword, editTextName;
    private Button buttonRegister;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextName = findViewById(R.id.editTextName);
        buttonRegister = findViewById(R.id.buttonRegister);

        apiService = RetrofitClient.getInstance().getApiService();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextUsername.getText().toString();
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                // Make API request to register user
                // Example: registerUser(username, password);

                // Call the login method from UserDAO
//                UserDAO userDAO = new UserDAO();
//                boolean registerSuccessful = userDAO.registerUser(name, username, password);
//
//                if (registerSuccessful) {
//                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
//                }

                User user = new User(name, username, password);

                Call<Void> call = apiService.registerUser(user);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            // Navigate to login activity or any other screen
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}