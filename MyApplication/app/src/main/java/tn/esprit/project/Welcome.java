package tn.esprit.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import tn.esprit.project.models.User;

public class Welcome extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);


        Intent myintent=new Intent(this,addInfo.class);
     //   TextView tv=(TextView) findViewById(R.id.name);
       // Bundle bdl =getIntent().getExtras();
      //  tv.setText(bdl.getString("var1"));
       // FloatingActionButton fab=(FloatingActionButton) findViewById(R.id.floatingActionButton);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(myintent);
            }
        });*/
    }
}
