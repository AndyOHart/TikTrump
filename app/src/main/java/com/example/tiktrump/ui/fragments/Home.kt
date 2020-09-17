package com.example.tiktrump.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tiktrump.adapter.TrumpQuoteAdapter
import com.example.tiktrump.databinding.FragmentHomeBinding
import com.example.tiktrump.utils.NewResult
import com.example.tiktrump.viewmodel.TikTrumpViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment() {

    private val viewModel: TikTrumpViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TrumpQuoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setupRetrieveQuotesObserver()
        setupDeleteDataListener()
        setupFetchNewDataListener()
        setupSwipeToRefresh()
    }

    private fun initRecyclerView() {
        adapter = TrumpQuoteAdapter()
        binding.trumpQuoteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.trumpQuoteRecyclerView.adapter = adapter
    }

    private fun setupDeleteDataListener() {
        binding.removeQuotesButton.setOnClickListener {
            viewModel.removeAllTrumpQuotes()
            Snackbar.make(binding.root, "Deleted Database", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setupFetchNewDataListener() {
        binding.getTrumpQuotesButton.setOnClickListener {
            viewModel.removeAllTrumpQuotes()
            viewModel.getTrumpQuotes()
        }
    }

    private fun setupRetrieveQuotesObserver() {
        viewModel.trumpQuoteLiveDataList.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                NewResult.Status.SUCCESS -> {
                    Log.d("HOME", "SUCCESS")
                    result.data.let { adapter.setItems(ArrayList(result.data)) }
                    binding.progressBar.visibility = View.GONE
                    binding.swipeContainer.isRefreshing = false
                }

                NewResult.Status.ERROR -> {
                    Log.d("HOME", "ERROR")
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(binding.root, "Some error has occurred", Snackbar.LENGTH_SHORT)
                        .show()
                }

                NewResult.Status.LOADING -> {
                    Log.d("HOME", "INPROGRESS")
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setupSwipeToRefresh() {
        binding.swipeContainer.setOnRefreshListener {
            viewModel.getTrumpQuotes()
        }
    }
}