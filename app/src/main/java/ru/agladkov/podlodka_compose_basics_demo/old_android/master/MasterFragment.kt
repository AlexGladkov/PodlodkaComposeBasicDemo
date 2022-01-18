package ru.agladkov.podlodka_compose_basics_demo.old_android.master

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.agladkov.podlodka_compose_basics_demo.MainActivity
import ru.agladkov.podlodka_compose_basics_demo.R
import ru.agladkov.podlodka_compose_basics_demo.old_android.changeScreen
import ru.agladkov.podlodka_compose_basics_demo.old_android.detail.DetailFragment

class MasterFragment: Fragment(R.layout.fragment_master) {

    private val viewModel: MasterViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MasterViewModel::class.java)
    }

    private val masterAdapter = MasterAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.masterListView)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = masterAdapter

        masterAdapter.onItemClick = { data ->
            val detailFragment = DetailFragment()
            detailFragment.arguments = Bundle().apply {
                putParcelable("param", data)
            }

            (requireActivity() as? MainActivity)?.changeScreen(DetailFragment())
        }

        viewModel.masterList.observe(viewLifecycleOwner, {
            masterAdapter.setNewData(it)
        })

        viewModel.fetchMasterData()
    }
}