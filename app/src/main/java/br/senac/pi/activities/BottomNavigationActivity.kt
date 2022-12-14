package br.senac.pi.activities

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.senac.pi.R
import br.senac.pi.databinding.ActivityBottomNavigationBinding
import br.senac.pi.fragments.AlbumsFragment
import br.senac.pi.fragments.ArtistsFragment
import br.senac.pi.fragments.RecentsFragment
import br.senac.pi.model.Produto
import br.senac.pi.service.ProdutoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BottomNavigationActivity : AppCompatActivity() {

    lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Trata o clique nos itens da bottom navigation
        binding.bottomNavigation.setOnItemSelectedListener {
            //Verifica qual opção do menu foi clicada, de acordo com o ID das opções no arquivo de menu
            when(it.itemId) {
                //Se clicou no menu com ID "artistas"
                R.id.pesquisar -> {
                    //Cria uma nova instância do fragmento de artistas
                    val frag = ArtistsFragment()
                    //Faz o gestor de fragmentos trocar o fragmento atualmente em exibição
                    //pelo fragmento de artistas
                    supportFragmentManager.beginTransaction().replace(R.id.container, frag).commit()
                }
                R.id.perfil -> {
                    val frag = AlbumsFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container, frag).commit()
                }
                //Se clicou em outra opção
                else -> {
                    //Cria uma nova instância do fragmento de recentes
                    val frag = RecentsFragment(12)
                    //Faz o gestor de fragmentos trocar o fragmento atualmente em exibição
                    //pelo fragmento de recentes
                    supportFragmentManager.beginTransaction().replace(R.id.container, frag).commit()
                }
            }

            true
        }


    }

}