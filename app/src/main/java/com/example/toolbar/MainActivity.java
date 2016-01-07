package com.example.toolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.toolbar.animate_utils.AnimateConstruction;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private final String EXTRA_EXPANDED = "EXTRA_EXPANDED";

    private RecyclerView mRecyclerView;
    private SimpleCardViewAdapter mAdapter;

    private boolean isExpanded = false;

    private ImageView arrow;
    private View background;
    private RelativeLayout datePickerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        arrow = (ImageView) findViewById(R.id.date_picker_arrow);
        background = findViewById(R.id.dialog_background);
        datePickerButton = (RelativeLayout) findViewById(R.id.date_picker_button);

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimateConstruction.create()
                        .translateFromY(isExpanded ? 0 : -mRecyclerView.getHeight(), mRecyclerView)
                        .translateToY(isExpanded ? -mRecyclerView.getHeight() : 0, mRecyclerView)
                        .visibilityFrom(View.VISIBLE, mRecyclerView, background)
                        .visibilityTo(isExpanded ? View.INVISIBLE : View.VISIBLE, mRecyclerView, background)
                        .rotateBy(180, arrow)
                        .alphaFrom(isExpanded ? 0.6f : 0, background)
                        .alphaTo(isExpanded ? 0 : 0.6f, background)
                        .enabledFrom(false, datePickerButton)
                        .enabledTo(true, datePickerButton)
                        .duration(250)
                        .animate();
                isExpanded = !isExpanded;
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setVisibility(View.INVISIBLE);

        mAdapter = new SimpleCardViewAdapter(Arrays.asList(
                new CardViewData("Sample Title Item 0", "Description of Item 0", R.drawable.image_0),
                new CardViewData("Sample Title Item 0", "Description of Item 0", R.drawable.image_0)));

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        if (state.containsKey(EXTRA_EXPANDED)) {
            isExpanded = state.getBoolean(EXTRA_EXPANDED);
            AnimateConstruction.create()
                    .translateFromY(!isExpanded ? -mRecyclerView.getHeight() : 0, mRecyclerView)
                    .visibilityFrom(!isExpanded ? View.INVISIBLE : View.VISIBLE, mRecyclerView, background)
                    .rotateFrom(!isExpanded ? 0 : 180, arrow)
                    .alphaFrom(!isExpanded ? 0 : 0.6f, background)
                    .animate();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        state.putBoolean(EXTRA_EXPANDED, isExpanded);
        super.onSaveInstanceState(state);
    }
}
