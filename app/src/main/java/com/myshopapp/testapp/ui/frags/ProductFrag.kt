package com.myshopapp.testapp.ui.frags

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.myshopapp.testapp.arch.entities.ItemModel
import com.myshopapp.testapp.arch.vm.ItemViewModel
import com.myshopapp.testapp.databinding.FragItemsBinding
import com.myshopapp.testapp.ui.CardActivity
import com.myshopapp.testapp.ui.adapter.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFrag : Fragment() {
    private val moduleType by lazy { requireArguments().getInt("moduleType") }
    private var binding: FragItemsBinding? = null
    private var adapter: ItemAdapter? = null

    private val viewModel: ItemViewModel by viewModels<ItemViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding != null) {
            binding = FragItemsBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        if (moduleType != IS_CART) {
            viewModel.fetchItems()
        }
        initListener()
    }

    private fun initAdapter() {
        adapter = ItemAdapter {}
        binding?.items?.adapter = adapter
    }

    private fun observeData() {
        if (moduleType != IS_CART) {

        }else{
            viewModel.shareMutableLiveData.observe(this) {
                if (it is com.myshopapp.testapp.arch.repos.Result.Success) {
                    adapter?.notifyAdapter(it.data.items)
                } else {
                    initEmptyView()
                }
            }
        }
    }

    private fun initEmptyView() {

    }


    private fun initListener() {
        if (moduleType == IS_CART) {
            binding?.submit?.text = "Order"
        }
        binding?.submit?.setOnClickListener {
            if (moduleType == IS_CART) {
                startActivity(
                    Intent(requireContext(), CardActivity::class.java)
                        .putParcelableArrayListExtra(
                            "list",
                            adapter?.list ?: arrayListOf<ItemModel>()
                        )
                )
            } else {
                var isItemAvailable = false
                adapter?.list?.forEach {
                    if (it.selected > 0) {
                        isItemAvailable = true
                    }
                }
                if (isItemAvailable) {
                    viewModel.order(adapter?.list)
                }
            }
        }
    }

    companion object {
        const val IS_CART = 1
        fun getInstnace(moduleType: Int): ProductFrag {
            return ProductFrag().also {
                it.arguments = bundleOf(
                    "moduleType" to moduleType
                )
            }
        }
    }
}