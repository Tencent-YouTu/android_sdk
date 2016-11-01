package com.facein;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.common.LoadingDialog;
import com.common.YTServerAPI;
import com.qq.youtu.youtuyundemo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by qingliang on 16/9/18.
 */
public class CardResultActivity extends Activity {
    private String mName;
    private String mIdCard;

    private TextView mNameText;
    private TextView mIdCardText;
    private Button mConfirmButton;

    private YTServerAPI mServerAPI;
    private LoadingDialog mLoadingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cardresult);
        mNameText = (TextView) findViewById(R.id.name);
        mIdCardText = (TextView) findViewById(R.id.idCard);
        mConfirmButton = (Button) findViewById(R.id.confirmButton);

        Intent intent = getIntent();
        mName = intent.getStringExtra("name");
        mIdCard = intent.getStringExtra("idCard");

        mNameText.setText(mName);
        mIdCardText.setText(mIdCard);

        mLoadingDialog = new LoadingDialog(this);
        mServerAPI = new YTServerAPI();

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mServerAPI.getLiveCheckData();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (KeyManagementException e) {
                            e.printStackTrace();
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }

                    }

                }).start();

            }
        });

        mServerAPI.setRequestListener(new YTServerAPI.OnRequestListener() {
            @Override
            public void onSuccess(int statusCode, String responseBody) {
                mLoadingDialog.dismiss();
                String validateData = "";
                try {
                    JSONObject jsonObject = new JSONObject(responseBody);
                    validateData = jsonObject.getString("validate_data");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(CardResultActivity.this, CameraActivity.class);
                intent.putExtra("validate_data", validateData);
                intent.putExtra("name", mName);
                intent.putExtra("idCard", mIdCard);
                startActivity(intent);
            }

            @Override
            public void onFailure(int statusCode) {
                mLoadingDialog.dismiss();

            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onDestroy(){

        super.onDestroy();
    }
}
