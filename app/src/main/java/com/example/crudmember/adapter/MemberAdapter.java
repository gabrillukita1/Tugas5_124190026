package com.example.crudmember.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.crudmember.R;
import com.example.crudmember.Edit_data;
import com.example.crudmember.entity.DataMember;
import com.example.crudmember.main.MainContact;


public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {
    Context context;
    List<DataMember> list;
    MainContact.hapus view;

    public MemberAdapter(Context context, List<DataMember> list, MainContact.hapus view) {
        this.view = view;
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override

    public MemberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_lihat_data, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final DataMember data = list.get(i);
        viewHolder.tvName.setText(data.getName_member());
        viewHolder.tvEmail.setText(data.getEmail_member());
        viewHolder.tvPhone.setText(data.getPhone_member());
        viewHolder.tvClass.setText(data.getClass_member());
        viewHolder.id.setText(String.valueOf(data.getId())); //disini berbeda karena id itu langsung autogenerate jadi selalu ada nilainya
        viewHolder.btnHapus.setOnClickListener(new View.OnClickListener() { //membuat action hapus
            @Override
            public void onClick(View v) {
                view.deleteData(data); //terlempar ke class maincontact
                // return true;
            }
        });
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() { //membuat action hapus
            @Override
            public void onClick(View v) {
                Intent x = new Intent(context, Edit_data.class); //pertama disini kita melemparkan class dulu dengan menyimpan berbagai data
                x.putExtra("name", data.getName_member()); //data pertama dengan name valuenya nama, dan class data sekolah.ambil nilai dari get yg ada disana
                x.putExtra("email", data.getEmail_member());
                x.putExtra("phone", data.getPhone_member());
                x.putExtra("class", data.getClass_member());
                x.putExtra("id", data.getId());
                //dia mengirim ke class edit data bahwa akan memulai aktivitas dalam tugas baru yang harus dilakukan
                x.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //proses perpindahan
                context.startActivity(x);
            }
        });

    }

    @Override
    public int getItemCount() { return list.size(); } //mendapatkan ukuran set data yg akan ditampilkan

    public class ViewHolder extends RecyclerView.ViewHolder {
        //inisialisasi text view dan button samain kaya di layout lihat data
        TextView tvName, tvEmail, tvPhone, tvClass, id;
        Button btnHapus, btnEdit;

        //class holdernya yg menghubungkan antara inisialisasinya ke yg ada pada layout lihat data
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_member);
            tvEmail = itemView.findViewById(R.id.tv_email_member);
            tvPhone = itemView.findViewById(R.id.tv_phone_member);
            tvClass = itemView.findViewById(R.id.tv_class_member);
            btnHapus = itemView.findViewById(R.id.btn_hapus);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            id = itemView.findViewById(R.id.tv_id_member);
        }
    }
}

