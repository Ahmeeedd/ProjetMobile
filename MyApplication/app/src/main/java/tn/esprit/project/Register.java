package tn.esprit.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.project.DAO.UserDAO;
import tn.esprit.project.database.MyDataBase;
import tn.esprit.project.models.Role;
import tn.esprit.project.models.User;

public class Register extends AppCompatActivity {
    private ProgressDialog progressDialog;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        Intent myintent =new Intent(this,MainActivity.class);
        Button btnR=(Button) findViewById(R.id.btRegister);
        Button cancel=(Button) findViewById(R.id.btCancel);
        EditText mail=(EditText) findViewById(R.id.emailinput);
        EditText psw=(EditText) findViewById(R.id.passwordinput);
        EditText name=(EditText) findViewById(R.id.nameinput);
        EditText lastname=(EditText) findViewById(R.id.lastnameinput);
        EditText confpwd=(EditText) findViewById(R.id.confpasswordinput);


        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtfirst = name.getText().toString();
                String txtlast = lastname.getText().toString();
                String txtemail = mail.getText().toString();
                String  txtpassword = psw.getText().toString();
                String  txtconfirmpassword = confpwd.getText().toString();
                MyDataBase database = MyDataBase.getDataBase(getApplicationContext());
                User checkUserifexists = database.userdao().checkIfUserExists(txtemail);

                if (txtfirst.equals("") || txtemail.equals("") ||
                        txtpassword.equals("") || txtconfirmpassword.equals("") ||
                        txtlast.equals("")) {
                    Toast.makeText(Register.this, "Some Fields are empty !", Toast.LENGTH_SHORT).show();

                }
                else if (!txtpassword.equals(txtconfirmpassword)) {
                    Toast.makeText(Register.this, "Passwords don't match !", Toast.LENGTH_SHORT).show();
                }
                else if (checkUserifexists != null)
                {
                    Toast.makeText(Register.this, "User Already Exists !", Toast.LENGTH_SHORT).show();
                }else if (!txtemail.matches(emailPattern)){
                    Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                }


                else {

                    User  u = new User();
                    MyDataBase db = MyDataBase.getDataBase(getApplicationContext());
                    UserDAO userdao = db.userdao();
                    u.setFirstname(txtfirst);
                    u.setEmail(txtemail);
                    u.setPassword(txtpassword);
                    u.setLastname(txtlast);
                  /*  if (rdparent.isChecked())
                    {
                        u.setRole(Role.Parent);
                    }
                    else if (rdadmin.isChecked()) {
                        u.setRole(Role.Admin);
                    }*/
                    new Thread(() -> {
                        userdao.registerUser(u);

                        runOnUiThread(() -> {

                            Toast.makeText(getApplicationContext(),"User successfully registered !",Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(Register.this, LoginActivity.class));
                        });
                    }).start();




                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent=new Intent(Register.this,LoginActivity.class);
                startActivity(myintent);
            }
        });
    }
}
