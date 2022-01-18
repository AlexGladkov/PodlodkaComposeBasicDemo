package ru.agladkov.podlodka_compose_basics_demo.old_android.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.agladkov.podlodka_compose_basics_demo.R
import ru.agladkov.podlodka_compose_basics_demo.old_android.master.MasterData

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)
    }

    private val adapter = DetailAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.detailListView)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner, {
            adapter.setNewData(it)
        })
        viewModel.fetchDetailInfo("Title")
    }
}