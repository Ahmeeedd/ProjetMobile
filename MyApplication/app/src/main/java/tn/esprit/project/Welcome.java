package tn.esprit.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        Intent myintent=new Intent(this,Add_Enfant_Activity.class);
        Intent myintent2=new Intent(this,ManageEnfantsActivity.class);
        Bundle bdl =getIntent().getExtras();
        FloatingActionButton faList=(FloatingActionButton) findViewById(R.id.floatingActionlist);
        //  tv.setText(bdl.getString("var1"));
        FloatingActionButton fab=(FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(myintent);
            }
        });
        faList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(myintent2);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Intent myintent=new Intent(Welcome.this,LoginActivity.class);
                startActivity(myintent);
                Toast.makeText(this, "logout successfully", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.profile:
                Intent myintent2=new Intent(Welcome.this,ProfileActivity.class);
                startActivity(myintent2);
                Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}