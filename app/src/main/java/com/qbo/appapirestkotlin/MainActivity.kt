package com.qbo.appapirestkotlin

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //Declaramos la cola de peticiones.
    private lateinit var mQueue: RequestQueue


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mQueue = Volley.newRequestQueue(this);
        consumirApiRest(applicationContext)
    }

    fun consumirApiRest(context: Context){
        val listdatos: ArrayList<String> = ArrayList()
        val urlwslista = "https://jsonplaceholder.typicode.com/albums"
        val request = JsonArrayRequest(
            Request.Method.GET,
            urlwslista,
            null,
            { response->
                for(i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    listdatos.add(item.get("title").toString())
                }
                lvdatos.adapter = ArrayAdapter<String>(context,
                    android.R.layout.simple_list_item_1,
                    listdatos)
            },{
                Log.i("LISTAR", it.toString())
            })
        mQueue.add(request)
    }
}