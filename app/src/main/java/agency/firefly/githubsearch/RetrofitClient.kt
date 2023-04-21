package agency.firefly.githubsearch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// object je jedna instanca u memoriji. Retrofit zauzima puno mjesta pa se samo jednom kreira.
object RetrofitClient {
    private const val BASE_URL = "https://api.github.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // izgenerira se klasa u pozadini
    val searchApi = retrofit.create(SearchApi::class.java)

}