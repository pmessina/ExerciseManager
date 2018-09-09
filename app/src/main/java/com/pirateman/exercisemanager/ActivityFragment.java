package com.pirateman.exercisemanager;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ActivityFragment extends Fragment {

    private GridItemViewModel mViewModel;

    public static ActivityFragment newInstance() {
        return new ActivityFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();

        ExerciseInterval exerciseInterval = (ExerciseInterval)bundle.getSerializable("exerciseInterval");

        //GridItemFragmentBinding binding = GridItemFragmentBinding.inflate(inflater, container, false);
        //ExerciseInterval exerciseInterval = new ExerciseInterval(1, 3.0, 1.0, 5);

        //binding.setExercise(exerciseInterval);

        return this.getView();//binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(GridItemViewModel.class);
        // TODO: Use the ViewModel
    }


    public void onReplaceFragmentClick(View view)
    {
        ExerciseInterval interval = new ExerciseInterval();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Activities containing this fragment must implement its callbacks
        mCallbacks = (Callbacks) context;

    }

    private Callbacks mCallbacks;

    public interface Callbacks {
        //Callback for when button clicked.
        public void onButtonClicked();
    }


}


