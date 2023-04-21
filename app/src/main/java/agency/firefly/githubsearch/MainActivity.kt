package agency.firefly.githubsearch

import agency.firefly.githubsearch.databinding.ActivityMainBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnUserClickListener {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // nemamo sve potrebne parametre u ovom trenutku
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        userAdapter = UserAdapter(this)

        binding.apply {
            // predajemo svoju verziju adaptera da recycler view zna kako prikazati podatke
            recycler.adapter = userAdapter

            // kako prikazujemo podatke na ekranu - u ovom slučaju linearno, kao lista
            recycler.layoutManager = LinearLayoutManager(this@MainActivity)

            searchButton.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    search(query)
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }

            })


        }

    }

    // funkcija koja pretražuje github
    fun search (query: String) {
        RetrofitClient.searchApi
            .searchRepos(query)
            // startaj ovaj API poziv. Callback - vraća result
            .enqueue(object: Callback<SearchResponse> {
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            userAdapter.setList(it.items) }
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    // funkcija koja otvara novi ekran s detaljima korisnika
    override fun onClick(repos: ReposData) {
        val intent = Intent(this, DetailsActivity::class.java)
        val userData = repos
        intent.putExtra(DetailsActivity.GENERAL, repos.owner)
        startActivity(intent)
    }


}

interface OnUserClickListener {
    fun onClick(repos: ReposData)
}


// GITHUB token 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
// https://api.github.com/search/users?q=broBert47

// data class u intent
// fetchanje repositorija
// kad se klikne, pošalje se ID reposa, pročita se taj ID, fetchaju se podaci i onda se prikažu


/*
 val userData = repos.owner.bio?.let { UserData(repos.owner.login, repos.owner.type, it, repos.owner.avatar_url ) }
        intent.putExtra(DetailsActivity.GENERAL, userData)

        intent.putExtra(DetailsActivity.LOGIN, repos.owner.login)
        intent.putExtra(DetailsActivity.TYPE, repos.owner.type)
        intent.putExtra(DetailsActivity.BIO, repos.owner.type)
        intent.putExtra(DetailsActivity.IMAGE, repos.owner.avatar_url)
 */