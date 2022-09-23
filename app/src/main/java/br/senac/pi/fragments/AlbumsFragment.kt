package br.senac.pi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.senac.pi.R
import br.senac.pi.databinding.FragmentAlbumsBinding

class AlbumsFragment : Fragment() {
    lateinit var binding: FragmentAlbumsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAlbumsBinding.inflate(inflater)

        return binding.root
    }

}