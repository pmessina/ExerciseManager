package com.pirateman.exercisemanager.workout

import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import kotlinx.android.parcel.Parcelize

class WorkoutsExpandableListAdapter(groups: MutableList<WorkoutNameExpandableGroup>) : ExpandableRecyclerViewAdapter<WorkoutNameViewHolder, WorkoutRepsViewHolder>(groups) {

    override fun onBindChildViewHolder(holder: WorkoutRepsViewHolder?, flatPosition: Int, group: ExpandableGroup<*>?, childIndex: Int) {
        TODO("Not yet implemented")
    }

    override fun onBindGroupViewHolder(holder: WorkoutNameViewHolder?, flatPosition: Int, group: ExpandableGroup<*>?) {
        TODO("Not yet implemented")
    }

    override fun onCreateChildViewHolder(parent: ViewGroup?, viewType: Int): WorkoutRepsViewHolder {
        TODO("Not yet implemented")
    }

    override fun onCreateGroupViewHolder(parent: ViewGroup?, viewType: Int): WorkoutNameViewHolder {
        TODO("Not yet implemented")
    }
}

class WorkoutNameExpandableGroup(private val workoutTitle: String, private val workouts: List<WorkoutRepsSets>) : ExpandableGroup<WorkoutRepsSets>(workoutTitle, workouts){

}


@Parcelize
class WorkoutRepsSets(val reps: String, val sets: String) : Parcelable {

}

//@Parcelize
//class RepsSetsInWorkout: Parcelable{
//
//}

class WorkoutNameViewHolder(view: View): GroupViewHolder(view){
}

class WorkoutRepsViewHolder(view: View): ChildViewHolder(view){

}