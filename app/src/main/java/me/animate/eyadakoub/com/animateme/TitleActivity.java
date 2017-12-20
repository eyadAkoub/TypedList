package me.animate.eyadakoub.com.animateme;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class TitleActivity extends AppCompatActivity {


    TypedArray Colours400;
    View background_color;

    int currentPosition = 0;

    EditText title_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        Colours400 = ClipUtil.get400Color("400" , this);

        background_color = findViewById(R.id.background_color);
        title_value = findViewById(R.id.title_value);

        onClick();
    }

    private void onClick() {
        background_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPosition < Colours400.length() - 1)
                    view.setBackgroundColor(Colours400.getColor(++currentPosition, getResources().getColor(R.color.blue500)));
                else
                    view.setBackgroundColor(Colours400.getColor(currentPosition = 0, getResources().getColor(R.color.blue500)));
            }
        });
    }

    public void nextActive(View view) {
        String title = title_value.getText().toString();
        if(TextUtils.isEmpty(title)){
            Toast.makeText(this , "please put a title" , Toast.LENGTH_LONG).show();
        }else {
            int color_id = Colours400.getColor(currentPosition, getResources().getColor(R.color.blue500));
            VideoAttributes.getAttributes().setGroupTwo(title, color_id);
            //TODO here you should pun an intent
            Intent intent = new Intent(this , Clip_Creation_Activity.class);
            startActivity(intent);
        }
    }
}
