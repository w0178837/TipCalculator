package com.example.w0178837.tipcalculator;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.*;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    // Step 1 - Decalre variables for controls
    EditText billEditText;
    EditText tip10EditText;
    EditText tip15EditText;
    EditText tip20EditText;
    EditText total10EditText;
    EditText total15EditText;
    EditText total20EditText;
    EditText tipCustomEditText;
    EditText totalCustomEditText;
    SeekBar customSeekBar;
    TextView customTipTextView;

    public String calculateTip(double amount, double percent) {
        return String.format(Locale.getDefault(), "%,.2f", (double) (amount * percent));
    }

    public String calculateTotal(double amount, double percent) {
        return String.format(Locale.getDefault(), "%,.2f", (double) (amount * (1 + percent)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // step 2, connect Java vars to xml controls
        billEditText = (EditText) findViewById(R.id.billEditText);
        tip10EditText = (EditText) findViewById(R.id.tip10EditText);
        tip15EditText = (EditText) findViewById(R.id.tip15EditText);
        tip20EditText = (EditText) findViewById(R.id.tip20EditText);
        total10EditText = (EditText) findViewById(R.id.total10EditText);
        total15EditText = (EditText) findViewById(R.id.total15EditText);
        total20EditText = (EditText) findViewById(R.id.total20EditText);
        tipCustomEditText = (EditText) findViewById(R.id.tipCustomEditText);
        totalCustomEditText = (EditText) findViewById(R.id.totalCustomEditText);
        customSeekBar = (SeekBar) findViewById(R.id.customSeekBar);
        customSeekBar.setProgress(18);
        customTipTextView = (TextView) findViewById(R.id.customTipTextView);


        // step 3 - create listeners (as inner classes)
        TextWatcher billEditTextListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!billEditText.getText().toString().isEmpty()) {
                    tip10EditText.setText(calculateTip(Double.parseDouble(billEditText.getText().toString()), 0.1));
                    tip15EditText.setText(calculateTip(Double.parseDouble(billEditText.getText().toString()), 0.15));
                    tip20EditText.setText(calculateTip(Double.parseDouble(billEditText.getText().toString()), 0.2));
                    total10EditText.setText(calculateTotal(Double.parseDouble(billEditText.getText().toString()), 0.1));
                    total15EditText.setText(calculateTotal(Double.parseDouble(billEditText.getText().toString()), 0.15));
                    total20EditText.setText(calculateTotal(Double.parseDouble(billEditText.getText().toString()), 0.2));
                    double cusomPercent = customSeekBar.getProgress() / 100.00;
                    tipCustomEditText.setText(calculateTip(Double.parseDouble(billEditText.getText().toString()), cusomPercent));
                    totalCustomEditText.setText(calculateTotal(Double.parseDouble(billEditText.getText().toString()), cusomPercent));
                } else {
                    tip10EditText.setText("0.00");
                    tip15EditText.setText("0.00");
                    tip20EditText.setText("0.00");
                    total10EditText.setText("0.00");
                    total15EditText.setText("0.00");
                    total20EditText.setText("0.00");
                    tipCustomEditText.setText("0.00");
                    totalCustomEditText.setText("0.00");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        SeekBar.OnSeekBarChangeListener customSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                {
                    double cusomPercent = progress / 100.00;
                    customTipTextView.setText(String.format("%.0f",(double) progress)+"%");
                    tipCustomEditText.setText(calculateTip(Double.parseDouble(billEditText.getText().toString()), cusomPercent));
                    totalCustomEditText.setText(calculateTotal(Double.parseDouble(billEditText.getText().toString()), cusomPercent));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        // step 4, hook up the listeners
        billEditText.addTextChangedListener(billEditTextListener);
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);

    } // end create
}
