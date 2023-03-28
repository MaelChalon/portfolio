package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import model.BDD;
import model.Contact;

public class ListContact extends AppCompatActivity {
    final static int RETOUR = 555;

    private BDD bdd = new BDD(this);

    Contact contact;
    ArrayList<Contact> listContact = new ArrayList<Contact>();
    ConstraintLayout layoutDetailActivity;
    FloatingActionButton valider;
    contactAdapter adapter = new contactAdapter(listContact);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        layoutDetailActivity = findViewById(R.id.layoutDetailActivity);

        chargerListe();

        ListView lv = findViewById(R.id.ListView);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "tel: " + listContact.get(i).getPhone_number() + "\nadresse: " + listContact.get(i).getAdresse(), Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact remove = listContact.get(i);
                listContact.remove(i);
                bdd.deleteData(remove.getId());
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        valider = findViewById(R.id.BtnNewContact);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("retour", "retour en validant");
                setResult(RESULT_OK, i);
                startActivityForResult(i, RETOUR);
            }
        });

    }

    private void chargerListe(){
        Cursor curs = bdd.getAllData();

        while(curs.moveToNext()){
            Contact c = new Contact();
            int colone = curs.getColumnIndex(bdd.COL_ID);
            c.setId(curs.getInt(colone));

            colone = curs.getColumnIndex(bdd.COL_NOM);
            c.setName(curs.getString(colone));

            colone = curs.getColumnIndex(bdd.COL_PRENOM);
            c.setSurname(curs.getString(colone));

            colone = curs.getColumnIndex(bdd.COL_AGE);
            c.setAge(curs.getInt(colone));

            colone = curs.getColumnIndex(bdd.COL_ADRESSE);
            c.setAdresse(curs.getString(colone));

            colone = curs.getColumnIndex(bdd.COL_GENDER);
            c.setGenre(curs.getString(colone));

            colone = curs.getColumnIndex(bdd.COL_NUMBER);
            c.setPhone_number(curs.getString(colone));

            listContact.add(c);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RETOUR){
            contact = (Contact) data.getSerializableExtra("contact");
            bdd.insertData(contact.getName(), contact.getSurname(), contact.getAge(), contact.getPhone_number(), contact.getAdresse(), contact.getGenre());
            listContact.add(contact);
            adapter.notifyDataSetChanged();
        }

    }
}