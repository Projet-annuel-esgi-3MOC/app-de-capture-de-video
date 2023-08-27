package com.esgi.students.camerax.ui.home

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.esgi.students.camerax.bo.Recipe
import com.esgi.students.camerax.databinding.FragmentHomeBinding
import org.json.JSONException

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val recipesList: ArrayList<Recipe> = ArrayList()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private fun getData() {

        val progressDialog = ProgressDialog(activity)
        progressDialog.setMessage("Loading...")
        progressDialog.show()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        val recipesRecyclerView: RecyclerView = binding.recipesList

        homeViewModel.recipes.observe(viewLifecycleOwner) {
            textView.text = it.toString()
            //recipesRecyclerView.
        }

        homeViewModel.fetchRecipes()

        getData()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}