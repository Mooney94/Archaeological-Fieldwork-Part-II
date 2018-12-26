package org.wit.hillfort.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.intentFor
import org.wit.hillfort.R
import java.lang.Exception

class SplashView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val background = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(5000)

                    startActivityForResult(intentFor<UserWelcomeView>(),0)
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }


}