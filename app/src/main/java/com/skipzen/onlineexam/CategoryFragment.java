package com.skipzen.onlineexam;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skipzen.onlineexam.model.Category;
import com.skipzen.onlineexam.model.Common;
import com.skipzen.onlineexam.model.DataItem;
import com.skipzen.onlineexam.model.QuestionResponse;
import com.skipzen.onlineexam.model.Response;
import com.skipzen.onlineexam.network.AuthService;
import com.skipzen.onlineexam.network.ItemClickListener;
import com.skipzen.onlineexam.util.CategoryViewHolder;
import com.squareup.picasso.Picasso;

import retrofit2.Call;

import static androidx.recyclerview.widget.RecyclerView.*;


public class CategoryFragment extends Fragment {

    View myFragment;

    RecyclerView listCategory;
    LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference categories;

    public static CategoryFragment newInstance(){
        CategoryFragment categoryFragment = new CategoryFragment();
        return categoryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        categories = database.getReference("QuestionType");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_category, container, false);
        listCategory = (RecyclerView) myFragment.findViewById(R.id.listCategory);
        listCategory.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(container.getContext());
        listCategory.setLayoutManager(layoutManager);

        loadCategory();
        return myFragment;
    }

    private void loadCategory() {
        adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(
                Category.class,
                R.layout.category_layout,
                CategoryViewHolder.class,
                categories
        ) {
            @Override
            protected void populateViewHolder(CategoryViewHolder categoryViewHolder, final Category model, int position) {
                categoryViewHolder.categoryName.setText(model.getName());
                Picasso.with(getActivity())
                        .load(model.getImage())
                        .into(categoryViewHolder.categoryImage);

                categoryViewHolder.setItemClickListener(new AuthService() {
                    @Override
                    public Call<Response> login(String login, String password) {
                        return null;
                    }

                    @Override
                    public Call<QuestionResponse> getExamData(String token) {
                        return null;
                    }

                    @Override
                    public Call<DataItem> getQuestion() {
                        return null;
                    }

                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                       // Toast.makeText(getActivity(), String.format("%s|%s", adapter.getRef(position).getKey(), model.getName()), Toast.LENGTH_SHORT).show();
                        Intent StartExam = new Intent(getActivity(), StartExam.class);
                        Common.categoriId = adapter.getRef(position).getKey();
                        startActivity(StartExam);
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        listCategory.setAdapter(adapter);
    }

}
