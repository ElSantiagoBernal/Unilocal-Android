package com.example.unilocal.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unilocal.R
import com.example.unilocal.databinding.FragmentCommentsPlaceBinding
import com.example.unilocal.db.Comments
import com.example.unilocal.model.Comment


class CommentsPlaceFragment : Fragment() {

    lateinit var binding: FragmentCommentsPlaceBinding
    var list:ArrayList<Comment> = ArrayList()
    var codePlace:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(arguments != null){
            codePlace = requireArguments().getInt("id_place")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommentsPlaceBinding.inflate(inflater, container, false)

        list = Comments.listById(codePlace)


        return binding.root
    }

    companion object{
        fun newInstance(codePlace: Int):CommentsPlaceFragment{
            val args = Bundle()
            args.putInt("id_place", codePlace)
            val fragment = CommentsPlaceFragment()
            fragment.arguments = args
            return fragment
        }
    }
}