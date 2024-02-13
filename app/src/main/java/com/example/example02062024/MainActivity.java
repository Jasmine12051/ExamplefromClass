package com.example.example02062024;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.example02062024.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final int CHAIN_LENGTH = 6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initializeLayout();

        TextView tv = binding.layout.findViewWithTag("textView0");
        tv.setText("FIRST!");
    }

    private void initializeLayout() {

        ConstraintLayout layout = binding.layout;

        int[] viewIds = new int[CHAIN_LENGTH];

        for(int i =0; i< CHAIN_LENGTH; ++i){

            int id = View.generateViewId();
            TextView tv = new TextView(this);
            tv.setId(id);
            tv.setTag("textView" + i);
            tv.setText(getString(R.string.textview_label) + i);
            tv.setTextSize(24);
            layout.addView(tv);
            viewIds[i] = id;
        }

        ConstraintSet set = new ConstraintSet();
        set.clone(layout);

        for(int id : viewIds){
            set.connect(id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
            set.connect(id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
        }

        set.createVerticalChain(ConstraintSet.PARENT_ID, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, viewIds, null, ConstraintSet.CHAIN_PACKED);

        set.applyTo(layout);
    }
}