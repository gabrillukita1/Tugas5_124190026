package com.example.crudmember.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.crudmember.entity.DataMember;

import java.util.List;

@Dao //untuk ngasih tau kalau ini kelas DAO (data access object)
public interface DataMemberDAO {
    @Insert //fungsi insert
    long insertData(DataMember dataMember); //nantinya dia akan ambil dari kelas data sekolah

    @Query("Select * from member_db") //untuk memanggil seluruh data yg ada di db
    List<DataMember> getData(); //si db itu ngambil datanya dari list yang ada di data sekolah

    @Update //fungsi edit data
    int updateData(DataMember item);

    @Delete //fungsi hapus data
    void deleteData(DataMember item);
}