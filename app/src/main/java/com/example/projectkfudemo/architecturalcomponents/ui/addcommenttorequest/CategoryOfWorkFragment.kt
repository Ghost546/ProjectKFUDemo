package com.example.projectkfudemo.architecturalcomponents.ui.addcommenttorequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectkfudemo.architecturalcomponents.ui.MainActivity
import com.example.projectkfudemo.architecturalcomponents.ui.ViewModelGet
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.databinding.FragmentCategoryOfWorkBinding
import com.example.projectkfudemo.parametrclasses.requests.Request

class CategoryOfWorkFragment : Fragment(), ViewModelGet {

    companion object {
        fun newInstance(): CategoryOfWorkFragment {
            val fragment = CategoryOfWorkFragment() //TODO: Доделать получение списка
            return fragment
        }
    }

    private var request: Request? = null

    var mainActivity: MainActivity?=null

    private var _binding: FragmentCategoryOfWorkBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCategoryOfWorkBinding.inflate(inflater, container, false)
        mainActivity = activity as MainActivity
        val view = binding.root



        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getViewModel(): ViewModelInterface {
        return mainActivity?.viewModelAddCommentToRequest!!
    }
}