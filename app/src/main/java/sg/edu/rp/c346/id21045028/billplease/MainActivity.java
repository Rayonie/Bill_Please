package sg.edu.rp.c346.id21045028.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amt_enter;
    EditText px_enter;
    ToggleButton svs_button;
    ToggleButton gst_button;
    EditText disc_enter;
    RadioGroup payment_by;
    Button calculate;
    Button reset;
    TextView totalbill;
    TextView eachpay;
    TextView errortext;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amt_enter = findViewById(R.id.amount_enter);
        px_enter = findViewById(R.id.pax_enter);
        svs_button = findViewById(R.id.svs);
        gst_button = findViewById(R.id.gst);
        disc_enter = findViewById(R.id.discount_enter);
        payment_by = findViewById(R.id.payment_type);
        calculate = findViewById(R.id.split);
        reset = findViewById(R.id.reset);
        totalbill = findViewById(R.id.total_bill);
        eachpay = findViewById(R.id.each_pay);
        errortext = findViewById(R.id.error);
        errortext.setText("");


        calculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                double gst = 0.07;
                double svs = 0.1;
                String in_cash = "in cash";
                String in_pay_now = "via PayNow to 12345678";
                String amt_string = amt_enter.getText().toString();
                String pax_string = px_enter.getText().toString();
                String disc_string = disc_enter.getText().toString();
                errortext.setText(amt_string);
                if( amt_string.equals("") || pax_string.equals("") || disc_string.equals("")) {
                    errortext.setText("Invalid Text! Please enter all the fields!");
                }else{
                    double discount = Double.parseDouble(disc_enter.getText().toString()) / 100;
                    double amt = Double.parseDouble(amt_enter.getText().toString());
                    double pax = Double.parseDouble(px_enter.getText().toString());
                    int checkedRadioId = payment_by.getCheckedRadioButtonId();
                    errortext.setText("");
                    if (svs_button.isChecked() && gst_button.isChecked()) {
                        double total_bill = amt + (amt * gst) + (amt * svs) - (amt * discount);
                        double perpaxbill = (total_bill / pax);
                        String string_bill = String.format("Total Bill : $%.2f ", total_bill);
                        String string_pax = String.format("Each Pays : $%.2f ", perpaxbill);
                        totalbill.setText(string_bill);
                        if (checkedRadioId == R.id.cash) {
                            eachpay.setText(string_pax + in_cash);
                        } else {
                            eachpay.setText(string_pax + in_pay_now);
                        }
                    } else if (svs_button.isChecked()) {
                        double total_bill = amt + (amt * svs) - (amt * discount);
                        double perpaxbill = (total_bill / pax);
                        String string_bill = String.format("Total Bill : $%.2f ", total_bill);
                        String string_pax = String.format("Each Pays : $%.2f ", perpaxbill);
                        totalbill.setText(string_bill);
                        if (checkedRadioId == R.id.cash) {
                            eachpay.setText(string_pax + in_cash);
                        } else {
                            eachpay.setText(string_pax + in_pay_now);
                        }
                    } else if (gst_button.isChecked()) {
                        double total_bill = amt + (amt * gst) - (amt * discount);
                        double perpaxbill = (total_bill / pax);
                        String string_bill = String.format("Total Bill : $%.2f ", total_bill);
                        String string_pax = String.format("Each Pays : $%.2f ", perpaxbill);
                        totalbill.setText(string_bill);
                        if (checkedRadioId == R.id.cash) {
                            eachpay.setText(string_pax + in_cash);
                        } else {
                            eachpay.setText(string_pax + in_pay_now);
                        }


                    } else if (gst_button.isChecked() == false && svs_button.isChecked() == false) {
                        double total_bill = amt - (amt * discount);
                        double perpaxbill = (total_bill / pax);
                        String string_bill = String.format("Total Bill : $%.2f ", total_bill);
                        String string_pax = String.format("Each Pays : $%.2f ", perpaxbill);
                        totalbill.setText(string_bill);
                        if (checkedRadioId == R.id.cash) {
                            eachpay.setText(string_pax + in_cash);
                        } else {
                            eachpay.setText(string_pax + in_pay_now);
                        }
                    }
                }



            }
        });

        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                amt_enter.setText("");
                px_enter.setText("");
                disc_enter.setText("");
                svs_button.setChecked(false);
                gst_button.setChecked(false);
                totalbill.setText("Total Bill : $");
                eachpay.setText("Each Pays : $");
                payment_by.check(R.id.cash);
                errortext.setText("");
            }
            });
    }
}