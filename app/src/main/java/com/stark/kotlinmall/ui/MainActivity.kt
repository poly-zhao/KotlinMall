package com.stark.kotlinmall.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.stark.kotlinmall.R
import com.stark.kotlinmall.ui.fragment.HomeFragment
import com.stark.kotlinmall.ui.fragment.MeFragment
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var mStack = Stack<Fragment>()

    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { HomeFragment() }
    private val mCartFragment by lazy { HomeFragment() }
    private val mMsgFragment by lazy { HomeFragment() }
    private val mMeFragment by lazy { MeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        initView()
        changeFragment(0)
    }


    private fun initFragment() {
        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMeFragment)

        supportFragmentManager.beginTransaction()
                .add(R.id.mContainer, mHomeFragment)
                .add(R.id.mContainer, mCategoryFragment)
                .add(R.id.mContainer, mCartFragment)
                .add(R.id.mContainer, mMsgFragment)
                .add(R.id.mContainer, mMeFragment)
                .commit()
    }

    private fun initView() {
        mBottomNavBar.checkCartBadge(10)
        mBottomNavBar.checkMsgBadge(false)

        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mBottomNavBar.checkMsgBadge(true)
                }
        Observable.timer(5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mBottomNavBar.checkCartBadge(12)
                }

        val tabSelectedListener = mBottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabReselected(position: Int) = Unit

            override fun onTabUnselected(position: Int) = Unit

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }
        })
    }

    fun changeFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        for (f in mStack) {
            transaction.hide(f)
        }
        transaction.show(mStack[position])
                .commit()
    }


}
