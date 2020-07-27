package com.boss.fortify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup;

import com.bumptech.glide.load.model.Model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;




import java.io.File;
import java.util.ArrayList;

public class gallery extends AppCompatActivity {

    private RecyclerView mBlogList;


    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("uploads");
        mDatabase.keepSynced(true);

        mBlogList = (RecyclerView) findViewById(R.id.imagegallery);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
    }

    // search data
    private void firebaseSearch(String searchText) {
        Query firebaseSearchQuery = mDatabase.orderByChild("title").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                        Blog.class,
                        R.layout.blog_row,
                        BlogViewHolder.class,
                        firebaseSearchQuery) {
                    @Override
                    protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getTitle(),model.getDesc(),model.getImage());

                    }
                };
        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>
                (Blog.class, R.layout.blog_row, BlogViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(), model.getImage());


            }
        };


        mBlogList.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu; this adds items to the actionbar if it present
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                firebaseSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);

    }


    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title) {
            TextView post_title = (TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);

        }

        public void setDesc(String desc) {
            TextView post_desc = (TextView) mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }

        public void setImage(Context ctx, String image) {
            ImageView post_Image = (ImageView) mView.findViewById(R.id.post_image);
            Picasso.with(ctx)
                    .load(image)
                    .centerCrop()
                    .fit()
                    .into(post_Image);

            }
        public void setDetails(Context ctx, String title, String desc, String image) {
            TextView post_title = mView.findViewById(R.id.post_title);
            TextView post_desc = mView.findViewById(R.id.post_desc);
            ImageView post_Image = mView.findViewById(R.id.post_image);

            post_title.setText(title);
            post_desc.setText(desc);
            Picasso.with(ctx)
                    .load(image)
                    .centerCrop()
                    .fit()
                    .into(post_Image);
        }
        }
    }

