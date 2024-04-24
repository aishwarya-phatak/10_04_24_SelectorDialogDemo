package in.bitcode.a10_04_24_selectordialogdemo;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends Activity {

    Button btnSelectedSkills, btnSelectedCities;
    TextView txtDisplay;
    String [] skills = {"C", "Cpp", "Java", "iOS", "Android"};
    String [] cities = {"Pune", "Nashik", "Mumbai", "Amravati", "Nagpur"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
    }

    private void initViews(){
        btnSelectedSkills = findViewById(R.id.btnSelectedSkills);
        btnSelectedCities = findViewById(R.id.btnSelectedCities);
        txtDisplay = findViewById(R.id.txtDisplay);
    }

    private void initListeners(){
        btnSelectedSkills.setOnClickListener(new BtnSelectedSkillsListener());
        //btnSelectedCities.setOnClickListener(new BtnSelectedCitiesListener());
    }

    class BtnSelectedSkillsListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            SelectorDialog selectorDialog = new SelectorDialog(MainActivity.this, skills);
            selectorDialog.setTitle("Skills");
            selectorDialog.setOnOptionListener(new MySkillsSelectedListener());
            selectorDialog.show();
        }
    }

    class MySkillsSelectedListener implements SelectorDialog.OnOptionsSetListener{
        @Override
        public void onOptionsSet(ArrayList<String> selectedOptions) {
                txtDisplay.setText("");
            for (String skills: selectedOptions) {
                txtDisplay.append(skills + "\n");
            }
        }
    }

//    class BtnSelectedCitiesListener implements View.OnClickListener{
//        @Override
//        public void onClick(View view) {
//
//        }
//    }
}