package com.example.customgridview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.customgridview.model.SampleDataDetails;
import com.example.customgridviewlibrary.CustomAdapter;
import com.example.customgridviewlibrary.ItemViewHolder;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView dataItemRecyclerView;
    private static final String TAG = "MainActivity123";
    int index = 0;
    CustomAdapter<SampleDataDetails> customAdapter;
    FloatingActionButton addItemFAB;
    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        ArrayList<SampleDataDetails> sampleDataDetails = mainActivityViewModel.retrieveInitialData();
        addItemFAB.setOnClickListener(view -> displayItemDetails(null));
        customAdapter = new CustomAdapter<SampleDataDetails>(this, sampleDataDetails) {

            @Override
            public int getLayoutResourceID() {
                return R.layout.item_sample_data_view;
            }

            @Override
            public void onDataBind(SampleDataDetails dataItem, int position, ItemViewHolder itemViewHolder) {
                ImageView imageView = itemViewHolder.itemView.findViewById(R.id.imageView);
                TextView titleTextView = itemViewHolder.itemView.findViewById(R.id.textView);
                displayImageWithGLide(imageView,dataItem.imageUrl);
                titleTextView.setText(dataItem.title);
            }

            @Override
            public void onItemClick(SampleDataDetails dataItem) {
                super.onItemClick(dataItem);

                displayItemDetails(dataItem);

            }
        };
        dataItemRecyclerView.setAdapter(customAdapter);
        dataItemRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        dataItemRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                try {
                    GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                    if ((gridLayoutManager != null && gridLayoutManager.findLastCompletelyVisibleItemPosition() == sampleDataDetails.size() - 1)) {
                        if (index > 10)
                            return;
                        index = index + 4;
                        ArrayList<SampleDataDetails> sampleDataDetails = mainActivityViewModel.retrieveMoreData(index);
                        recyclerView.getRecycledViewPool().clear();
                        customAdapter.setItemList(sampleDataDetails);
                        customAdapter.notifyItemChanged(sampleDataDetails.size() - 1);

                    }
                } catch (Exception e) {
                    Log.e(TAG, "onScrollStateChanged: " + e.getLocalizedMessage());
                }
            }

        });
    }

    /**
     * initiates views to variables
     */
    private void initViews() {

        dataItemRecyclerView = findViewById(R.id.recyclerview);
        addItemFAB = findViewById(R.id.additem);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

    }

    @SuppressLint("NotifyDataSetChanged")
    void displayItemDetails(@Nullable SampleDataDetails sampleDataDetails) {
        BottomSheetDialog bottomSheet =
                new BottomSheetDialog(this);
        bottomSheet.setContentView(R.layout.dialog_item_view);
        bottomSheet.findViewById(R.id.close).setOnClickListener(view -> bottomSheet.cancel());

        ImageView deleteImageView = bottomSheet.findViewById(R.id.delete);
        ImageView imageDataImageView = bottomSheet.findViewById(R.id.imageView);

        EditText titleEditText = bottomSheet.findViewById(R.id.titleeditview);
        EditText imageUrlEditText = bottomSheet.findViewById(R.id.editText);

        if (sampleDataDetails != null) {
            deleteImageView.setVisibility(View.VISIBLE);
            titleEditText.setText(sampleDataDetails.title);
            imageDataImageView.setVisibility(View.VISIBLE);
            displayImageWithGLide(imageDataImageView,sampleDataDetails.imageUrl);
            imageUrlEditText.setText(sampleDataDetails.imageUrl);
        }
        deleteImageView.setOnClickListener(view -> {
            customAdapter.getItemList().remove(sampleDataDetails);
            dataItemRecyclerView.getRecycledViewPool().clear();
            customAdapter.notifyDataSetChanged();
            bottomSheet.cancel();
        });
        bottomSheet.findViewById(R.id.submit).setOnClickListener(view -> {
            String titleValue = titleEditText.getText().toString();
            if (titleValue.isEmpty()) {
                Toast.makeText(MainActivity.this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (imageUrlEditText.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

                List<SampleDataDetails> sampleDataDetailsList = customAdapter.getItemList();
                if (sampleDataDetails != null) {

                    int index = sampleDataDetailsList.indexOf(sampleDataDetails);

                    sampleDataDetails.title = titleValue;
                    sampleDataDetails.imageUrl = imageUrlEditText.getText().toString();
                    sampleDataDetailsList.remove(index);
                    sampleDataDetailsList.add(index, sampleDataDetails);

                } else {
                    sampleDataDetailsList.add(0, new SampleDataDetails(titleValue, imageUrlEditText.getText().toString()));
                }
                dataItemRecyclerView.getRecycledViewPool().clear();
                customAdapter.setItemList(sampleDataDetailsList);
                customAdapter.notifyDataSetChanged();
                bottomSheet.cancel();

        });
        bottomSheet.show();
    }
void displayImageWithGLide(ImageView imageView,String imageUrl)
{
    Glide.with(MainActivity.this).load(imageUrl).placeholder(R.drawable.no_photo).into(imageView);
}
}