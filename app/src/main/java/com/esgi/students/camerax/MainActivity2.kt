package com.esgi.students.camerax

import android.animation.ObjectAnimator
import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

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

        splashScreen.setOnExitAnimationListener { splashScreenView ->
            // Create your custom animation.
            val slideUp = ObjectAnimator.ofFloat(
                splashScreenView,
                View.TRANSLATION_Y,
                0f,
                -splashScreenView.height.toFloat()
            )
            slideUp.interpolator = AnticipateInterpolator()
            slideUp.duration = 200L

            // Call SplashScreenView.remove at the end of your custom animation.
            slideUp.doOnEnd { splashScreenView.remove() }

            // Run your animation.
            slideUp.start()
        }


        getData()
    }
}