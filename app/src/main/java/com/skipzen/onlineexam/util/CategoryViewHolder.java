package com.skipzen.onlineexam.util;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skipzen.onlineexam.R;
import com.skipzen.onlineexam.network.AuthService;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView categoryName;
    public ImageView categoryImage;

    private AuthService itemClickListener;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryName =  (TextView) itemView.findViewById(R.id.categoryName);
        categoryImage = (ImageView) itemView.findViewById(R.id.categoryImage);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(AuthService itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(), false);

    }
}
