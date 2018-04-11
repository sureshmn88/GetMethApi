package com.example.intel.getmethodapi.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.intel.getmethodapi.PersonalDetails;
import com.example.intel.getmethodapi.R;
import com.example.intel.getmethodapi.Utils.CommonFuns;
import com.example.intel.getmethodapi.Utils.Constants;
import com.example.intel.getmethodapi.Utils.GlobalValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText numberEdt;
    Button submitBtn;
    ArrayList<PersonalDetails> mList;
    GlobalValues mGlobalValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intizlise();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDetailsFromServer(numberEdt.getText().toString());
            }
        });
    }

     void intizlise() {

        mGlobalValues=new GlobalValues(this);
        numberEdt=(EditText)findViewById(R.id.number_edt);
        submitBtn=(Button)findViewById(R.id.submit_btn);
    }

    void getDetailsFromServer(String valuesedt) {

        if (CommonFuns.isNetworkConnected(this)) {

            CommonFuns.showProgressDialog(this);

            // Get
            //AndroidNetworking.get(Constants.GET_DETAILS)

                String Url="http://andydevelopment.com/CurbCart/getallstores2.php?userid="+valuesedt;
            //String Url="http://andydevelopment.com/CurbCart/getallstores2.php?userid="+valuesedt
            // +"&email="+emaiedt+"&age="+ageedt;

            Log.d("TAGGG","url : "+Url);

            AndroidNetworking.get(Url)
                    .setTag("GetDetails")
                    .setPriority(Priority.HIGH)
                   // .addBodyParameter("name", "34") // Parameter Name and Value
                    //.addBodyParameter("pass", "454")
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // do anything with response


                            Log.d("TAGW", "Server Response:" + response);
                            CommonFuns.stopProgressDialog(MainActivity.this);

                            try {
                                if (response.has("status")) {
                                    if(response.getString("status").equals("true")){
                                    JSONArray itemArray = response.getJSONArray("data");

                                    mList=new ArrayList<>();

                                    for (int i = 0; i < itemArray.length(); i++) {
                                        JSONObject jObj = itemArray.getJSONObject(i);
                                        PersonalDetails item = new PersonalDetails();
                                        item.setName(jObj.getString("storename"));
                                        item.setCity(jObj.getString("city"));
                                        item.setState(jObj.getString("state"));
                                        item.setEmail(jObj.getString("email"));
                                        item.setCountry(jObj.getString("country"));
                                        item.setImage(jObj.getString("image"));
                                        mList.add(item);
                                        mGlobalValues.put("email",jObj.getString("email"));

                                        //mDBHandler.addProductJsonData(jObj.getString("id"), jObj.getString("name"), jObj.getString("price"), jObj.getString("uom"));
                                    }

                                    Intent intent=new Intent(MainActivity.this,UserDetailsScreen.class);
                                    intent.putParcelableArrayListExtra("sentData",mList);
                                    startActivity(intent);
                                    Toast.makeText(MainActivity.this, "Stored in Local Database", Toast.LENGTH_SHORT).show();



                                }

                            } }catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(ANError error) {
                            // handle error
                            //prepareResponse(error.getErrorBody());
                            CommonFuns.stopProgressDialog(MainActivity.this);
                            Toast.makeText(MainActivity.this, error.getErrorBody(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
