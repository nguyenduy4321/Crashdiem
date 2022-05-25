package com.example.crashodauem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.crashodauem.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'crashodauem' library on application startup.
    static {
        System.loadLibrary("crashodauem");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI());

        Button btnCrash = new Button(this);
        btnCrash.setText("Crash Native");
        btnCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crashNative();
            }
        });

        addContentView(btnCrash,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    /**
     * A native method that is implemented by the 'crashodauem' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    private native void crashNative();
}