package com.example.crudmember;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudmember.main.LihatDataActivity;
import com.example.crudmember.main.MainContact;
import com.example.crudmember.adapter.MemberAdapter;
import com.example.crudmember.entity.AppDatabase;
import com.example.crudmember.entity.DataMember;
import com.example.crudmember.presenter.MemberPresenter;

public class Edit_data extends AppCompatActivity implements MainContact.view {
    private AppDatabase appDatabase;
    private MemberPresenter memberPresenter;
    private MemberAdapter memberAdapter;
    private EditText etName, etEmail, etPhone, etClass;
    private Button btnSubmit;
    private String setName, setEmail, setPhone, setClass ;
    private boolean edit = false;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etClass = findViewById(R.id.et_class);


        btnSubmit = findViewById(R.id.btn_submit);
        memberPresenter = new MemberPresenter(this);
        appDatabase = AppDatabase.iniDb(getApplicationContext());

        setName = getIntent().getStringExtra("name");
        setEmail = getIntent().getStringExtra("email");
        setPhone = getIntent().getStringExtra("phone");
        setClass = getIntent().getStringExtra("class");

        id = getIntent().getIntExtra("id", 99);

        etName.setText(setName);
        etEmail.setText(setEmail);
        etPhone.setText(setPhone);
        etClass.setText(setClass);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void resetForm() {
        etName.setText("");
        etEmail.setText("");
        etPhone.setText("");
        etClass.setText("");
        btnSubmit.setText("Submit");
    }

    @Override
    public void sukses() {
        Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LihatDataActivity.class));
    }

    @Override
    public void editData(DataMember item) {
        etName.setText(item.getName_member());
        etEmail.setText(item.getEmail_member());
        etPhone.setText(item.getPhone_member());
        etClass.setText(item.getClass_member());

        edit = true;
        btnSubmit.setText("Update");
    }

    @Override
    public void onClick(View v) {
        String NameMember, EmailMember, PhoneMember, ClassMember;
        NameMember = etName.getText().toString();
        EmailMember = etEmail.getText().toString();
        PhoneMember = etPhone.getText().toString();
        ClassMember = etClass.getText().toString();
        if(v ==  btnSubmit){
            if(NameMember.equals("") || EmailMember.equals("") || PhoneMember.equals("") || ClassMember.equals("")) {
                Toast.makeText(this, "Insert data please", Toast.LENGTH_SHORT).show();
            } else {

              memberPresenter.editData(NameMember, EmailMember, PhoneMember, ClassMember, id, appDatabase);
                edit = false;
            }
            resetForm();
        }
    }
}

