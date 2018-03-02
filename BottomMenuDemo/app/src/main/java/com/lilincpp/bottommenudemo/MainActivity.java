package com.lilincpp.bottommenudemo;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lilincpp.bottommenu.BottomMenu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomMenu.Builder builder = new BottomMenu.Builder(v);
                builder.linearLayout()
                        .addItem("你好", ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_launcher_background))
                        .addItem("你好", ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_launcher_background))
                        .addItem("你好", ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_launcher_background))
                        .addItem("你好", ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_launcher_background))
                        .addItem("你好", ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_launcher_background));
                builder.create().show();
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomMenu.Builder builder = new BottomMenu.Builder(v);
                builder.gridLayout()
                        .addItem("你好", ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_launcher_background))
                        .addItem("你好", ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_launcher_background))
                        .addItem("你好", ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_launcher_background))
                        .addItem("你好", ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_launcher_background))
                        .addItem("你好", ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_launcher_background));
                builder.create().show();
            }
        });
    }
}
