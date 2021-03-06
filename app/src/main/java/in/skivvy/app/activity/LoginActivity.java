package in.skivvy.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import in.skivvy.app.tools.HelperMethods;
import in.skivvy.app.R;

import java.util.Arrays;


public class LoginActivity extends BaseLoginActivity {

    private EditText et_mobile_number;
    private Button btn_login, fb_button, btn_google;
    public static Activity fa;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fa = this;
        et_mobile_number = (EditText) findViewById(R.id.et_mobile_number);
        btn_login = (Button) findViewById(R.id.btn_login);
        fb_button = (Button) findViewById(R.id.fb_button);
        btn_google = (Button) findViewById(R.id.btn_google);


        btn_login.setBackgroundColor(getResources().getColor(R.color.black));
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!HelperMethods.isNetworkConnected(LoginActivity.this)){
                    showCustomToast(et_mobile_number, "No internet Connection !");
                    return;
                }
                if (validateMobileNumber()){
                    validateOTP();
                }
            }
        });

        fb_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("user_photos", "email", "public_profile"));
            }
        });

        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);

            }
        });

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>()
                {
                    @Override
                    public void onSuccess(LoginResult loginResult)
                    {
                        getFacebookUserDetails(loginResult);
                    }

                    @Override
                    public void onCancel()
                    {
                        Log.e("cancel", "OnCancel");
                    }
                    @Override
                    public void onError(FacebookException exception)
                    {
                        Log.e("Error", exception.toString());
                    }
                });
    }


    private boolean validateMobileNumber() {
        if (et_mobile_number.getText().toString().trim().isEmpty()) {
            Toast.makeText(LoginActivity.this, R.string.err_msg_mobile_number, Toast.LENGTH_SHORT).show();
            requestFocus(et_mobile_number);
            return false;
        } else if (et_mobile_number.getText().toString().length() < 10){
            Toast.makeText(LoginActivity.this, R.string.err_msg_mobile_number_wrong, Toast.LENGTH_SHORT).show();
            requestFocus(et_mobile_number);
            return false;
        }
        return true;
    }


    public boolean validateOTP(){
        Intent mIntent = new Intent(this, OTPActivity.class);
        mIntent.putExtra("Mobile_Number", et_mobile_number.getText().toString());
        startActivity(mIntent);

        return true;
    }

}
