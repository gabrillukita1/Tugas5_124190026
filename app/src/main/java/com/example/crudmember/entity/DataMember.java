package com.example.crudmember.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "member_db") //untuk nama tabelnya
public class DataMember {
    @NonNull
    @PrimaryKey(autoGenerate = true) //menandakan primary key yaitu id
    @ColumnInfo(name = "id") //info buat atribut dalam database
    private int id; // tipe data dari masing masin gatributnya

    @ColumnInfo(name = "name_member")
    private String name_member;

    @ColumnInfo(name = "email_member")
    private String email_member;

    @ColumnInfo(name = "phone_member")
    private String phone_member;

    @ColumnInfo(name = "class_member")
    private String class_member;

    //selanjutnya di alt enter untuk setter dan getternya
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) { this.id = id; }

    public String getName_member() { return name_member; }

    public void setName_member(String name_member) {
        this.name_member = name_member;
    }

    public String getEmail_member() {
        return email_member;
    }

    public void setEmail_member(String email_member) {
        this.email_member = email_member;
    }

    public String getPhone_member() {
        return phone_member;
    }

    public void setPhone_member(String phone_member) { this.phone_member = phone_member; }

    public String getClass_member() {
        return class_member;
    }

    public void setClass_member(String class_member) { this.class_member = class_member; }


}

