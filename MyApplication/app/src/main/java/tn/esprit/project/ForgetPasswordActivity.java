package tn.esprit.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import tn.esprit.project.DAO.UserDAO;
import tn.esprit.project.database.MyDataBase;

public class ForgetPasswordActivity extends AppCompatActivity {


    EditText editextemail;
    Button btnconfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        editextemail = findViewById(R.id.idemail);
        btnconfirm = findViewById(R.id.button);



        btnconfirm.setOnClickListener( view -> {

            MyDataBase database = MyDataBase.getDataBase(getApplicationContext());
            UserDAO userdao = database.userdao();
            String email = editextemail.getText().toString();

            if (email.isEmpty())
            {
                Toast.makeText(getApplicationContext(),"Email is empty!",Toast.LENGTH_SHORT).show();
            }

            else if ( userdao.checkUser(email) != null)
            {
                Intent confirmpasswordintent = new Intent(this,ConfirmPasswordActivity.class);
                confirmpasswordintent.putExtra("email",email.trim());
                emptyInputEditText();
                startActivity(confirmpasswordintent);
            }
            else
            {
                Snackbar.make(view, "Wrong Email!", Snackbar.LENGTH_SHORT).show();
            }

        });


    }

    private void emptyInputEditText() {
        editextemail.setText("");
    }



}