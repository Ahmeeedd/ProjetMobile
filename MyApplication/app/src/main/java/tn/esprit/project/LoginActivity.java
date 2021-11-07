package tn.esprit.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import tn.esprit.project.DAO.UserDAO;
import tn.esprit.project.database.MyDataBase;
import tn.esprit.project.models.Role;
import tn.esprit.project.models.User;

public class LoginActivity extends AppCompatActivity {


    Intent myintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnsignin = (Button) findViewById(R.id.btSignIn);
        Button btsignup = (Button) findViewById(R.id.btSignUp);
        EditText emailtxt = (EditText) findViewById(R.id.emailinput);
        EditText pwdtxt = (EditText) findViewById(R.id.passwordinput);
        TextView forgot = (TextView) findViewById(R.id.forgot);
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //myintent.putExtra("var1",et.getText().toString());
                String email = emailtxt.getText().toString();
                String passsword = pwdtxt.getText().toString();

                if (email.isEmpty() || passsword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fields are empty !", Toast.LENGTH_SHORT).show();
                } else {
                    MyDataBase db = MyDataBase.getDataBase(getApplicationContext());

                    UserDAO userdao = db.userdao();
                    new Thread(() -> {
                        User user = userdao.loginUser(email, passsword);
                        if (user == null) {
                            runOnUiThread(() ->
                                    {
                                        Toast.makeText(getApplicationContext(), "Invalid Credentials!", Toast.LENGTH_SHORT).show();

                                    }
                            );
                        } else {
                            if (user.getRole().equals(Role.Admin)) {

                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                 } else {
                                Intent homeparent = new Intent(getApplicationContext(), Welcome.class);
                                User usertoModified = db.userdao().checkUser(email);

                                ProfileActivity.userprofile = usertoModified;
                                Add_Enfant_Activity.userconnected = usertoModified;
                                ManageEnfantsActivity.userconnected = usertoModified;
                                startActivity(homeparent);
                              }


                        }

                    }).start();


                }


            }
        });

        btsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myintent = new Intent(LoginActivity.this, Register.class);
                startActivity(myintent);
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myintent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(myintent);
            }
        });
    }



}