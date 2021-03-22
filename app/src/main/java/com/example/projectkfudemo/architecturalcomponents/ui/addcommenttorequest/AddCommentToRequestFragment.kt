package com.example.projectkfudemo.architecturalcomponents.ui.addcommenttorequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.example.projectkfudemo.architecturalcomponents.ui.MainActivity
import com.example.projectkfudemo.architecturalcomponents.ui.ViewModelGet
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.databinding.FragmentAddCommentToRequestBinding
import com.example.projectkfudemo.parametrclasses.requests.Request

class AddCommentToRequestFragment: Fragment(), ViewModelGet {

    companion object {
        fun newInstance(request: Request): AddCommentToRequestFragment {
            val fragment = AddCommentToRequestFragment()
            fragment.request = request
            return fragment
        }
    }

    private var request: Request? = null

    public var mainActivity: MainActivity?=null

    private var _binding: FragmentAddCommentToRequestBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddCommentToRequestBinding.inflate(inflater, container, false)
        mainActivity = activity as MainActivity
        val view = binding.root

        //задаю кнопку программно чтобы она вывелась ниже от чекбоксов

        return view
    }

    fun createNewCheckBox(id: Int, text: String): CheckBox {
        var checkBox = CheckBox(context)
        checkBox.id = id
        checkBox.text = text
        return checkBox
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getViewModel(): ViewModelInterface {
        return mainActivity?.viewModelAddCommentToRequest!!
    }
}