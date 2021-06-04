package com.example.crudmember.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudmember.R;
import com.example.crudmember.adapter.MemberAdapter;
import com.example.crudmember.entity.AppDatabase;
import com.example.crudmember.entity.DataMember;
import com.example.crudmember.presenter.MemberPresenter;

import java.util.List;

public class LihatDataActivity extends AppCompatActivity implements MainContact.hapus {
    private AppDatabase appDatabase;
    private MemberAdapter memberAdapter;
    private MemberPresenter memberPresenter;
    View view;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc_view);
        memberPresenter = new MemberPresenter(this);
        recyclerView = findViewById(R.id.rc_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        appDatabase = AppDatabase.iniDb(getApplicationContext());

        readData(appDatabase);
    }

    public void readData(AppDatabase database) {
        List list;
        list = database.dao().getData();
        //view.getData(list);
        memberAdapter = new MemberAdapter(getApplicationContext(), list, this);
        recyclerView.setAdapter(memberAdapter);
    }

    @Override
    public void sukses() {
        Toast.makeText(getApplicationContext(), "Data Berhasil di hapus", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), com.example.crudmember.main.LihatDataActivity.class));
    }


    @Override
    public void deleteData(final DataMember item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // resetForm();
                        memberPresenter.deleteData(item, appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}

