package agency.firefly.githubsearch

import agency.firefly.githubsearch.databinding.RowLayoutBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserAdapter(val listener: OnUserClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list = mutableListOf<ReposData>()

    // obriše se lista, daju se podaci i javljamo da se nešto promijenilo
    fun setList(repos: List<ReposData>){
        list.clear()
        list.addAll(repos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = CellViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CellViewHolder).bind(list[position], listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class CellViewHolder (val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root){
    // predaješ podatke koje vadiš iz liste tj. podatke koje želiš pokazati
    fun bind(repos: ReposData, listener: OnUserClickListener){
        binding.apply {
            reposData.text = repos.name
            langData.text = repos.language
            ownerData.text = repos.owner.login


            Glide.with(binding.root)
                .load(repos.owner.avatar_url)
                .into(image)
        }

        binding.root.setOnClickListener {
            listener.onClick(repos)
        }

    }
}


