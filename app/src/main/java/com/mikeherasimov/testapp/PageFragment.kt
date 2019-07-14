package com.mikeherasimov.testapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_page.*


class PageFragment : Fragment() {

    private val pageName: String by lazy { arguments!!.getString(ARG_PAGE_NAME) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvPageNumber.text = pageName
    }

    companion object {

        private const val ARG_PAGE_NAME = "pageName"

        fun newInstance(pageName: String): PageFragment {
            val fragment = PageFragment()
            val args = Bundle()
            args.putString(ARG_PAGE_NAME, pageName)
            fragment.arguments = args
            return fragment
        }

    }

}
