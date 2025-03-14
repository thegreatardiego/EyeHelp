package com.example.eyehelp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class AdminMapAdapter extends RecyclerView.Adapter<AdminMapAdapter.MyViewHolder> {

    Context context;
    ArrayList<UserInformation>list;

    public AdminMapAdapter(Context context, ArrayList<UserInformation> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdminMapAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName()); // Use getName() instead of name()
        holder.email.setText(list.get(position).getEmail()); // Use getEmail() instead of email()
        holder.location.setText(list.get(position).getLocation()); // Use getLocation() instead of location()
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, location;
        public MyViewHolder (@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.EyeSpecialist);
            email=itemView.findViewById(R.id.emailtext);
            location=itemView.findViewById(R.id.Locationtext);
        }

    }
}
