package xys.com.pathart.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import xys.com.pathart.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pathPainter(View view) {
        startActivity(new Intent(this, PathPainterActivity.class));
    }

    public void pathPainterEffect(View view) {
        startActivity(new Intent(this, PathPainterEffectActivity.class));
    }

    public void pathPainterTan(View view) {
        startActivity(new Intent(this, PathTanActivity.class));
    }
}
