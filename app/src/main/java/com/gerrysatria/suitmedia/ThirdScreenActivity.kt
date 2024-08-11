package com.gerrysatria.suitmedia

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gerrysatria.core.choose_name_data
import com.gerrysatria.core.data.Resource
import com.gerrysatria.suitmedia.databinding.ActivityThirdScreenBinding

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private val viewModel: ThirdScreenViewModel by viewModel()

    private var currentPage = 1
    private val perPage = 10

    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            title = "Third Screen"
            setDisplayHomeAsUpEnabled(true)
        }

        setupRecyclerView()
        setupSwipeRefresh()

        loadData(currentPage)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupRecyclerView () {
        adapter = UserListAdapter { user ->
            val resultIntent = Intent().apply {
                putExtra(choose_name_data, "${user.firstName} ${user.lastName}")
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
        binding.listUser.layoutManager = LinearLayoutManager(this)
        binding.listUser.adapter = adapter
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            refreshData()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadData(page: Int) {
        binding.apply {
            viewModel.getListUser(page, perPage).observe(this@ThirdScreenActivity) { user ->
                swipeRefreshLayout.isRefreshing = false
                if (user != null) {
                    when (user) {
                        is Resource.Loading -> {
                            progressBar.show(true)
                            emptyStateView.show(false)
                            emptyPageView.show(false)
                            listUser.show(false)
                        }
                        is Resource.Success -> {
                            val data = user.data.orEmpty()
                            if (data.isEmpty()) {
                                progressBar.show(false)
                                emptyStateView.show(true)
                                emptyPageView.show(true)
                                emptyPageView.text = "Page $page"
                                listUser.show(false)
                            } else {
                                adapter.submitList(data)
                                progressBar.show(false)
                                emptyStateView.show(false)
                                emptyPageView.show(false)
                                listUser.show(true)
                            }
                        }
                        is Resource.Error -> {
                            progressBar.show(false)
                            emptyStateView.show(true)
                            emptyPageView.show(true)
                            emptyPageView.text = "Page $page"
                            listUser.show(false)
                        }
                    }
                }
            }
        }
    }

    private fun refreshData() {
        currentPage++
        adapter.submitList(emptyList())
        loadData(currentPage)
    }
}