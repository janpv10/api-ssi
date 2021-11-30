package com.example.wallet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PendingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending)
        val btn1: ImageButton = findViewById(R.id.btncred)
        btn1.setOnClickListener {
            val intent = Intent(this, CredentialsActivity::class.java)
            startActivity(intent)
        }
        val btn2: ImageButton = findViewById(R.id.btnhome)
        btn2.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }
}