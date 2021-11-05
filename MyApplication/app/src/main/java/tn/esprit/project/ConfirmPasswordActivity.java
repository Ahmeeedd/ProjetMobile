package tn.esprit.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import tn.esprit.project.DAO.UserDAO;
import tn.esprit.project.database.MyDataBase;

public class ConfirmPasswordActivity extends AppCompatActivity {

    EditText password, confirm;
    Button btnreset;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);
        password = findViewById(R.id.idpassword);
        confirm = findViewById(R.id.idconfirmpassword);
        btnreset = findViewById(R.id.btnreset);

        email = getIntent().getStringExtra("email");

        btnreset.setOnClickListener(v -> {
            updatePassword(v);
        });

    }

    private void updatePassword(View v) {
        String stringpassword = password.getText().toString();
        String stringconfirmpassword = confirm.getText().toString();
        MyDataBase database = MyDataBase.getDataBase(getApplicationContext());
        UserDAO userdao = database.userdao();

        if (stringpassword.isEmpty() && stringconfirmpassword.isEmpty()) {
            Toast.makeText(this, "Fields are empty!", Toast.LENGTH_SHORT).show();
        } else if (!stringpassword.contentEquals(stringconfirmpassword)) {
            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
        } else if (userdao.checkUser(email) == null) {
            Snackbar.make(v, "Email don't exist!", Snackbar.LENGTH_SHORT).show();
        } else {
            userdao.updatePassword(email, stringpassword);
            Toast.makeText(this, "Password reset successfully", Toast.LENGTH_SHORT).show();
            emptyInputEditText();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private void emptyInputEditText() {
        password.setText("");
        confirm.setText("");
    }
}