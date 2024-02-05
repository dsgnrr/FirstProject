package com.andr.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("NO!");

        Button but1 = findViewById(R.id.button1);
        Button but2 = findViewById(R.id.button2);
        Button but3 = findViewById(R.id.button3);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                but1.setWidth(but1.getWidth() + 10);
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            int clickCount = 0;

            @Override
            public void onClick(View v) {
                if (clickCount < 20) {
                    clickCount++;
                    but2.setText(String.format("%d", clickCount));
                } else {
                    but2.setEnabled(false);
                    Toast.makeText(MainActivity.this, "Догрався", Toast.LENGTH_SHORT).show();
                }
            }
        });

        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date nextLesson = null;
                try {
                    nextLesson = sdf.parse("2024-02-06 08:50:00");
                } catch (ParseException ignored) {
                    Toast.makeText(MainActivity.this, ignored.getMessage(), Toast.LENGTH_SHORT).show();
                }
                if (nextLesson != null) {
                    long msResult = nextLesson.getTime() - new Date().getTime();
                    String result = TimeUnit.MILLISECONDS.toDays(msResult) + " днів "
                            + TimeUnit.MILLISECONDS.toHours(msResult) % 24 + " годин "
                            + TimeUnit.MILLISECONDS.toMinutes(msResult) % 60 + " хвилин "
                            + TimeUnit.MILLISECONDS.toSeconds(msResult) % 60 + " секунд ";
                    setTitle(result);
                } else {
                    setTitle("EROR!");
                }
            }
        });
    }

}