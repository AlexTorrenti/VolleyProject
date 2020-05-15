package com.imagina.volleyproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "https://swapi.dev/api/people/1/"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    val gson = Gson()
                    val person = gson.fromJson(response.toString(), Person::class.java)
                    textResult.append("\n" + person.name)
                },
                Response.ErrorListener { error ->
                    Log.e("VOLLEY_ERROR", error.toString())
                }
        )

        bLoad.setOnClickListener{
            RequestMaker.getInstance(this).addToRequestQueue(jsonObjectRequest)
        }
    }
}
