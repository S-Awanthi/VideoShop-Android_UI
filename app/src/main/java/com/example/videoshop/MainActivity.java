package com.example.videoshop;

import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //    ------------------------------------- For date picker ----------------------------------------
    EditText b_day;
    final Calendar myCalendar = Calendar.getInstance();
//    ----------------------------------------------------------------------------------------------

    //    ------------------------------------- For category alert dialog  -----------------------------
    Button mAlertBtn;
    TextView mOutputAlert;
//    ----------------------------------------------------------------------------------------------

    //    ------------------------------------- For save button  -----------------------------
    Button mSaveBtn;
//    ----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//    ------------------------------------- For date picker ----------------------------------------
        b_day = findViewById(R.id.dobInput);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //Set calender
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        b_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
//        ------------------------------------------------------------------------------------------


//        ------------------------------------- For category alert dialog  -------------------------

        mAlertBtn = findViewById(R.id.alertBtn);
        mOutputAlert = findViewById(R.id.outputAlert);

        mAlertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                //String array for multi choice categories
                String[] categoryArray = new String[] {"Action","Adventure","Historical","Crime","Thriller","Horror","Fantasy","Comedy"};

                //Boolean array for initial selected items
                final boolean[] checkedCategoryArray = new boolean[]{
                        //Initial status of 8 elements
                        false,  false,  false,  false,
                        false,  false,  false,  false
                };

                //Convert the categoryArray to a list
                final List<String> colorList = Arrays.asList(categoryArray);

                //Set alert dialog box title
                builder.setTitle("Pick Your Favourite Movie Categories");

                //Set the icon before title
                builder.setIcon(R.drawable.icofav);

                //Set multi choice items
                builder.setMultiChoiceItems(categoryArray, checkedCategoryArray, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                        //Update current focused item's checked status
                        checkedCategoryArray[which] = isChecked;

                        //Get the all current focused items
                        String currentItem = colorList.get(which);

                        //Notify the current action
                        Toast.makeText(MainActivity.this, currentItem +" -> "+ isChecked, Toast.LENGTH_SHORT).show();
                    }
                });

                //Set positive button
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //mTextV.setText("Interested...\n");

                        for (int i=0; i<checkedCategoryArray.length; i++){
                            boolean checked = checkedCategoryArray[i];
                            if (checked) {
                                //mOutputAlert.setText(mOutputAlert.getText() + colorList.get(i) + "\n");
                                mOutputAlert.setText(mOutputAlert.getText() + colorList.get(i) + " ,  ");
                            }
                        }
                    }
                });

                //Set negative button
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Discarded", Toast.LENGTH_SHORT).show();
                    }
                });

                //Create final results
                AlertDialog dialog = builder.create();

                //Display alert
                dialog.show();
            }
        });
//        ------------------------------------------------------------------------------------------

        //    ------------------------------------- For Save Button ---------------------------------------
        mSaveBtn = findViewById(R.id.saveBtn);
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Button implementation codes goes here
                Toast.makeText(MainActivity.this, "User Successfully Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }


//    ------------------------------------- For date picker---------------------------------------

    //Method to set selected date in the text field
    private void updateLabel(){
        String myFormat = "yyyy - MM - dd";         //Displaying format
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        b_day.setText(sdf.format(myCalendar.getTime()));    //Display
    }
//    ----------------------------------------------------------------------------------------------

}