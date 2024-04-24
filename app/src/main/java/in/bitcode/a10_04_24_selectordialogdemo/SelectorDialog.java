package in.bitcode.a10_04_24_selectordialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SelectorDialog extends Dialog {

    private TextView txtTitle;
    private Button btnSet;
    private LinearLayout mainContainer;

    private String [] options;
    private String title;
    private Context context;
    private ArrayList<CheckBox> checkedOptions = new ArrayList<CheckBox>();

    public interface OnOptionsSetListener{
        void onOptionsSet(ArrayList<String> selectedOptions);
    }

    private OnOptionsSetListener onOptionsSetListener;

    public void setOnOptionListener(OnOptionsSetListener onOptionsSetListener){
        this.onOptionsSetListener = onOptionsSetListener;
    }

    public SelectorDialog(@NonNull Context context, String [] options) {
        super(context);
        this.context = context;
        this.options = options;
        setContentView(R.layout.selector_dialog);

        initViews();
        initListeners();
    }

    private void initViews(){
       txtTitle = findViewById(R.id.txtTitle);
       btnSet = findViewById(R.id.btnSet);
       mainContainer = findViewById(R.id.mainContainer);

       for(int i = 0;i < options.length;i++){
           CheckBox checkOption = new CheckBox(context);

           ViewGroup.LayoutParams layoutParamsForCheckBox = new ViewGroup.LayoutParams(
                   ViewGroup.LayoutParams.WRAP_CONTENT,
                   ViewGroup.LayoutParams.WRAP_CONTENT
           );
           checkOption.setLayoutParams(layoutParamsForCheckBox);
           checkOption.setText(options[i]);
           checkedOptions.add(checkOption);
           mainContainer.addView(checkOption);
       }
    }

    private void initListeners(){
        btnSet.setOnClickListener(new BtnSetClickListener());
    }

    class BtnSetClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            ArrayList<String> selectedOptions = new ArrayList<String>();

                for (CheckBox checkBox : checkedOptions) {
                    if(checkBox.isChecked()) {
                        selectedOptions.add(checkBox.getText().toString());
                    }
                }
                onOptionsSetListener.onOptionsSet(selectedOptions);
                dismiss();
        }
    }

    public void setTitle(String title) {
        this.title = title;
        txtTitle.setText(title);
    }
}