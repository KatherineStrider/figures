package com.example.kate.customizedui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textFigure;
    FigureView figureView;
    ImageButton btnRect;
    ImageButton btnCircle;
    ImageButton btnTriangle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        figureView.setTextFigure(textFigure);
    }

    private void initView(){

        textFigure = (TextView) findViewById(R.id.textFigure);
        figureView = (FigureView) findViewById(R.id.figureView);

        btnRect = (ImageButton) findViewById(R.id.btn0);
        btnCircle = (ImageButton) findViewById(R.id.btn1);
        btnTriangle = (ImageButton) findViewById(R.id.btn2);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){

                    case R.id.btn0:
                        figureView.setFigureType(0);
                        figureView.setTextFigure(textFigure);
                        break;

                    case R.id.btn1:
                        figureView.setFigureType(1);
                        figureView.setTextFigure(textFigure);
                        break;

                    case R.id.btn2:
                        figureView.setFigureType(2);
                        figureView.setTextFigure(textFigure);
                        break;

                    case R.id.figureView:
                        Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.animation);
                        figureView.setAnimation(anim);
                        figureView.startAnimation(anim);
                        break;

                    default:
                        figureView.setFigureType(0);
                        figureView.setTextFigure(textFigure);
                        break;
                }
            }
        };

        btnRect.setOnClickListener(onClickListener);
        btnCircle.setOnClickListener(onClickListener);
        btnTriangle.setOnClickListener(onClickListener);
        figureView.setOnClickListener(onClickListener);

    }

}
