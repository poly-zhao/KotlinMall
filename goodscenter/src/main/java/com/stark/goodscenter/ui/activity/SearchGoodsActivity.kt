package com.stark.goodscenter.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.stark.base.ui.BaseRecyclerViewAdapter
import com.stark.base.utils.AppPrefsUtils
import com.stark.goodscenter.R
import com.stark.goodscenter.common.GoodsConstant
import com.stark.goodscenter.ui.adapter.SearchHistoryAdapter
import kotlinx.android.synthetic.main.activity_search_goods.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SearchGoodsActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_goods)
        initView()
    }

    private fun initView() {
        mLeftIv.setOnClickListener(this)
        mSearchTv.setOnClickListener(this)
        mClearBtn.setOnClickListener(this)
        mSearchHistoryRv.layoutManager = LinearLayoutManager(this)
        var adapter = SearchHistoryAdapter(this)
        mSearchHistoryRv.adapter = adapter
        adapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<String> {
            override fun onItemClick(item: String, position: Int) {
                enterGoodsList(item)
            }
        })
    }


    fun loadData() {
        val set = AppPrefsUtils.getStringSet(GoodsConstant.SP_SEARCH_HISTORY)
        mNoDataTv.visibility = if (set.isEmpty()) View.VISIBLE else View.INVISIBLE
        mNoDataTv.visibility = if (!set.isEmpty()) View.VISIBLE else View.INVISIBLE
        if (set.isNotEmpty()) {
            val list = mutableListOf<String>()
            list.addAll(set)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mLeftIv -> finish()
            R.id.mSearchTv -> doSearch()
            R.id.mClearBtn -> {
                AppPrefsUtils.remove(GoodsConstant.SP_SEARCH_HISTORY)
                loadData()
            }
        }
    }

    //搜索
    private fun doSearch() {
        if (mKeywordEt.text.isNullOrEmpty()) {
            toast("请输入需要搜索的关键字")
        } else {
            val inputValue = mKeywordEt.text.toString()
            AppPrefsUtils.putStringSet(GoodsConstant.SP_SEARCH_HISTORY, mutableSetOf(inputValue))
            enterGoodsList(inputValue)
        }
    }


    /*
    进入商品列表界面
 */
    private fun enterGoodsList(value: String) {
        //输入不为空，进入商品列表
        startActivity<GoodsListActivity>(
                GoodsConstant.KEY_SEARCH_GOODS_TYPE to GoodsConstant.SEARCH_GOODS_TYPE_KEYWORD,
                GoodsConstant.KEY_GOODS_KEYWORD to value
        )

    }
}
