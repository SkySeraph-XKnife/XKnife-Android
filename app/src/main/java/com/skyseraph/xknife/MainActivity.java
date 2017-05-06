package com.skyseraph.xknife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.skyseraph.xknife.c13_3_1.RxDemoActivity;
import com.skyseraph.xknife.c13_3_2.RxHttpDemoActivity;
import com.skyseraph.xknife.lib.module.log.L;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The Btn 1.
     */
    @BindView(R.id.btn1)
    Button btn1;

    /**
     * The Btn 2.
     */
    @BindView(R.id.btn2)
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        L.w(">> AspectTest onCreate");
        ButterKnife.bind(this);
    }

    /**
     * On button click.
     *
     * @param view the view
     */
    @OnClick({R.id.btn1, R.id.btn2})
    void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                L.d(">> onClick 1");
                Toast.makeText(this, "Button Click!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, RxDemoActivity.class));
                break;
            case R.id.btn2:
                L.d(">> onClick 2");
                startActivity(new Intent(this, RxHttpDemoActivity.class));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.w(">> AspectTest onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.w(">> AspectTest onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        L.w(">> AspectTest onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.w(">> AspectTest onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.w(">> AspectTest onDestroy");
    }
}
