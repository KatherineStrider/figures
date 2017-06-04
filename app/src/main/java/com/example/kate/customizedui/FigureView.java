package com.example.kate.customizedui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Kate on 03.06.2017.
 */

public class FigureView extends View {

    private Paint paint;
    private Paint paintShadow;
    private Paint paintLine;
    private int radius;
    private int figureType;
    private final int padding = 32;
    private final int bias = 24;
    private final int line = 16;

    public FigureView(Context context) {
        super(context);
        paint = new Paint();
    }

    public FigureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FigureView);
        figureType = a.getInt(R.styleable.FigureView_figureType, 0);
        a.recycle();

        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.colorFigure));
        paintShadow = new Paint();
        paintShadow.setColor(getResources().getColor(R.color.colorFigureShadow));
        paintShadow.setAlpha(180);
        paintLine = new Paint();
        paintLine.setColor(getResources().getColor(R.color.colorFigureLine));
        paintLine.setAlpha(180);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int height = this.getHeight();
        int width = this.getWidth();

        switch (figureType){

            case 0:
                drawRectView(canvas, height, width);
                break;
            case 1:
                drawCircleView(canvas, height, width);
                break;
            case 2:
                drawTriangleView(canvas, height, width);
                break;
            default:

                break;
        }
    }

    private void drawCircleView(Canvas canvas, int height, int width){

        if(height>width){
            radius = width/2 - padding;
        }else {
            radius = height/2 - padding;
        }

        canvas.drawCircle(width/2 + bias, height/2 + bias, radius, paintShadow);
        canvas.drawCircle(width/2, height/2, radius, paintLine);
        canvas.drawCircle(width/2, height/2, radius - line, paint);
    }

    private void drawRectView(Canvas canvas, int height, int width){

        canvas.drawRect(padding + bias, padding + bias, width - padding + bias, height - padding + bias, paintShadow);
        canvas.drawRect(padding, padding, width - padding, height - padding, paintLine);
        canvas.drawRect(padding + line, padding + line, width - padding - line, height - padding - line, paint);
    }

    private void drawTriangleView(Canvas canvas, int height, int width){

        Point aS = new Point(width/2 + bias, padding + bias);
        Point bS = new Point(padding + bias, height-padding + bias);
        Point cS = new Point(width-padding + bias, height-padding + bias);

        Path pathS = new Path();
        pathS.setFillType(Path.FillType.EVEN_ODD);

        pathS.lineTo(aS.x, aS.y);
        pathS.lineTo(bS.x, bS.y);
        pathS.lineTo(cS.x, cS.y);
        pathS.lineTo(aS.x, aS.y);
        pathS.close();

        canvas.drawPath(pathS, paintShadow);

        Point a = new Point(width/2, padding);
        Point b = new Point(padding, height-padding);
        Point c = new Point(width-padding, height-padding);

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        path.lineTo(a.x, a.y);
        path.lineTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.lineTo(a.x, a.y);
        path.close();

        canvas.drawPath(path, paintLine);

        Point aL = new Point(width/2, padding + line + line);
        Point bL = new Point(padding + line + line, height-padding - line) ;
        Point cL = new Point(width-padding - line - line, height-padding - line);

        Path pathL = new Path();
        pathL.setFillType(Path.FillType.EVEN_ODD);

        pathL.lineTo(aL.x, aL.y);
        pathL.lineTo(bL.x, bL.y);
        pathL.lineTo(cL.x, cL.y);
        pathL.lineTo(aL.x, aL.y);
        pathL.close();

        canvas.drawPath(pathL, paint);
    }

    public int getFigureType() {
        return figureType;
    }

    public void setFigureType(int figureType) {
        this.figureType = figureType;
        invalidate();
    }

    public void setTextFigure(TextView textFigure){

        switch (this.getFigureType()){

            case 0:
                textFigure.setText("Прямоугольник");
                break;
            case 1:
                textFigure.setText("Круг");
                break;
            case 2:
                textFigure.setText("Треугольник");
                break;
            default:
                textFigure.setText("Прямоугольник");
                break;
        }
    }
}
