package dev.iamspathan.tasker.ui.tasks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.iamspathan.tasker.R
import dev.iamspathan.tasker.databinding.FragmentTaskBinding

@AndroidEntryPoint
class TaskFragment : Fragment(R.layout.fragment_task) {

    private val viewModel: TaskViewModel by viewModels()
    private lateinit var binding: FragmentTaskBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTaskBinding.bind(view)

        val taskAdapter = TasksAdapter()
        binding.apply {
            recyclerViewTask.apply {
                adapter  = taskAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

        }

        viewModel.tasks.observe(viewLifecycleOwner) {
            taskAdapter.submitList(it)
        }

    }

}