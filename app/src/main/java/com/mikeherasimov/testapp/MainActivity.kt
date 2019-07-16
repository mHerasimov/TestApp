package com.mikeherasimov.testapp

import android.content.Intent
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
            adapter.addFragment(PageFragment.newInstance(++pagesCount))
            viewPager.setCurrentItem(pagesCount, true)
            updateRemoveVisibility()
        }
        fabRemove.setOnClickListener {
            adapter.removeLastFragment()
            viewPager.setCurrentItem(--pagesCount, true)
            updateRemoveVisibility()
        }
        fabAdd.performClick()
    }

    override fun onNewIntent(intent: Intent) {
        val pageToOpen = intent.getIntExtra(PageFragment.ARG_PAGE_NUMBER, -1)
        if (pageToOpen != -1) {
            viewPager.setCurrentItem(pageToOpen - 1, true)
        }
    }

    private fun updateRemoveVisibility() {
        if (pagesCount > 1) {
            fabRemove.show()
        } else {
            fabRemove.hide()
        }
    }

}
