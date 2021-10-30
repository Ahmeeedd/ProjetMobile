package tn.esprit.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tn.esprit.project.DAO.UserDAO;
import tn.esprit.project.database.MyDataBase;
import tn.esprit.project.models.Role;
import tn.esprit.project.models.User;

public class MainActivity extends AppCompatActivity {
    Intent myintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnsignin=(Button) findViewById(R.id.btSignIn);
        Button btsignup=(Button) findViewById(R.id.btSignUp);
        EditText emailtxt=(EditText) findViewById(R.id.emailinput);
        EditText pwdtxt=(EditText) findViewById(R.id.passwordinput);

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
                     //       if (user.getRole().toString().equals(Role.Parent)) {
                                startActivity(new Intent(MainActivity.this, Welcome.class));
                      //      } else {
                                //Driver Home Page à developper
                       //     }


                        }

                    }).start();


                }


         }
        });

        btsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myintent=new Intent(MainActivity.this,Register.class);
                startActivity(myintent);
            }
        });

    }

}