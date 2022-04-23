package com.example.androidtest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidtest.tools.Suffix;

import java.io.IOException;
import java.util.EmptyStackException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button=findViewById(R.id.button);
        final EditText in1=findViewById(R.id.input1);
        final EditText in2=findViewById(R.id.input2);
        final TextView out=findViewById(R.id.out);

        button.setOnClickListener(view -> {
            String val1=in1.getText().toString();
            String val2s=in2.getText().toString();
            int val2 = 0;

            if (val1.length()==0){
                out.setText("The first input should not be a empty!");
                return;
            }

            if (val2s.length()==0){
                out.setText("The second input should not be empty!");
                return;
            }

            try {
                val2=Integer.parseInt(val2s);
            } catch (NumberFormatException e){
                out.setText(R.string.in2_error);
                return;
            }

            Suffix s1= new Suffix(val1,val2);

            try {
                out.setText("The suffix is:" + s1.getSuffix());
            }
            catch (Exception e){
                out.setText(e.getMessage());
            }
        });
    }
}