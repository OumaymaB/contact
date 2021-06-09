package com.example.contact;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

public class MainEditContact extends AppCompatActivity {
    ContactDAO contactDAO=new ContactDAO(this);
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_edit_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHome(view);
            }
        });

        if (getIntent().getExtras() != null) {
            Bundle data=getIntent().getBundleExtra("data");
            this.id = data.getLong("id");
            initData(id);
        }
    }

    public void initData(long id){
        Contact c=contactDAO.getByID(id);
        EditText last_name=findViewById(R.id.input_last_name_edit);
        EditText first_name=findViewById(R.id.input_first_name_edit);
        EditText job=findViewById(R.id.input_job_edit);
        EditText phone=findViewById(R.id.input_phone_edit);
        EditText email=findViewById(R.id.input_email_edit);

        last_name.setText(c.getLast_Name());
        first_name.setText(c.getFirst_Name());
        email.setText(c.getEmail());
        phone.setText(c.getPhone());
        job.setText(c.getJob());
    }
    public void update(){
        Contact c=contactDAO.getByID(this.id);
        EditText last_name=findViewById(R.id.input_last_name_edit);
        EditText first_name=findViewById(R.id.input_first_name_edit);
        EditText job=findViewById(R.id.input_job_edit);
        EditText phone=findViewById(R.id.input_phone_edit);
        EditText email=findViewById(R.id.input_email_edit);
        c.setEmail(String.valueOf(email.getText()));
        c.setFirst_Name(String.valueOf(first_name.getText()));
        c.setJob(String.valueOf(job.getText()));
        c.setLast_Name(String.valueOf(last_name.getText()));
        c.setPhone(String.valueOf(phone.getText()));
        contactDAO.update(c);
    }

    public void toHome(View view) {
        Intent i= new Intent(this,MainActivity.class);
        startActivity(i);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_contact:
               update();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
