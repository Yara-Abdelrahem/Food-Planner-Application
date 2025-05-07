package com.example.yallameal.Register.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yallameal.HomeYallaMeal.HomeActivity;
import com.example.yallameal.Login.View.LoginActivity;
import com.example.yallameal.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONException;

import java.util.Arrays;

public class IntroActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private CallbackManager callbackManager;
    SharedPreferences sharedPreferences;
    Button SignupMailBtn, signgooglebtn, LoginnupMailBtn, Guest_Btn;
    Intent sign_intent, sign_google_intent, login_intent, Guest_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro);

        mAuth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create(); // Initialize Facebook callback manager

        
        // Google Sign-In setup
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        sharedPreferences = getSharedPreferences("MyPrefsFile", MODE_PRIVATE); // <-- declare here only

        // Buttons
        SignupMailBtn = findViewById(R.id.signup_email_intro_btn);
        signgooglebtn = findViewById(R.id.sign_google_btn);
        LoginnupMailBtn = findViewById(R.id.Login_Intro_btn);
        Guest_Btn = findViewById(R.id.Guest_Btn);
        Button loginFacebookButton = findViewById(R.id.signup_face_btn);

        // Google sign-in click
        signgooglebtn.setOnClickListener(v -> signIn());

        // Email SignUp
        SignupMailBtn.setOnClickListener(v -> {
            sign_intent = new Intent(IntroActivity.this, SignUpActivity.class);
            startActivity(sign_intent);
            finish();
        });

        // Email Login
        LoginnupMailBtn.setOnClickListener(v -> {
            login_intent = new Intent(IntroActivity.this, LoginActivity.class);
            startActivity(login_intent);
            finish();
        });

        // Facebook login click
        loginFacebookButton.setOnClickListener(v -> {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
        });

        // Facebook login callback
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                fetchFacebookEmail(accessToken);
                Intent guestIntent = new Intent(IntroActivity.this, HomeActivity.class);
                startActivity(guestIntent);
                finish();
            }

            @Override
            public void onCancel() {
                Toast.makeText(IntroActivity.this, "Facebook login canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(IntroActivity.this, "Facebook login failed", Toast.LENGTH_SHORT).show();
            }
        });

        // Guest login
        Guest_Btn.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("user_email");
            editor.apply();
            Intent guestIntent = new Intent(IntroActivity.this, HomeActivity.class);
            startActivity(guestIntent);
            finish();
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // Google Sign-In result handler
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data); // Pass to Facebook

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());

                if (account != null) {
                    String email = account.getEmail();
                    saveEmailToSharedPreferences(email);
                    navigateToHome();
                }
            } catch (ApiException e) {
                Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Authenticate with Firebase using Google token
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(this, "Signed in as " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Get email from Facebook Graph API
    private void fetchFacebookEmail(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, (object, response) -> {
            try {
                String email = object.has("email") ? object.getString("email") : null;
                if (email != null) {
                    saveEmailToSharedPreferences(email);
                    navigateToHome();
                    Log.i("facebook", email);
                } else {
                    Log.e("Facebook", "Email not available from Facebook");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void saveEmailToSharedPreferences(String email) {
//        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_email", email);
        editor.apply();
    }

    private void navigateToHome() {
        Intent intent = new Intent(IntroActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
