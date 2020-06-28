package com.example.androiddata.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.androiddata.R
import com.example.androiddata.model.Monster
import com.example.androiddata.shared.SharedViewModel

class MainFragment : Fragment() ,RecyclerAdapter.ItemClickListner{

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: SharedViewModel
    private lateinit var swipelayout: SwipeRefreshLayout
    private lateinit var navcontroler: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view= inflater.inflate(R.layout.main_fragment,
            container, false)
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

        recyclerView=view.findViewById(R.id.recyclerView)
        navcontroler=Navigation.findNavController(
            requireActivity(),R.id.nav_host
        )

        swipelayout = view.findViewById(R.id.swiper)
        swipelayout.setOnRefreshListener {
            viewModel.refreshData()
        }


        viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)
        viewModel.monsterData.observe(this, Observer {

            val adapter =RecyclerAdapter(requireContext(),it,this)
            recyclerView.adapter=adapter
            swipelayout.isRefreshing=false

        })

        return view
    }


    override fun onItemClick(monster: Monster) {
        viewModel.selectedMonster.value=monster
       navcontroler.navigate(R.id.action_nav_detail)
    }

}
