package com.example.tp1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

import model.Contact;

public class contactAdapter extends BaseAdapter {

    ArrayList<Contact> listContact;

    public contactAdapter(ArrayList<Contact> list){
        this.listContact = list;
    }

    @Override
    public int getCount() {
        return listContact.size();
    }

    @Override
    public Object getItem(int i) {
        return listContact.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ConstraintLayout layout;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        if(view == null){
            layout = (ConstraintLayout) inflater.inflate(R.layout.adapter_list_contact, viewGroup, false);
        }else{
            layout = (ConstraintLayout) view;
        }

        Contact c = listContact.get(i);
        TextView ContactName = layout.findViewById(R.id.ContactName);
        ContactName.setText(c.getName());

        TextView ContactSurname = layout.findViewById(R.id.ContactSurname);
        ContactSurname.setText(c.getSurname());

        TextView ContactAge = layout.findViewById(R.id.ContactAge);
        ContactAge.setText(Integer.toString(c.getAge()));

        TextView ContactGender = layout.findViewById(R.id.ContactGender);
        ContactGender.setText(c.getGenre());

        return layout;
    }
}
