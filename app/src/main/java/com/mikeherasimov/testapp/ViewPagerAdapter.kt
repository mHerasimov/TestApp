package com.mikeherasimov.testapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import android.view.ViewGroup




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

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (position >= count) {
            val manager = (`object` as Fragment).fragmentManager
            manager!!.beginTransaction().remove(`object`).commit()
        }
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