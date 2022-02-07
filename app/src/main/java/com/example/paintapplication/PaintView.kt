package com.example.paintapplication

import android.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.paintapplication.MainActivity.Companion.paintBrush
import com.example.paintapplication.MainActivity.Companion.path
import android.graphics.Path
import android.graphics.drawable.ShapeDrawable


class PaintView : View {

    var params:ViewGroup.LayoutParams?=null

    lateinit var shapeDrawable: ShapeDrawable



    companion object{
        var pathList=ArrayList<Path>()
        var colorList=ArrayList<Int>()
        var currentBruch= Color.BLACK

        var circleX :Float = 5.0f
        var circleY :Float = 10.0f
    }

    constructor(context: Context) : this(context, null){
        initBrush()
    }
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0){
        initBrush()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initBrush()
    }

    //initial Brush
    private fun initBrush(){
        //smoothes out the edges of what is drawn without affecting the shape.
        paintBrush.isAntiAlias=true
        paintBrush.color= currentBruch
        paintBrush.style= Paint.Style.STROKE
       // specifies how lines and curve segments join on a stroked path
        paintBrush.strokeJoin=Paint.Join.ROUND
        //widht of brush 8 unit in float
        paintBrush.strokeWidth=8f

        params=ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)

    }
    //get the moment of finger on screen
    override fun onTouchEvent(event: MotionEvent): Boolean {
        var x = event.x
        var y = event.y
         circleX=event.x
         circleY=event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
                pathList.add(path)
                colorList.add(currentBruch)

            }

            else-> return false

        }
        postInvalidate()
        return false


    }
     //take array of path and and draw on screen
    override fun onDraw(canvas: Canvas) {
         when(MainActivity.check){
             1-> {

                 for (i in pathList.indices) {
                     paintBrush.setColor(colorList[i])
                     canvas.drawPath(pathList[i], paintBrush)
                     //Call onDraw more than one time to redraw te shape
                     invalidate()
                 }
             }
             2 ->{
               //  canvas.drawCircle(circleX,circleY,100f, paintBrush)
                 //shapeDrawable.draw(canvas)
                 //path=Path()

             }
             3 ->{
                // canvas.drawRec(circleX,circleY,50f, paintBrush)

             }



             }

         }
     }


/*fun onDrawCircle(canvas : Canvas) {
    val width = canvas.width.toFloat()
    val height = canvas.height.toFloat()

}*/
        
 /*fun onDrawCircle() {
     var canvas : Canvas
     canvas.drawCircle(circleX,circleY,50f, paintBrush)
     invalidate()
 }*/
   /*  var width=getWidth()
     var hight=getHeight()
     var radius=100f
         paintBrush.color= currentBruch
         paintBrush.style= Paint.Style.FILL
         canvas.drawPaint(paintBrush)
         paintBrush.setColor(Color.parseColor("#CD5C5C"))
         canvas.drawCircle(x/2,y/2,radius, paintBrush)
*/




