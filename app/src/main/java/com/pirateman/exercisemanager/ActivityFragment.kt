package com.pirateman.exercisemanager

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
class ActivityFragment : Fragment() {
    private var mViewModel: GridItemViewModel? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val bundle = arguments
        val exerciseInterval = bundle!!.getSerializable("exerciseInterval") as ExerciseInterval?

        //GridItemFragmentBinding binding = GridItemFragmentBinding.inflate(inflater, container, false);
        //ExerciseInterval exerciseInterval = new ExerciseInterval(1, 3.0, 1.0, 5);

        //binding.setExercise(exerciseInterval);
        return this.view //binding.getRoot();
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(GridItemViewModel::class.java)
    }

    fun onReplaceFragmentClick(view: View?) {
        val interval = ExerciseInterval()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Activities containing this fragment must implement its callbacks
        mCallbacks = context as Callbacks
    }

    private var mCallbacks: Callbacks? = null

    interface Callbacks {
        //Callback for when button clicked.
        fun onButtonClicked()
    }

    companion object {
        fun newInstance(): ActivityFragment {
            return ActivityFragment()
        }
    }
}