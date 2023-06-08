package com.wiam.javasip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class AmericanoActivity extends AppCompatActivity {
    // creating variables for our edittext, button and dbhandler
    private TextView nameEdt;
    //private RadioButton sizeEdt, creamEdt;
    private Button addCoffeeBtn;
    private DBHandler dbHandler;
    String sizeOpt,creamOpt, price;

    public void RadioButtonClicked (View view){
        //This variable will store the user cup size and price
        sizeOpt = "";
        price="";
        // Check that the button is  now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.small:
                if (checked)
                    sizeOpt = "small";
                price="10";
                break;
            case R.id.medium:
                if (checked)
                    sizeOpt = "medium";
                price="12";
                break;
            case R.id.large:
                if (checked)
                    sizeOpt = "large";
                price="13";
                break;
        }
    }
    public void RadioButtonClicked2 (View view){
        //This variable will store whether the user want cream or not
        creamOpt = "";
        // Check that the button is  now checked?
        boolean checked2 = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.cream:
                if (checked2)
                    creamOpt = "cream";
                break;
            case R.id.nocream:
                if (checked2)
                    creamOpt = "no cream";
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_americano);
        // initializing all our variables.
        nameEdt = findViewById(R.id.editTextTextPersonName);
        // creating a new dbhandler class and passing our context to it.
        dbHandler = new DBHandler(AmericanoActivity.this);
        //Note the name of the method must match the xml onClick value in this case 'RadioButtonClicked'
        addCoffeeBtn=findViewById(R.id.addCoffeeBtn);
        addCoffeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is to get data from all edit text fields.
                String name = nameEdt.getText().toString();
                // validating if the text fields are empty or not.
                if (name.isEmpty() && sizeOpt.isEmpty() && creamOpt.isEmpty()){
                    Toast.makeText(AmericanoActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
// on below line we are calling a method to add new order to sqlite data and pass all our values to it.
                dbHandler.addNewOrder(name, sizeOpt, creamOpt, price);
// after adding the data we are displaying a toast message.
                Toast.makeText(AmericanoActivity.this, "order has been added.", Toast.LENGTH_SHORT).show();
                return;
            }
        });

    }
}
