package com.android.person.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.person.R
import com.android.person.data.source.Person
import com.android.person.databinding.ItemPersonBinding

class PersonAdapter :
    RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    private val persons: MutableList<Person> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPersonBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(persons[position])
    }

    override fun getItemCount(): Int = persons.size

    fun setPersons(personList: List<Person>) {
        val beforeSize = persons.size
        persons.addAll(personList)
        notifyItemRangeInserted(beforeSize, personList.size)
    }

    class ViewHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            binding.item = person
        }
    }

}