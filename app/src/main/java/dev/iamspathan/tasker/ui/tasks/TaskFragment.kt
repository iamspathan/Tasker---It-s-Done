package dev.iamspathan.tasker.ui.tasks

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.iamspathan.tasker.R
import dev.iamspathan.tasker.databinding.FragmentTaskBinding
import dev.iamspathan.tasker.ui.tasks.SortOrder.BY_DATE
import dev.iamspathan.tasker.ui.tasks.SortOrder.BY_NAME
import dev.iamspathan.tasker.util.onQueryTextChanged

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

        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_task, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.onQueryTextChanged {
            viewModel.searchQuery.value = it
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){

            R.id.action_sort_by_name -> {
                viewModel.sortOrder.value = BY_NAME
                true
            }

            R.id.action_sort_by_date_created -> {
                viewModel.sortOrder.value = BY_DATE
                true
            }

            R.id.action_hide_completed_all_task -> {
                item.isChecked = !item.isChecked
                viewModel.hideCompleted.value = item.isChecked
                true
            }
            else -> super.onOptionsItemSelected(item)

        }


    }

}