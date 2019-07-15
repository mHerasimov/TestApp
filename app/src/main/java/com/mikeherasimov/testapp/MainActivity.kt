package com.mikeherasimov.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var pagesCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        fabAdd.setOnClickListener {
            pagesCount++
            adapter.addFragment(PageFragment.newInstance(pagesCount.toString()))
            viewPager.setCurrentItem(pagesCount, true)
            updateRemoveVisibility()
        }
        fabRemove.setOnClickListener {
            pagesCount--
            (adapter.getItem(pagesCount) as PageFragment).dismissNotifications()
            viewPager.setCurrentItem(pagesCount, true)
            adapter.removeLastFragment()
            updateRemoveVisibility()
        }
        fabAdd.performClick()
    }

    private fun updateRemoveVisibility() {
        if (pagesCount > 1) {
            fabRemove.show()
        } else {
            fabRemove.hide()
        }
    }

}
