package com.example.basicapp

import android.content.ContentValues
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import android.content.ContentValues.TAG

import android.util.Log;
import android.widget.TextView
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

class MainActivity : AppCompatActivity() {
    //private RecyclerView.Adapter adapter;
    private val itemsList = ArrayList<String>()
    private lateinit var customAdapter: CustomAdapter
   // private var mRequestQueue: RequestQueue? = null
   // private var mStringRequest: StringRequest? = null
    //val url = "https://run.mocky.io/v3/85cf9aaf-aa4f-41bf-b10c-308f032f7ccc"

    // ...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "RecyclerView - www.tutorialkart.com"

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        customAdapter = CustomAdapter(itemsList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter
        prepareItems()
        getData()
    }

    private fun prepareItems() {
        itemsList.add("Item 1")
        itemsList.add("Item 2")
        itemsList.add("Item 3")
        itemsList.add("Item 4")
        itemsList.add("Item 5")
        itemsList.add("Item 6")
        customAdapter.notifyDataSetChanged()
    }
    private fun getData() {
        // RequestQueue initialized
        val textView = findViewById<TextView>(R.id.textView)
        val mRequestQueue = Volley.newRequestQueue(this)
        val url = "http://localhost:8080/api/categories/5"
        // String Request initialized
//        val mStringRequest = StringRequest(Request.Method.GET, url, Response.Listener<String?>() {
//            // display the response on screen
//            fun onResponse(response: String) {
//                Toast.makeText(applicationContext, "Response :$response", Toast.LENGTH_LONG)
//                    .show()
//            }
//        }, object : Response.ErrorListener{
//            Log.d("error",it.localizedMessage)
//        })

        val mStringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                textView.text = "Response is: ${response.toString()}"
            },
            Response.ErrorListener { textView.text = "That didn't work!" })

// Add the request to the RequestQueue.
        //queue.add(stringRequest)
        mRequestQueue.add(mStringRequest)
    }
}