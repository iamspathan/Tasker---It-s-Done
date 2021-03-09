package dev.iamspathan.tasker.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.iamspathan.tasker.data.Task
import dev.iamspathan.tasker.databinding.ItemTaskBinding
import dev.iamspathan.tasker.ui.tasks.TasksAdapter.TaskViewHolder

class TasksAdapter : ListAdapter<Task, TaskViewHolder>(DiffCallBack()) {

    class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.apply {
                textViewTaskName.text = task.name
                checkBoxCompleted.isChecked = task.completed
                textViewTaskName.paint.isStrikeThruText = task.completed
                labelPriority.isVisible = task.important
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DiffCallBack : DiffUtil.ItemCallback<Task>() {

        override fun areItemsTheSame(oldItem: Task, newItem: Task) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Task, newItem: Task) =
            oldItem == newItem
    }
}