package com.example.projectkfudemo.architecturalcomponents.ui.addcommenttorequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectkfudemo.databinding.FragmentAddCommentToRequestBinding
import com.example.projectkfudemo.requests.Request

class AddCommentToRequestFragment: Fragment() {
    companion object {
        fun newInstance(request: Request): AddCommentToRequestFragment {
            val fragment = AddCommentToRequestFragment()
            fragment.request = request
            return fragment
        }
    }

    private var request: Request? = null

    private var _binding: FragmentAddCommentToRequestBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddCommentToRequestBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}