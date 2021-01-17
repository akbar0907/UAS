package com.github.UAS0678.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.UAS0678.R
import com.github.UAS0678.adapter.MainAdapter
import com.github.UAS0678.databinding.FragmentFollowBinding


class FollowingFragment : Fragment(R.layout.fragment_follow) {


    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowingViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = arguments
        username = args?.getString(DetailActivity.EXTRA_USERNAME).toString()

        _binding = FragmentFollowBinding.bind(view)

        adapter = MainAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            recyclerMain.setHasFixedSize(true)
            recyclerMain.layoutManager = LinearLayoutManager(activity)
            recyclerMain.adapter = adapter
        }

        showLoading(true)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowingViewModel::class.java)
        viewModel.setListFollowing(username)
        viewModel.getListFollowing().observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

}