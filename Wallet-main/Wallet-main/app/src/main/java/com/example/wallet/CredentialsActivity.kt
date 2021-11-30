package com.example.wallet

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CredentialsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credentials)

        val btn2: ImageButton = findViewById(R.id.btnhome)
        btn2.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        val btn3: ImageButton = findViewById(R.id.btnpend)
        btn3.setOnClickListener {
            val intent = Intent(this, PendingActivity::class.java)
            startActivity(intent)
        }
    }
}