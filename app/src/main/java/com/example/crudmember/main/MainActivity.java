package com.example.crudmember.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudmember.R;
import com.example.crudmember.entity.AppDatabase;
import com.example.crudmember.entity.DataMember;

public class MainActivity extends AppCompatActivity {
    //inisiasi edit text, button, string untuk membaca inputan sesuaiin sama layout activity main, dan inisiasi class appdatabase
    private EditText etName, etEmail, etPhone, etClass ;
    private Button btnSubmit, btnLihat;
    private String setName, setEmail, setPhone, setClass ;

    AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //seperti biasa untuk mengambil id dari layout yang dihubungkan dengan variable yang telah dibuat diatas
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etClass = findViewById(R.id.et_class);


        btnSubmit = findViewById(R.id.btn_submit);
        btnLihat = findViewById(R.id.btn_lihat);
        appDatabase = AppDatabase.iniDb(getApplicationContext()); //menginisiaslisasi db
        btnSubmit.setOnClickListener(new View.OnClickListener() { //action perpindahan dari class ini ke class lihat data
            @Override
            public void onClick(View v) {
                input();
                Intent submit = new Intent(getApplicationContext(), LihatDataActivity.class);
                startActivity(submit);
            }
        });
        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lihat = new Intent(getApplicationContext(), LihatDataActivity.class);
                startActivity(lihat);
            }
        });
    }
    //fungsi untuk create ke databasenya
    public void input(){
        setName = etName.getText().toString();
        setEmail = etEmail.getText().toString();
        setPhone = etPhone.getText().toString();
        setClass = etClass.getText().toString();

        //manggil kelas data sekolah
        final DataMember dataMember = new DataMember();

        dataMember.setName_member(setName);
        dataMember.setEmail_member(setEmail);
        dataMember.setPhone_member(setPhone);
        dataMember.setClass_member(setClass);

        //memanggil fungsi insert data(variable appdatabase untuk koneksi ke app database, variabel kelas data sekolah untuk masukin data
        //ke databasee terus nanti baru di eksekusi
        new InsertData(appDatabase, dataMember).execute();
    }
    //class insertdata dengan extendsnya
    //asynctask itu kelas yg disediakan android untuk proses pengambilan/pengiriman yang dilakukan secara background
    class InsertData extends AsyncTask<Void, Void, Long> {
        //inisialisasi appdatabase dan datasekolah
        private AppDatabase database;
        private DataMember dataMember;

        //fungsi insert data ini menggunakan kelas kelas yg sudah di inisialisasi
        public InsertData(AppDatabase database, DataMember dataMember) {
            this.database = database;
            this.dataMember = dataMember;
        }

        //nah ini method yg ada di class asynctask
        //pada method ini proses thread berjalan, dan proses pengiriman/pengambilan datanya
        @Override
        protected Long doInBackground(Void... voids) {
            //dari appdatabase diambil var dao yang adalah class datasekolahdao lalu mengakses fungsi insertdata dengan parameternya datasekolah
            return database.dao().insertData(dataMember);
        }

        //method ini untuk mengupdate user interface ketika proses doinbackground telah selesai
        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            //ngeluarin teks notif "sukses" untuk waktu yang sebentar, kalau dalam waktu lama diganti aja LENGTH_LONG
            Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_SHORT).show();

        }

    }
}
