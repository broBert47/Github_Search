package agency.firefly.githubsearch

import agency.firefly.githubsearch.databinding.DetailsBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class DetailsActivity: AppCompatActivity(){

    private val binding: DetailsBinding by lazy {
        DetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val userData = intent.getParcelableExtra<UserData>(GENERAL)!!

        binding.username.text = userData.login
        binding.type.text = userData.type
        binding.bio.text = userData.bio

        Glide.with(this)
            .load(userData.avatar_url)
            .into(binding.imageDetails)

        /*
        binding.username.text = intent.getStringExtra(LOGIN)
        binding.type.text = intent.getStringExtra(TYPE)
        binding.bio.text = intent.getStringExtra(BIO)

        Glide.with(this)
            .load(intent.getStringExtra(IMAGE))
            .into(binding.imageDetails)


         */



    }

    companion object{
        const val LOGIN = "Login"
        const val TYPE = "Type"
        const val BIO = "Bio"
        const val IMAGE = "Image"
        const val GENERAL = "General"
    }

}