package com.example.test.second

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test.databinding.GitItemFragmentBinding
import kotlinx.android.synthetic.main.git_item_fragment.*

private const val NAME = "param1"
private const val PROJECT_NAME = "param2"

class GitItemFragment : Fragment() {

    private var name: String? = null
    private var projectName: String? = null
    private var binding: GitItemFragmentBinding? = null
    @NonNull
    private var gitItemViewModel: GitItemViewModel? = null
    private var listener: OnFragmentInteractionListener? = null

    companion object {
        fun newInstance(name: String,projectName: String) =
            GitItemFragment().apply {
                arguments = Bundle().apply {
                    putString(NAME, name)
                    putString(PROJECT_NAME, projectName)
                }
            }
    }

    private lateinit var viewModel: GitItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(NAME)
            projectName = it.getString(PROJECT_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GitItemFragmentBinding.inflate(inflater, container, false)
        binding!!.lifecycleOwner = this
        binding!!.data = gitItemViewModel

        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        gitItemViewModel = ViewModelProviders.of(this).get(GitItemViewModel::class.java)

        try{ gitItemViewModel!!.setGitData(name!!,projectName!!) }catch (e:Exception){}
        backButton.setOnClickListener { listener?.onBack() }

        binding!!.data = gitItemViewModel
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onBack()
    }
}
