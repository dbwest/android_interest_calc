/*
 * Copyright (C) 2013 Code Here Now - A subsidiary of Mobs & Geeks
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file 
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package com.codeherenow.sicalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class SICalculatorActivity extends Activity implements OnClickListener {

    private EditText principal;
    private EditText interest_rate;
    private TextView calc_result;

    private static SeekBar year_slider;
    private static TextView year_text;

    private Button calc_button;
    int years = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sicalculator);

        principal = (EditText) findViewById(R.id.principal);
        interest_rate = (EditText) findViewById(R.id.interest_rate);
        calc_result = (TextView) findViewById(R.id.calc_result);

        calc_button = (Button) findViewById(R.id.calc_button);
        calc_button.setOnClickListener(this);

        seek_bar_method ();
    }

    public void seek_bar_method (){

        year_slider = (SeekBar) findViewById(R.id.year_slider);
        year_text = (TextView) findViewById(R.id.year_text);

        year_slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setYears(progress);
                year_text.setText(year_slider.getProgress() + " years" );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v == calc_button) {
        Double principal_val = Double.parseDouble(principal.getText().toString());
        int interest_rate_val = Integer.parseInt(interest_rate.getText().toString());
        int years_val = getYears();

        Double result = principal_val * (interest_rate_val / 100.0) * years_val;

        String output = "The interest for $" + String.format("%.2f", principal_val) + " at a rate of " + interest_rate_val + "% for " + years_val + " year(s) is $" + String.format("%.2f", result);

        calc_result.setText(output);
        }
    }

    public int getYears() {
        return this.years;
    }

    public void setYears(int years) {
        this.years = years;
    }

}
