package ir.pkokabi.datepickerexample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import ir.pkokabi.datepicker.DialogDatePicker;


public class MainActivity extends AppCompatActivity implements DialogDatePicker.DatePickerInterface {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        AppCompatButton dialog = findViewById(R.id.dialog);

        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DialogDatePicker(context, 1, 8, 20,MainActivity.this);
            }
        });

    }

    @Override
    public void onDateSelect(String date, String dateTime, String time, int hour, int minute, int dateDifference) {

    }

}
