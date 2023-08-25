package com.rakib.ju_bus_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.rakib.ju_bus_tracker.databinding.ActivityLoginBinding;
import com.rakib.ju_bus_tracker.manager.UserDataManager;
import com.rakib.ju_bus_tracker.model.User;

import java.util.concurrent.atomic.AtomicReference;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        AtomicReference<User.UserType> userType = new AtomicReference<>(User.UserType.STUDENT);

        binding.studentTextView.setOnClickListener(view1 -> {
            userType.set(User.UserType.STUDENT);
            changeTextView(userType.get());
        });

        binding.driverTextView.setOnClickListener(view1 -> {
            userType.set(User.UserType.DRIVER);
            changeTextView(userType.get());
        });

        binding.loginButton.setOnClickListener(view1 -> {
            String enteredId = binding.idFilledTextField.getEditText().getText().toString();
            String enteredPassword = binding.passwordFilledTextField.getEditText().getText().toString();

            if (isValidCredentials(enteredId, enteredPassword, userType.get())) {
                Intent intent = new Intent(this, BusListActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void changeTextView(User.UserType userType) {
        if (userType.equals(User.UserType.STUDENT)) {
            binding.studentTextView.setBackgroundResource(R.drawable.filled_rounded_corner_view);
            binding.studentTextView.setTextColor(ContextCompat.getColor(this, R.color.white));
            binding.driverTextView.setBackgroundResource(R.drawable.stroked_rounded_corner_view);
            binding.driverTextView.setTextColor(ContextCompat.getColor(this, R.color.black));
        } else {
            binding.driverTextView.setBackgroundResource(R.drawable.filled_rounded_corner_view);
            binding.driverTextView.setTextColor(ContextCompat.getColor(this, R.color.white));
            binding.studentTextView.setBackgroundResource(R.drawable.stroked_rounded_corner_view);
            binding.studentTextView.setTextColor(ContextCompat.getColor(this, R.color.black));
        }
    }

    private boolean isValidCredentials(String id, String password, User.UserType userType) {
        User user = UserDataManager.getUserById(id);
        return user != null && user.getPassword().equals(password) && user.getUserType().equals(userType);
    }
}
