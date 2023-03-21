package cat.udl.gtidic.course2223.teacher.memory.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cat.udl.gtidic.course2223.teacher.memory.R;
import cat.udl.gtidic.course2223.teacher.memory.helpers.ActivityHelper;

public class LoginActivity extends AppCompatActivity {

    protected String myClassTag = this.getClass().getSimpleName();

    EditText etEmail;
    EditText etPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmailLogin);
        etPassword = findViewById(R.id.etPasswordLogin);
        findViewById(R.id.btLogin).setOnClickListener(v -> login());
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        reload();
    }

    private void login() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        ActivityHelper.hideKeyboard(this);

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(myClassTag, "signInWithEmail:success");
                        reload();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(myClassTag, "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        etPassword.setText("");
                    }
                }
            });
    }

    private void reload(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            etPassword.setText("");
            etEmail.setText("");
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}