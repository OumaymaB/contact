package com.example.contact;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
    ContactDAO contactDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactDAO =new ContactDAO(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        ListView contactes=findViewById(R.id.list_contact);
        contactes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView id_view=view.findViewById(R.id.id);
                final Long id_delet=Long.parseLong((String) id_view.getText());
                final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Delete");
                alert.setMessage("Are you sure you want to delete?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        contactDAO.delet(id_delet);
                        initView();
                        dialog.dismiss();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
                return false;
            }

        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContact(view);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView search = (SearchView) menu.findItem(R.id.search_contact).getActionView();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                initView(newText);
                return true;
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.search_contact) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void addContact(View view) {
        Intent i= new Intent(this,MainAddContact.class);
        startActivity(i);
    }
    public void updateContact(View view) {
        Intent i= new Intent(this,MainEditContact.class);
        Bundle data=new Bundle();
        TextView id_view=view.findViewById(R.id.id);
        Long id_update=Long.parseLong((String) id_view.getText());
        data.putLong("id", id_update);
        i.putExtra("data",data);
        startActivity(i);
    }
    public void initView(){
        ListView list_view = (ListView) findViewById(R.id.list_contact);
        ContactAdapter contactAdapter = new ContactAdapter(this, contactDAO.getAll());
        list_view.setAdapter(contactAdapter);
    }
    public void initView(String key){
        ListView list_view = (ListView) findViewById(R.id.list_contact);
        ContactAdapter contactAdapter = new ContactAdapter(this, contactDAO.getByKey(key));
        list_view.setAdapter(contactAdapter);
    }
    public void call(View view){
        View parent= (View) view.getParent();
        TextView phone=parent.findViewById(R.id.phone);
        MakePhoneCall(phone.getText().toString());
    }
    public void MakePhoneCall(String phone){
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null)));
    }

}
