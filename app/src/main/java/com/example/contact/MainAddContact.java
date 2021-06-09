package com.example.contact;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

public class MainAddContact extends AppCompatActivity {
    ContactDAO contactDAO=new ContactDAO(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHome(view);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_contact:
                add();
                return true;
            case R.id.reset_form:
                reset();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void add(){
        EditText first_name=findViewById(R.id.input_first_name);
        EditText last_name=findViewById(R.id.input_last_name);
        EditText job=findViewById(R.id.input_job);
        EditText phone=findViewById(R.id.input_phone);
        EditText email=findViewById(R.id.input_email);
        Contact c=new Contact(-1,String.valueOf(first_name.getText()),String.valueOf(last_name.getText()),String.valueOf(job.getText()),String.valueOf(phone.getText()),String.valueOf(email.getText()));
        contactDAO.add(c);
    }
    public void reset(){
        EditText first_name=findViewById(R.id.input_first_name);
        EditText last_name=findViewById(R.id.input_last_name);
        EditText job=findViewById(R.id.input_job);
        EditText phone=findViewById(R.id.input_phone);
        EditText email=findViewById(R.id.input_email);
        first_name.setText("");
        last_name.setText("");
        job.setText("");
        phone.setText("");
        email.setText("");
    }
    public void toHome(View view) {
        Intent i= new Intent(this,MainActivity.class);
        startActivity(i);
    }

}
