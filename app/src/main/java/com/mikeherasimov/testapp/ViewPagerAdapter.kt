package com.mikeherasimov.testapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter


class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragments = mutableListOf<Fragment>()

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

    override fun getItemPosition(`object`: Any): Int {
        val position = fragments.indexOf(`object`)
        if (position != -1) {
            return PagerAdapter.POSITION_UNCHANGED
        }
        return PagerAdapter.POSITION_NONE
    }

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
        notifyDataSetChanged()
    }

    fun removeFragmentAt(pos: Int) {
        fragments.removeAt(pos)
        notifyDataSetChanged()
    }

    fun removeLastFragment() {
        removeFragmentAt(fragments.size - 1)
    }

}