package com.pbaileyapps.gesturedemo

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GestureDetectorCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity(){
    lateinit var gestureDetector: GestureDetectorCompat
    lateinit var questionView: TextView
    lateinit var answerView: TextView
    lateinit var scoreView: TextView
    lateinit var increment:FloatingActionButton
    lateinit var decrement:FloatingActionButton
    lateinit var view:FrameLayout
    var score = 0
    var answerValue = 0
    var firstNum = 0
    var secondNum = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view = findViewById<FrameLayout>(R.id.root_layout)
        questionView = findViewById(R.id.question)
        answerView = findViewById(R.id.answer)
        scoreView = findViewById(R.id.score)
        increment = findViewById(R.id.increment)
        decrement = findViewById(R.id.decrement)
        increment.setOnClickListener({
            answerValue++
            answerView.setText(answerValue.toString())
        })
        decrement.setOnClickListener({
            answerValue--
            answerView.setText(answerValue.toString())
        })
        generateProblem()

        gestureDetector = GestureDetectorCompat(this,MyGestureListener())

    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        return true
    }
    fun generateProblem(){
        firstNum = Random().nextInt(10)
        secondNum = Random().nextInt(10)
        var problem = firstNum.toString() + " + " + secondNum.toString()
        questionView.setText(problem)
    }

    /*
    These functions from the GestureDetector.OnGestureListener must be implemented
     */
    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {


        override fun onFling(
            event1: MotionEvent,
            event2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {

            firstNum = Random().nextInt(10)
            secondNum = Random().nextInt(10)
            view.setBackgroundColor(Random().nextInt())
            var question = firstNum.toString() + " + " + secondNum.toString()
            questionView.setText(question)
            Toast.makeText(applicationContext,"Skipped",Toast.LENGTH_SHORT).show()
            return true
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            if (answerValue == firstNum+secondNum){
                score++
                scoreView.setText("Score: ${score.toString()}")
                generateProblem()
            }
            else{
                generateProblem()
                Toast.makeText(applicationContext,"Incorrect",Toast.LENGTH_SHORT).show()
            }
            return true
        }
    }
}