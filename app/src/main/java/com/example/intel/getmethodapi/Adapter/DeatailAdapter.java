package com.example.intel.getmethodapi.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intel.getmethodapi.PersonalDetails;
import com.example.intel.getmethodapi.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DeatailAdapter extends RecyclerView.Adapter<DeatailAdapter.MyViewHolder> {
    Context context;
    ArrayList<PersonalDetails> mList;
    OnClickListener onClickListener;

    public interface OnClickListener {
        void onLayoutClick(int position);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    public DeatailAdapter(Context context, ArrayList<PersonalDetails> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.createlistdata,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PersonalDetails item = mList.get(position);
        holder.nameTxt.setText(item.getName());
        holder.countryTxt.setText(item.getCountry());
        holder.stateTxt.setText(item.getCity());
        holder.cityTxt.setText(item.getState());
        if (!item.getImage().isEmpty()) {
            String imageUrl = "http://andydevelopment.com/CurbCart/" + item.getImage();
            Glide.with(context).load(imageUrl).into(holder.profileimage);

            /*
            String attachmentBaseStr=item.getImage(); // pojo.getImage();
            byte[] imageByteArray = Base64.decode(attachmentBaseStr, Base64.DEFAULT);
            Glide.with(context)
                    .load(imageByteArray)
                    .asBitmap()
                    .into(holder.profileimage); //imageview name        }
            */

       /* String mobileNo="";
        for (int i=0;i<item.getPhoneno().size();i++){
            mobileNo=mobileNo+item.getPhoneno().get(i)+" - ";
        }
        holder.mobile.setText(mobileNo);*/
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileimage;
        TextView nameTxt,countryTxt,cityTxt,stateTxt;

        public MyViewHolder(View itemView) {
            super(itemView);
            profileimage=(CircleImageView)itemView.findViewById(R.id.image_vie);
            nameTxt=(TextView)itemView.findViewById(R.id.name_txt);
            countryTxt=(TextView)itemView.findViewById(R.id.country_txt);
            cityTxt=(TextView)itemView.findViewById(R.id.city_txt);
            stateTxt=(TextView)itemView.findViewById(R.id.state_txt);
            //mLayout=(LinearLayout)itemView.findViewById(R.id.layout_list);




          /*  mLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(onClickListener!=null)
                        onClickListener.onLayoutClick(getAdapterPosition());

                    return false;
                }
            });*/
        }
    }
}
