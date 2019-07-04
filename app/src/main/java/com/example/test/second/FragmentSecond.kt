package com.example.test.second

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test.databinding.FragmentSecondFragmentBinding
import kotlinx.android.synthetic.main.fragment_second_fragment.*


class FragmentSecond : Fragment(), OnRecyclerListener {

    companion object {
        fun newInstance() = FragmentSecond()
    }

    private var binding: FragmentSecondFragmentBinding? = null
    @NonNull
    private var secondViewModel: FragmentSecondViewModel? = null
    private var listener: FragmentSecondListener? = null

    // RecyclerViewとAdapter
    private var mAdapter: RecyclerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondFragmentBinding.inflate(inflater, container, false)
        binding!!.lifecycleOwner = this
        binding!!.viewModel = secondViewModel

        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        secondViewModel = ViewModelProviders.of(this).get(FragmentSecondViewModel::class.java)

        secondViewModel!!.setListsDataInit()

        try { secondViewModel!!.setGitListData("sample") }catch(e: Exception){}

        binding!!.viewModel = secondViewModel

        //Adapter設定
//        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = GridLayoutManager(context,2)
        mAdapter = RecyclerAdapter(context!!, secondViewModel!!.getGitListData().value!!, this)
        recycler_view.adapter = mAdapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentSecondListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onRecyclerClicked(v: View, position: Int,name: String, projectName: String) {
        listener?.onStartDetail(name,projectName)
    }

    interface FragmentSecondListener {
        fun onStartDetail(name: String, projectName: String)
    }
}
