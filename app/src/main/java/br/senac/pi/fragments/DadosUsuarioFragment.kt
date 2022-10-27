package br.senac.pi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.senac.pi.R
import br.senac.pi.databinding.FragmentDadosUsuarioBinding
import br.senac.pi.databinding.FragmentProdutoBinding

class DadosUsuarioFragment : Fragment() {
    lateinit var binding: FragmentDadosUsuarioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDadosUsuarioBinding.inflate(inflater)

        return binding.root
    }
}