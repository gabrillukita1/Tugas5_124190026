package com.example.crudmember.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.crudmember.entity.AppDatabase;
import com.example.crudmember.entity.DataMember;
import com.example.crudmember.main.MainContact;


public class MemberPresenter implements MainContact.datapresenter{
    MainContact.view view;
    MainContact.hapus viewH;
    public MemberPresenter(MainContact.view view) {
        this.view = view;
    }

    public MemberPresenter(MainContact.hapus viewH) {
        this.viewH = viewH;
    }

    class EditData extends AsyncTask<Void, Void, Integer> {
        private AppDatabase database;
        private DataMember dataMember;
        public EditData(AppDatabase database, DataMember dataMember) {
            this.database = database;
            this.dataMember = dataMember;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return database.dao().updateData(dataMember);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d("integer db", "onPostExecute: " + integer);
            view.sukses();
        }
    }

    @Override
    public void editData(String name_member, String email_member, String phone_member, String class_member, int id, AppDatabase database) {
        final DataMember dataMember = new DataMember();
        dataMember.setName_member(name_member);
        dataMember.setEmail_member(email_member);
        dataMember.setPhone_member(phone_member);
        dataMember.setClass_member(class_member);
        dataMember.setId(id);
        new EditData(database, dataMember).execute();
    }
    class DeleteData extends AsyncTask<Void, Void, Void>{
        private AppDatabase database;
        private DataMember dataMember;
        Context context;
        public DeleteData(AppDatabase database, DataMember dataMember) {
            this.database = database;
            this.dataMember = dataMember;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.dao().deleteData(dataMember);
            return  null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            viewH.sukses();
        }

    }
    @Override
    public void deleteData(DataMember dataMember, AppDatabase database) {
        new DeleteData(database,dataMember).execute();
    }

}
