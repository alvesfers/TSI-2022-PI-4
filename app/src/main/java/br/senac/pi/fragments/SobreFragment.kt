package br.senac.pi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.senac.pi.R
import br.senac.pi.databinding.FragmentSobreBinding

class SobreFragment : Fragment() {
    lateinit var binding: FragmentSobreBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSobreBinding.inflate(layoutInflater)

        return binding.root



    }

}