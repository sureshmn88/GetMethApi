package com.example.intel.getmethodapi.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.intel.getmethodapi.Adapter.DeatailAdapter;
import com.example.intel.getmethodapi.PersonalDetails;
import com.example.intel.getmethodapi.R;
import com.example.intel.getmethodapi.Utils.GlobalValues;

import java.util.ArrayList;

public class UserDetailsScreen extends AppCompatActivity {
    RecyclerView mRecyclerView;
    DeatailAdapter mAdapter;
    ArrayList<PersonalDetails> mList=new ArrayList<>();
    GlobalValues mGlobalValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_screen);

        mGlobalValues=new GlobalValues(this);

        Intent intent=getIntent();
        if(intent.hasExtra("sentData"))
        {
            mList=intent.getParcelableArrayListExtra("sentData");
        }
        mRecyclerView=(RecyclerView)findViewById(R.id.recycelr_list);
        FetchDataWithAdapter();

        Log.d("TAG","Email : "+mGlobalValues.getString("email"));


    }

    void FetchDataWithAdapter() {

        if(mList.size()>0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            //alertTxt.setVisibility(View.GONE);

            mAdapter = new DeatailAdapter(UserDetailsScreen.this, mList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UserDetailsScreen.this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.addItemDecoration(new DividerItemDecoration(UserDetailsScreen.this, LinearLayoutManager.VERTICAL));
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

            /*mAdapter.setOnClickListener(new ProductData.OnClickListener() {
                @Override
                public void onLayoutClick(int position) {
                    selectPosition = position;

                    Intent intent = new Intent(MainActivity.this, AddDataList.class);
                    intent.putExtra("getdata", mList.get(selectPosition));
                    startActivityForResult(intent, requestCodeForResult);
                }
            });*/
        }else {

            //mRecyclerView.setVisibility(View.GONE);
           // alertTxt.setVisibility(View.VISIBLE);
        }

    }
}
