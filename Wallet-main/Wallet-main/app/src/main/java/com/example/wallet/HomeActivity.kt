package com.example.wallet

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONObject

class HomeActivity : AppCompatActivity() {

    private var credentials: JSONArray = generateJSON()

    private fun generateJSON(): JSONArray {

        val credential1 = JSONObject()
        credential1.put("id", "0")
        credential1.put("title", "Over 18")
        credential1.put("name", "over-18")
        credential1.put("picture", "images/pegi18.png")
        credential1.put("validity", "∞")
        credential1.put("format", "basic")
        credential1.put("signature", "0xC05F1284A4C04043379856AAB4B9210FAC7736B36AFD10FD245E5DD0199281E0")

        val credential2 = JSONObject()
        credential2.put("id", "1")
        credential2.put("title", "Municipality of birth")
        credential2.put("name", "birth-place")
        credential2.put("picture", "images/logo_barcelona.png")
        credential2.put("validity", "∞")
        credential2.put("format", "barcelona")
        credential2.put("signature", "0x4DACEFBEAD8925A84D852A3E8D477FE75CF17D741F5F2C8105AB8691B824B7A4")

        val credential3 = JSONObject()
        credential3.put("id", "2")
        credential3.put("title", "Municipality of residence")
        credential3.put("name", "residence-place")
        credential3.put("picture", "images/logo_esplugues.jpg")
        credential3.put("validity", "1 year")
        credential3.put("format", "esplugues")
        credential3.put("signature", "0xAD3F5AE64D386810312CB745BC998CE3084599724A19A6669346770CFE3F47ED")

        val credential4 = JSONObject()
        credential4.put("id", "3")
        credential4.put("title", "University Degree")
        credential4.put("name", "university-degree")
        credential4.put("picture", "images/logo_upc.png")
        credential4.put("validity", "∞")
        credential4.put("format", "Informatics Engineering")
        credential4.put("signature", "0x64E7FBEB002A309E1545C08676169228C7704DDD72F1820A34331203C97AB520")

        val jsonArray = JSONArray()
        jsonArray.put(credential1)
        jsonArray.put(credential2)
        jsonArray.put(credential3)
        jsonArray.put(credential4)

        return jsonArray
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (!intent.getStringExtra("key").isNullOrEmpty()) {
            val newFragment = QuadreDialeg(intent.getStringExtra("key").toString())
            newFragment.show(supportFragmentManager, "peticions")
        }

        val btn1: ImageButton = findViewById(R.id.btncred)
        btn1.setOnClickListener {
            val intent = Intent(this, CredentialsActivity::class.java)
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
            startActivity(intent.putExtra("key", credentials.toString()))
        }
    }
}