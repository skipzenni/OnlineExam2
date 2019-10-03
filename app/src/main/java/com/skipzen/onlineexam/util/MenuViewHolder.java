package com.skipzen.onlineexam.util;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skipzen.onlineexam.R;
import com.skipzen.onlineexam.network.AuthService;

public class MenuViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtQuestion, txtjmlQuestion;
    public ImageView imgQuestion;

    private AuthService itemClickListener;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        txtQuestion = itemView.findViewById(R.id.txtSoalText);
        txtjmlQuestion = itemView.findViewById(R.id.txtJmlSoal);
        imgQuestion = itemView.findViewById(R.id.imgSoal);

        itemView.setOnClickListener(this);
    }
    public void setItemClickListener(AuthService itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
