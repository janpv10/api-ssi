package com.example.wallet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btn1: ImageButton = findViewById(R.id.btncred)
        btn1.setOnClickListener {
            val intent = Intent(this, CredentialsActivity::class.java)
            startActivity(intent)
        }
        val btn2: ImageButton = findViewById(R.id.btntrans)
        btn2.setOnClickListener {
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent)
        }
        val btn3: ImageButton = findViewById(R.id.btnpend)
        btn3.setOnClickListener {
            val intent = Intent(this, PendingActivity::class.java)
            startActivity(intent)
        }
        val btn4: FloatingActionButton = findViewById(R.id.floatingActionButton4)
        btn4.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btn5: FloatingActionButton = findViewById(R.id.floatingActionButton5)
        btn5.setOnClickListener{
            val intent = Intent(this,QrActivity::class.java)
            startActivity(intent)
        }
    }
}