package sg.edu.rp.c346.id22024852.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amtInput;
    EditText paxInput;
    EditText dcInput;

    ToggleButton tgsvs;
    ToggleButton tggst;


    Button brst;
    Button bsplt;

    TextView totalbill;
    TextView eachpays;

    RadioGroup payment;


    String stringresponse = "Total Billed: $";
    String stringresponse2 = "Each Pays: $";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amtInput = findViewById(R.id.editTextAmount);
        paxInput = findViewById(R.id.editTextPax);
        dcInput = findViewById(R.id.editTextDiscount);
        tgsvs = findViewById(R.id.toggleButtonSVS);
        tggst = findViewById(R.id.toggleButtonGST);
        brst = findViewById(R.id.resetButton);
        bsplt = findViewById(R.id.splitButton);
        totalbill = findViewById(R.id.textViewTotal);
        eachpays = findViewById(R.id.textViewIndividual);
        payment = findViewById(R.id.radioGroupPayMethod);

        bsplt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Calculate Total
                double total = 0.0;


                if (amtInput.getText().toString().trim().length() !=0 && paxInput.getText().toString().trim().length() !=0){

                 if (tggst.isChecked() && !tgsvs.isChecked()) {
                    total = Double.parseDouble(amtInput.getText().toString()) * 1.07;
                } else if (tggst.isChecked() && tgsvs.isChecked()) {
                    total = Double.parseDouble(amtInput.getText().toString()) * 1.17;
                } else if (!tggst.isChecked() && tgsvs.isChecked()){
                    total = Double.parseDouble(amtInput.getText().toString()) * 1.10;
                } else if (!tggst.isChecked() && !tgsvs.isChecked()){
                    total = Double.parseDouble(amtInput.getText().toString()) * 1;
                }
                    stringresponse += String.format("%.2f", total);}


                // discount
                if (dcInput.getText().toString().length()!=0){
                    double discount = 1 - Double.parseDouble(dcInput.getText().toString()) / 100;
                    total *= discount;
                }


                totalbill.setText(stringresponse);

                String stringResponse = "Total Billed: $" + String.format("%.2f",total);
                totalbill.setText(stringResponse);
                if (Integer.parseInt(paxInput.getText().toString()) != 0){
                    Double newAmt = total /  Integer.parseInt(paxInput.getText().toString());
                    stringresponse2 += String.format("%.2f", newAmt);
                }
                String method = " ";
                int checkedRadioId = payment.getCheckedRadioButtonId();
                if(checkedRadioId == R.id.radioButtonCash){
                    // Write the code when male selected

                    method = " in cash";
                }
                else{
                    // Write the code when female selected
                    method = " via PayNow to 91234567";

                }
                eachpays.setText(stringresponse2 + method);



            }
        });


        brst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eachpays.setText("");
                totalbill.setText("");
                amtInput.setText("");
                paxInput.setText("");
                dcInput.setText("");
            } });






    }




}