package com.zhengsr.viewpagerhelper.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhengsr.viewpagerhelper.R;

public class TabActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_page);
    }

    /**
     * 三角形指示器
     * @param view
     */
    public void tri(View view) {
        startActivity(new Intent(this,TriTabActivity.class));
    }

    /**
     * 条状指示器
     * @param view
     */
    public void rect(View view) {
        startActivity(new Intent(this,RectTabActivity.class));
    }

    /**
     * 颜色指示器
     * @param view
     */
    public void color(View view) {
        startActivity(new Intent(this,ColorTabActivity.class));
    }



}
