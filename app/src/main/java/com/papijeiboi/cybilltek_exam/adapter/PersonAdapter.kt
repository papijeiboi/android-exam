package com.papijeiboi.cybilltek_exam.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.papijeiboi.cybilltek_exam.data.Person
import com.papijeiboi.cybilltek_exam.databinding.ItemPersonBinding

class PersonAdapter(private val onClickListener: OnClickListener) : ListAdapter<Person, PersonAdapter.PersonViewHolder>(PersonComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
            holder.itemView.setOnClickListener {
                onClickListener.onClick(currentItem)
            }
        }
    }

    class PersonViewHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            binding.apply {
                Glide.with(itemView)
                    .load(person.picture.thumbnail)
                    .circleCrop()
                    .into(ivPersonImage)

                tvPersonName.text = "${person.name.first} ${person.name.last}"

                tvPersonEmailAddress.text = person.email
            }
        }
    }

    class PersonComparator : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person) =
            oldItem.name.first == newItem.name.first

        override fun areContentsTheSame(oldItem: Person, newItem: Person) =
            oldItem == newItem
    }

    class OnClickListener(val clickListener: (person: Person) -> Unit) {
        fun onClick(person: Person) = clickListener(person)
    }

}