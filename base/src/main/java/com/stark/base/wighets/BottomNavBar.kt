package com.stark.base.wighets

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.stark.base.R

/**
 * Created by zhao on 2018-01-18.
 * Copyright (c) 2018 ejia7.com . All rights reserved.
 */
class BottomNavBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {

    private var mCartBadge: TextBadgeItem
    private var mMsgBadge: ShapeBadgeItem
    var mCount = 0

    init {
        val itemHome = BottomNavigationItem(R.drawable.btn_nav_home_press, resources.getString(R.string.nav_bar_home))
                .setInactiveIconResource(R.drawable.btn_nav_home_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
        val categoryHome = BottomNavigationItem(R.drawable.btn_nav_category_press, resources.getString(R.string.nav_bar_category))
                .setInactiveIconResource(R.drawable.btn_nav_category_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
        val cartHome = BottomNavigationItem(R.drawable.btn_nav_cart_press, resources.getString(R.string.nav_bar_cart))
                .setInactiveIconResource(R.drawable.btn_nav_cart_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
        val msgHome = BottomNavigationItem(R.drawable.btn_nav_msg_press, resources.getString(R.string.nav_bar_msg))
                .setInactiveIconResource(R.drawable.btn_nav_msg_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        val userItem = BottomNavigationItem(R.drawable.btn_nav_user_press, resources.getString(R.string.nav_bar_user))
                .setInactiveIconResource(R.drawable.btn_nav_user_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
//添加badge
        mCartBadge = TextBadgeItem()
        cartHome.setBadgeItem(mCartBadge)

        mMsgBadge = ShapeBadgeItem()
        mMsgBadge.setShape(ShapeBadgeItem.SHAPE_OVAL)
        msgHome.setBadgeItem(mMsgBadge)

        //文字和图片同时显示, 如果使用default 未被选中的item只显示图片
        setMode(BottomNavigationBar.MODE_FIXED)
        //使用设置的背景色
        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        //设置背景色
        setBarBackgroundColor(R.color.common_white)

        addItem(itemHome)
        addItem(categoryHome)
        addItem(cartHome)
        addItem(msgHome)
        addItem(userItem)
        initialise()
    }

    fun checkCartBadge(count: Int) {
        mCount = count
        if (mCount == 0) {
            mCartBadge.hide()
        } else {
            mCartBadge.show()
            mCartBadge.setText("" + mCount)
        }
    }

    fun checkMsgBadge(hasMsg: Boolean) {
        if (hasMsg) {
            mMsgBadge.show()
        } else {
            mMsgBadge.hide()
        }
    }
}