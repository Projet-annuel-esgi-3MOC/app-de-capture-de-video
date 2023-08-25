package com.esgi.students.camerax

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.esgi.students.camerax.bo.Recipe
import com.esgi.students.camerax.databinding.ActivityMain2Binding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONException


class MainActivity2 : AppCompatActivity() {

    private val recipesList: ArrayList<Recipe> = ArrayList()
    private val mList: RecyclerView? = null
    private val adapter: RecyclerView.Adapter<*>? = null

    private fun getData() {
        val url = "http://localhost:3000/projects"

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.show()
        val jsonArrayRequest = JsonArrayRequest(url,
            {  response ->
                for (i in 0 until response.length()) {
                try {
                    val jsonObject = response.getJSONObject(i)
                    val recipe = Recipe()
                    recipe.name = (jsonObject.getString("name"))
                    recipe.description = (jsonObject.getString("description"))
                    recipesList.add(recipe)
                } catch (e: JSONException) {
                    e.printStackTrace()
                    progressDialog.dismiss()
                }
            }
            },
            {
            fun onErrorResponse(error: VolleyError) {
                Log.e("Volley", error.toString())
                progressDialog.dismiss()
            }
        })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(jsonArrayRequest)
    }

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main2)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_camera, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        getData()
    }
}