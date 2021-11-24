package com.example.qrscanner

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection



class MainActivity2 : AppCompatActivity() {

    private lateinit var tvIsConnected: TextView
    private lateinit var etTitle: EditText
    private lateinit var tvResult: TextView
    private lateinit var post: RadioButton
    private lateinit var get: RadioButton
    private lateinit var button: Button
    lateinit var myArray: Array<String>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        tvIsConnected = findViewById<TextView>(R.id.tvIsConnected)
        etTitle = findViewById<EditText>(R.id.etTitle)
        tvResult = findViewById<TextView>(R.id.tvResult)
        button = findViewById(R.id.btnSend)

        checkNetworkConnection()

        val queue = Volley.newRequestQueue(this)
        var url = intent.getStringExtra("key") + "/get"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val str = response.toString()
                Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
                myArray = str.split('"').toTypedArray()
                url = url.replace("/get", myArray[3])
            },
            { Toast.makeText(this, "Not Connected!", Toast.LENGTH_SHORT).show()})

        // Add the request to the RequestQueue.
        queue.add(stringRequest)

        button.setOnClickListener {
            if (checkNetworkConnection()){
                Toast.makeText(this, "Posted", Toast.LENGTH_SHORT).show()
                lifecycleScope.launch {
                    val myUrl = url + "?" + myArray[7] + "=" + etTitle.text.toString()
                    val result = httpPost(myUrl)

                }
            }
            else
                Toast.makeText(this, "Not Connected!", Toast.LENGTH_SHORT).show()
        }
    }

    @Throws(IOException::class, JSONException::class)
    private suspend fun httpPost(myUrl: String): String {

        val result = withContext(Dispatchers.IO) {
            val url = URL(myUrl)
            // 1. create HttpURLConnection
            val conn = url.openConnection() as HttpsURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8")

            // 2. build JSON object
            val jsonObject = buildJsonObject()

            // 3. add JSON content to POST request body
            setPostRequestContent(conn, jsonObject)

            // 4. make POST request to the given URL
            conn.connect()

            // 5. return response message
            conn.responseMessage + ""


        }
        return result
    }

    @SuppressLint("SetTextI18n")
    private fun checkNetworkConnection(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connMgr.activeNetworkInfo
        val isConnected: Boolean = networkInfo?.isConnected ?: false
        if (networkInfo != null && isConnected) {
            // show "Connected" & type of network "WIFI or MOBILE"
            tvIsConnected.text = "Connected " + networkInfo.typeName
            // change background color to red
            tvIsConnected.setBackgroundColor(-0x8333da)
        } else {
            // show "Not Connected"
            tvIsConnected.text = "Not Connected"
            // change background color to green
            tvIsConnected.setBackgroundColor(-0x10000)
        }
        return isConnected
    }

    @Throws(JSONException::class)
    private fun buildJsonObject(): JSONObject {

        val jsonObject = JSONObject()
        jsonObject.accumulate("post", etTitle.text.toString())
        print(jsonObject.toString())
        return jsonObject
    }

    @Throws(IOException::class)
    private fun setPostRequestContent(conn: HttpURLConnection, jsonObject: JSONObject) {

        val os = conn.outputStream
        val writer = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
        writer.write(jsonObject.toString())
        Log.i(MainActivity::class.java.toString(), jsonObject.toString())
        writer.flush()
        writer.close()
        os.close()
    }
}