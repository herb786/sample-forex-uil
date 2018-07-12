package com.hacaller.androidplayground2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hacaller.androidplayground2.R;
import com.hacaller.androidplayground2.fragments.PlaygroundTutorialPagerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityBeta extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_launch) Button btnLaunch;
    @BindView(R.id.btn_next) Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_beta);
        ButterKnife.bind(this);

        btnLaunch.setOnClickListener(this);
        btnNext.setOnClickListener(new NextListener());

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnLaunch.getId()){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, new PlaygroundTutorialPagerFragment());
            fragmentTransaction.commit();
        }
    }

    private class NextListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), ActivityDelta.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
    }

}
