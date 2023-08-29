package com.esgi.students.camerax.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.students.camerax.R
import com.esgi.students.camerax.bo.Challenge
import com.esgi.students.camerax.bo.Participation
import com.esgi.students.camerax.bo.Recipe
import com.esgi.students.camerax.databinding.FragmentHomeBinding
import com.esgi.students.camerax.services.BusinessResponse
import com.esgi.students.camerax.services.RetrofitInstance
import com.esgi.students.camerax.ui.camera.CameraFragmentArgs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val recipesList: ArrayList<Recipe> = ArrayList()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val apiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

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

        val challengesRecyclerView = binding.trainingList
        challengesRecyclerView.layoutManager = LinearLayoutManager(
            activity, LinearLayoutManager.HORIZONTAL, false
        )

        val challengesAdapter = ChallengesAdapter {
                challenge: Challenge ->

            apiScope.launch(Dispatchers.Main) {
                val res = performApiChallPartCall(challengeId = challenge._id)

                Log.i("toto", "create res $res");

                val action = HomeFragmentDirections.actionNavigationHomeToNavigationCamera(
                    challengeId = challenge._id
                )
                findNavController().navigate(action)
            }

        }
        challengesRecyclerView.adapter = challengesAdapter


        homeViewModel.challenges.observe(viewLifecycleOwner) { challenges ->
            challengesAdapter.submitList(challenges)
        }

        return root
    }

    private suspend fun performApiChallPartCall(challengeId: String): BusinessResponse<Participation> {
        return withContext(Dispatchers.IO) {
            RetrofitInstance.apiService.addChallengeParticipation(challengeId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onChallengeClick(challenge: Challenge) {
        Log.i("toto", challenge.toString());
    }
}