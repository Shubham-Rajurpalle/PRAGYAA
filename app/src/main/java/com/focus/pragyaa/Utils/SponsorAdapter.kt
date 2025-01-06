import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.focus.pragyaa.Utils.Sponsor
import com.focus.pragyaa.databinding.SponsorItemLayoutBinding

class SponsorAdapter(sponsorList: List<Sponsor>) : RecyclerView.Adapter<SponsorAdapter.SponsorViewHolder>() {

    private var sponsorList = ArrayList<Sponsor>()

    inner class SponsorViewHolder(private val binding: SponsorItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sponsor: Sponsor) {
            binding.eventCardRecyclerImageview.setImageResource(sponsor.imageurl)

            // Optional: Add click listener for the card
            binding.eventRecyclerCardview.setOnClickListener {
                // Handle click event
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SponsorViewHolder {
        val binding = SponsorItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SponsorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SponsorViewHolder, position: Int) {
        holder.bind(sponsorList[position])
    }

    override fun getItemCount(): Int = sponsorList.size

    fun updateList(newList: ArrayList<Sponsor>) {
        sponsorList = newList
        notifyDataSetChanged()
    }
}

