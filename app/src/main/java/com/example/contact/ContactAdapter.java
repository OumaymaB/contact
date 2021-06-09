package com.example.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    Context context;
    List<Contact> contacts;

    public ContactAdapter(Context context, List<Contact> contacts){
        this.context=context;
        this.contacts = contacts;
    }
    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Contact getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(view == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.contact, viewGroup, false);
        }

        TextView last_name=view.findViewById(R.id.last_name);
        TextView first_name=view.findViewById(R.id.first_name);
        TextView job=view.findViewById(R.id.job);
        TextView email=view.findViewById(R.id.email);
        TextView phone=view.findViewById(R.id.phone);
        TextView id=view.findViewById(R.id.id);
        Contact item=contacts.get(position);
        first_name.setText(item.getFirst_Name());
        last_name.setText(item.getLast_Name());
        job.setText(item.getJob());
        email.setText(item.getEmail());
        phone.setText(item.getPhone());
        id.setText(Long.toString(item.getId()));

        return view;
    }
}
