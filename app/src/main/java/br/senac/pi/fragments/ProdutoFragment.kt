package br.senac.pi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.senac.pi.R
import br.senac.pi.databinding.FragmentArtistsBinding
import br.senac.pi.databinding.FragmentProdutoBinding

class ProdutoFragment : Fragment() {
    lateinit var binding: FragmentProdutoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProdutoBinding.inflate(inflater)
        return binding.root
    }

}