package com.android.person.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.person.R
import com.android.person.common.BaseActivity
import com.android.person.common.EndlessScrollListener
import com.android.person.databinding.ActivityMainBinding
import com.android.person.ui.adapter.PersonAdapter
import com.android.person.util.observe
import com.android.person.util.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val mainVM: MainViewModel by viewModels()

    private val personAdapter = PersonAdapter()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): MainViewModel = mainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe()
        initRV()
        initListeners()
    }

    private fun initListeners() {
        binding.swipeRefresh.apply {
            setOnRefreshListener {
                mainVM.swipeToFetchData()
                isRefreshing = false
            }
        }

    }

    private fun observe() {
        with(mainVM) {
            observe(personsLiveData) { persons ->
                personAdapter.setPersons(persons)
            }

            observe(errorLiveData) { errorMessage ->
                showErrorDialog(errorMessage) {
                    fetchPersons()
                }
            }
        }
    }

    private fun initRV() {
        binding.rvPerson.apply {
            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
            adapter = personAdapter
            layoutManager = linearLayoutManager
            addOnScrollListener(object : EndlessScrollListener(linearLayoutManager) {
                override fun onLoadMore(page: Int) {
                    mainVM.fetchPersons()
                }
            })
        }
    }
}