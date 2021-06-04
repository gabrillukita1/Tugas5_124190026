package com.example.crudmember.main;

import android.view.View;

import com.example.crudmember.entity.AppDatabase;
import com.example.crudmember.entity.DataMember;

import java.util.List;

//untuk mengaktifkan fungsi edit dan delete
public interface MainContact {
    interface view extends View.OnClickListener{
        void resetForm();
        void sukses();
        //void getData(List<DataMember> list);
        void editData(DataMember item);
        //void deleteData(DataMember item);
    }
    interface datapresenter{
        //readData(AppDatabase database);
        void editData(String name_member, String email_member, String phone_member, String class_member, int id, AppDatabase database);
        void deleteData(DataMember dataDiri, AppDatabase database);
    }
    interface Cetak extends View.OnClickListener{
        void getData(List<DataMember> list);
    }
    interface hapus{
        // void resetForm();
        void sukses();
        void deleteData(DataMember item);
    }
}
