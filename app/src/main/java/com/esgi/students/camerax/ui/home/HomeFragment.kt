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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.esgi.students.camerax.R
import com.esgi.students.camerax.bo.Recipe
import com.esgi.students.camerax.databinding.FragmentHomeBinding
import com.esgi.students.camerax.ui._adapters.RecipeAdapter
import org.json.JSONException

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val recipesList: ArrayList<Recipe> = ArrayList()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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
        recipesRecyclerView.layoutManager = LinearLayoutManager(activity)

        val adapter = RecipeAdapter()
        recipesRecyclerView.adapter = adapter

//        homeViewModel.recipes.observe(viewLifecycleOwner) {
//            textView.text = it.toString()
//        }

        homeViewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            adapter.submitList(recipes)
        }

        val itemCount = minOf(2, adapter.itemCount) // Maximum 3 items
        val firstVisibleView = recipesRecyclerView.getChildAt(0)
        val itemHeight = firstVisibleView?.height ?: 0 // Height of the first visible item

        // Calculate the total height
        val totalHeight = itemCount * itemHeight

        // Set the calculated height to the RecyclerView
        recipesRecyclerView.layoutParams.height = totalHeight
        recipesRecyclerView.requestLayout()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}