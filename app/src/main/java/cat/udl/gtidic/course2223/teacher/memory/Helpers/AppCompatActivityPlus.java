package cat.udl.gtidic.course2223.teacher.memory.Helpers;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AppCompatActivityPlus extends AppCompatActivity {

    protected String myClassTag = this.getClass().getSimpleName();
    protected Boolean logStates = true;
    protected Boolean toastStates = false;

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        notifyState("onCreate");
    }

    @Override
    protected void onStart(){
        super.onStart();
        notifyState("onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        notifyState("onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        notifyState("onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        notifyState("onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        notifyState("onDestroy");
    }

    private void notifyState(String state){
        String message = state + " state";
        if (logStates) Log.d(this.myClassTag, message);
        if (toastStates) Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
