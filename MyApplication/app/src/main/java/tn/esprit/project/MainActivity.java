package tn.esprit.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Intent myintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn=(Button) findViewById(R.id.btSignIn);
        Button bt=(Button) findViewById(R.id.btSignUp);
        EditText et=(EditText) findViewById(R.id.emailinput);
        EditText psw=(EditText) findViewById(R.id.passwordinput);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myintent=new Intent(MainActivity.this,Welcome.class);
                //myintent.putExtra("var1",et.getText().toString());
                startActivity(myintent);
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myintent=new Intent(MainActivity.this,Register.class);
                startActivity(myintent);
            }
        });
    }
}