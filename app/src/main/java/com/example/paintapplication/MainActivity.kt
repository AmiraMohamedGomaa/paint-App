package com.example.paintapplication

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.paintapplication.PaintView.Companion.circleX
import com.example.paintapplication.PaintView.Companion.circleY
import com.example.paintapplication.PaintView.Companion.colorList
import com.example.paintapplication.PaintView.Companion.currentBruch
import com.example.paintapplication.PaintView.Companion.pathList


class MainActivity : AppCompatActivity() {

    companion object{
        var path=Path()
        var paintBrush=Paint()
        var check=0

        val bitmap: Bitmap = Bitmap.createBitmap(700, 1000, Bitmap.Config.ARGB_8888)
        val canvas: Canvas = Canvas(bitmap)
        lateinit var shapeDrawable: ShapeDrawable



    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()



        val pencil=findViewById<ImageButton>(R.id.pencil);
        val arrow=findViewById<ImageButton>(R.id.arrow);
        val circle=findViewById<ImageButton>(R.id.circle);
        val rectangle=findViewById<ImageButton>(R.id.rectangle);
        val palet=findViewById<ImageButton>(R.id.palet);
        val colorLayout=findViewById<LinearLayout>(R.id.color_layout)


        val redColor=findViewById<Button>(R.id.redBtn);
        val greenColor=findViewById<Button>(R.id.greenBtn)
        val blueColor=findViewById<Button>(R.id.bluetn);
        val blackColor=findViewById<Button>(R.id.blackBtn)
        val whiteColor=findViewById<Button>(R.id.whiteBtn)

        val background=findViewById<PaintView>(R.id.paint)




        pencil.setOnClickListener {
           path.reset()
            check=1
        }

        arrow.setOnClickListener {
         background.background=(getDrawable(R.drawable.arrow_shape))
        }
        circle.setOnClickListener {
            path.reset()
            check=2
            createcircle(circleX,circleY)

        }
        rectangle.setOnClickListener {
           check=3
            createRectangle(circleX, circleY)

        }
        palet.setOnClickListener {

            colorLayout.visibility=View.VISIBLE

            redColor.setOnClickListener {
               paintBrush.color= Color.RED
               currentColor(paintBrush.color)

           }
            greenColor.setOnClickListener {
             paintBrush.color=Color.GREEN
                currentColor(paintBrush.color)
            }
            blueColor.setOnClickListener {
                paintBrush.color=Color.BLUE
                currentColor(paintBrush.color)
            }
            blackColor.setOnClickListener {
                paintBrush.color=Color.BLACK
                currentColor(paintBrush.color)
            }
            whiteColor.setOnClickListener {
                pathList.clear()
                colorList.clear()
                path.reset()
            }


        }


    }

    private fun currentColor(color:Int){
        currentBruch=color
        path=Path()
    }


    fun createcircle( x:Float, y:Float){
        // oval positions
        var left = (0+x).toInt()
        var top = 100
        var right = (100+y).toInt()
        var bottom = 300

        // draw oval shape to canvas
        shapeDrawable = ShapeDrawable(OvalShape())
        shapeDrawable.setBounds( left, top, right, bottom)
        shapeDrawable.paint.style=Paint.Style.STROKE
        shapeDrawable.getPaint().setColor(currentBruch)
        shapeDrawable.draw(canvas)

        // now bitmap holds the updated pixels

        // set bitmap as background to ImageView
        val background=findViewById<PaintView>(R.id.paint)
        background.background = BitmapDrawable(getResources(), bitmap)
    }

    fun createRectangle( x:Float, y:Float){
        // rectangle positions
        var left = (50+x).toInt()
        var top = 100
        var right = (200+y).toInt()
        var bottom = 200


        // draw rectangle shape to canvas
        shapeDrawable = ShapeDrawable(RectShape())
        shapeDrawable.setBounds( left, top, right, bottom)
        shapeDrawable.paint.style=Paint.Style.STROKE
        shapeDrawable.getPaint().setColor(currentBruch)
        shapeDrawable.draw(canvas)
        // set bitmap as background to ImageView
        val background=findViewById<PaintView>(R.id.paint)
        background.background = BitmapDrawable(getResources(), bitmap)
    }
}