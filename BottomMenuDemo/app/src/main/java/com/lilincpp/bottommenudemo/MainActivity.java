package com.lilincpp.bottommenudemo;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lilincpp.bottommenu.BottomMenu;
import com.lilincpp.bottommenu.IMenu;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

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
                BottomMenu menu = builder.create();
                menu.addOnDismissListener(new IMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(IMenu menu) {
                        Log.e(TAG, "onDismiss: ");
                    }
                });

                menu.addOnShowListener(new IMenu.OnShowListener() {
                    @Override
                    public void onShow(IMenu menu) {
                        Log.e(TAG, "onShow: ");
                    }
                });

                menu.addOnItemClickListener(new IMenu.OnItemClickListener() {
                    @Override
                    public void onItemClick(IMenu menu, int position) {
                        Log.e(TAG, "onItemClick: ");
                        Toast.makeText(MainActivity.this, "点击 position=" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                menu.show();
            }
        });
    }
}
