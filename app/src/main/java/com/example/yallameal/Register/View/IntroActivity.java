package com.example.yallameal.Register.View;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yallameal.HomeYallaMeal.HomeActivity;
import com.example.yallameal.Login.View.LoginActivity;
import com.example.yallameal.R;
import com.facebook.login.LoginManager;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class IntroActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    Button SignupMailBtn;
    Intent sign_intent;

    Button signgooglebtn;
    Intent sign_google_intent;

    Button LoginnupMailBtn;
    Intent login_intent;

    Button Guest_Btn;
    Intent Guest_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro);
        mAuth = FirebaseAuth.getInstance();
        // Configure Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) // From google-services.json
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Declare UI components
        SignupMailBtn = findViewById(R.id.signup_email_btn);
        signgooglebtn = findViewById(R.id.sign_google_btn);
        LoginnupMailBtn = findViewById(R.id.Login_Intro_btn);
        Guest_Btn = findViewById(R.id.Guest_Btn);
        Button loginFacebookButton = findViewById(R.id.sign_face_btn);

        printHashKey(this);


        // Initialize sign in client
        // Email Sign-Up
        signgooglebtn.setOnClickListener(v -> {
             signIn();
        });
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
        loginFacebookButton.setOnClickListener(v -> {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));

        });

        // Continue as Guest
        Guest_Btn.setOnClickListener(v -> {
            Guest_intent = new Intent(IntroActivity.this, HomeActivity.class);
            startActivity(Guest_intent);
            finish();
        });
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign-In success
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(this, "Signed in as " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                        // Navigate to home activity
                    } else {
                        Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public static void printHashKey(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(),
                    PackageManager.GET_SIGNATURES
            );
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i("KeyHash", "Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e("KeyHash", "printHashKey()", e);
        } catch (Exception e) {
            Log.e("KeyHash", "printHashKey()", e);
        }
    }



}