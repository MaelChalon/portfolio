package com.example.tp1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import model.Contact;

public class MainActivity extends AppCompatActivity {

    private Contact contact = new Contact();
    final static int RETOUR = 666;

    private Button valider;
    private EditText InputNom;
    private EditText inputPrenom;
    private EditText inputNumTel;
    private EditText inputAge;
    private EditText inputAdresse;
    private RadioGroup radioGroupGender;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valider = findViewById(R.id.buttonValideMain);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(InputNom.getText().toString().isEmpty() || inputPrenom.getText().toString().isEmpty() || inputNumTel.getText().toString().isEmpty() || (RadioButton)findViewById(radioGroupGender.getCheckedRadioButtonId()) == null){
                    Toast.makeText(getApplicationContext(), "champs manquant", Toast.LENGTH_SHORT).show();
                }else{
                    contact.setName("name");
                    contact.setPhone_number("phone_number");
                    contact.setSurname("surname");
                    openDetail();
                }

            }
        });

        InputNom = findViewById(R.id.InputNom);

        inputPrenom = findViewById(R.id.inputPrenom);

        inputNumTel = findViewById(R.id.inputNumTel);

        radioGroupGender = findViewById(R.id.radioGroupGender);

        inputAge = findViewById(R.id.inputAge);

        inputAdresse = findViewById(R.id.inputAdresse);

        Log.e("MainActivity", "create");

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.e("MainActivity", "Start");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e("MainActivity", "Resume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e("MainActivity", "Destroy");
    }

    public void openDetail(){
        Intent I = new Intent(getApplicationContext(), ListContact.class);

        if(!InputNom.getText().toString().isEmpty()){
            contact.setName(InputNom.getText().toString());
        }else{
            contact.setName("");
        }

        if(!inputNumTel.getText().toString().isEmpty()){
            contact.setPhone_number(inputNumTel.getText().toString());
        }else{
            contact.setPhone_number("");
        }

        if(!inputPrenom.getText().toString().isEmpty()){
            contact.setSurname(inputPrenom.getText().toString());
        }else{
            contact.setSurname("");
        }

        if(!inputAge.getText().toString().isEmpty()){
            contact.setAge(Integer.parseInt(inputAge.getText().toString()));
        }else{
            contact.setAge(-1);
        }

        if(!inputAdresse.getText().toString().isEmpty()){
            contact.setAdresse(inputAdresse.getText().toString());
        }else{
            contact.setAdresse("");
        }

        contact.setGenre(((RadioButton)findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString());

        I.putExtra("contact", contact);

        setResult(ListContact.RETOUR, I);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RETOUR && resultCode == RESULT_OK){
            Toast.makeText(this, "retour normal", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "retour pas normal", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(), ListContact.class);
        i.putExtra("retour", "retour sans valider");
        setResult(RESULT_CANCELED, i);
        finish();
    }
}