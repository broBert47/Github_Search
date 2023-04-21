package agency.firefly.githubsearch

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun searchUsers(
        @Query("q") query: String,
    ): Call<SearchResponse>

    @GET("search/repositories")
    @Headers("Authorization: token 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")
    fun searchRepos(
        @Query("q") query: String,
    ): Call<SearchResponse>
}

// API poziv za specifičan repos