package com.pirateman.exercisemanager.workout

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.elder.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.elder.expandablerecyclerview.ExpandableRowOnClickListener
import com.pirateman.exercisemanager.R
import com.pirateman.exercisemanager.databinding.ContentWorkoutBinding
import com.pirateman.exercisemanager.databinding.ContentWorkoutIntervalBinding
import com.pirateman.exercisemanager.databinding.ContentWorkoutSubheaderBinding
import com.pirateman.exercisemanager.exercise.Exercise
import kotlinx.parcelize.Parcelize


class WorkoutsExpandableListAdapter(
    private val workouts: HashMap<WorkoutHeader, MutableList<WorkoutContent>>,
    hvhClass: Class<WorkoutHeaderViewHolder> = WorkoutHeaderViewHolder::class.java,
    svhClass: Class<WorkoutSubHeaderViewHolder> = WorkoutSubHeaderViewHolder::class.java,
    cvhClass: Class<WorkoutContentViewHolder> = WorkoutContentViewHolder::class.java
) :
    ExpandableRecyclerViewAdapter<WorkoutHeader, WorkoutSubHeader, WorkoutContent, WorkoutHeaderViewHolder, WorkoutSubHeaderViewHolder, WorkoutContentViewHolder>
        (hvhClass, svhClass, cvhClass), ExpandableRowOnClickListener<WorkoutHeader, WorkoutSubHeader, WorkoutContent> {

    init {
        this.expandableRowOnClickListener = this
    }


    // return the number of sections in your view
    override fun getNumberOfSections(): Int {
        return workouts.size
    }

    // provide the Header object for a section
    override fun getHeaderForSection(sectionIndex: Int): WorkoutHeader {

        return workouts.keys.elementAt(sectionIndex)
//
//        when (sectionIndex) {
//            0 -> return WorkoutHeader(Exercise("bicep curls", "biceps"))
//            1 -> return WorkoutHeader(Exercise("pushups", "chest"))
//            2 -> return WorkoutHeader(Exercise("tricep extensions", "triceps"))
//            3 -> return WorkoutHeader(Exercise("lunges", "quads"))
//        }
//
//        return WorkoutHeader()
    }

    // provide the SubHeader model object for a section
    override fun getSubHeaderForSection(p0: Int): WorkoutSubHeader? {
        return WorkoutSubHeader("Expand", "Minimize", "Loading", "Error loading")
    }

    // provide an ArrayList() of Content model objects for each section
    override fun getContentForSection(sectionIndex: Int): MutableList<WorkoutContent>? {

        val workoutHeader = workouts.keys.elementAt(sectionIndex)
        return workouts[workoutHeader]

//
//        when (sectionIndex) {
//            0 -> return mutableListOf(
//                WorkoutContent("0", "0", "0")
//            )
//            1 -> return mutableListOf(
//                WorkoutContent("0", "0", "0"),
//                WorkoutContent("1", "1", "1"),
//            )
//            2 -> return mutableListOf(
//                WorkoutContent("0", "0", "0"),
//                WorkoutContent("1", "1", "1"),
//                WorkoutContent("2", "2", "2")
//            )
//            3 -> return mutableListOf(
//                WorkoutContent("0", "0", "0"),
//                WorkoutContent("1", "1", "1"),
//                WorkoutContent("2", "2", "2"),
//                WorkoutContent("3", "3", "3")
//            )
//        }
//
//        return mutableListOf(WorkoutContent())
    }

    // provide the default expansion state for each section
    override fun createSectionHeaderViewHolder(parent: ViewGroup): WorkoutHeaderViewHolder {
        val binding = ContentWorkoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkoutHeaderViewHolder(binding)
    }

    override fun createSectionSubHeaderViewHolder(parent: ViewGroup): WorkoutSubHeaderViewHolder {
        val binding = ContentWorkoutSubheaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkoutSubHeaderViewHolder(binding)
    }

    override fun createSectionContentViewHolder(parent: ViewGroup): WorkoutContentViewHolder {
        val binding = ContentWorkoutIntervalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkoutContentViewHolder(binding)
    }

    override fun bindSectionHeaderViewHolder(holder: WorkoutHeaderViewHolder, header: WorkoutHeader, sectionIndex: Int, expansionState: Int) {
        holder.binding.tvWorkoutExerciseName.text = header.exercise.name

        holder.binding.btnAddWorkoutSet.setOnClickListener {
            //TODO: Add persistence
            setExpansionStateExpanded(sectionIndex)
            this.workouts[header]?.add(WorkoutContent())
            createSectionContentViewHolder(holder.binding.root)

            this.notifySectionContentChanged(sectionIndex)
        }

        holder.binding.imgArrowIndicator.setOnClickListener(this)
        if (expansionState == EXPANSION_STATE_MINIMIZED) {
            holder.binding.imgArrowIndicator.setImageDrawable(ContextCompat.getDrawable(holder.binding.root.context, R.drawable.arrow_down))

        } else {
            holder.binding.imgArrowIndicator.setImageDrawable(ContextCompat.getDrawable(holder.binding.root.context, R.drawable.arrow_up))
        }

    }

    override fun bindSectionSubHeaderViewHolder(holder: WorkoutSubHeaderViewHolder, subHeader: WorkoutSubHeader, sectionIndex: Int, expansionState: Int) {

        when (expansionState) {
            EXPANSION_STATE_EXPANDED -> holder.binding.tvWorkoutSubHeader.text = subHeader.expandString
            EXPANSION_STATE_ERROR_LOADING -> holder.binding.tvWorkoutSubHeader.text = subHeader.errorString
            EXPANSION_STATE_LOADING_CONTENT -> holder.binding.tvWorkoutSubHeader.text = subHeader.loadingString
            EXPANSION_STATE_MINIMIZED -> holder.binding.tvWorkoutSubHeader.text = subHeader.minimizeString
        }

    }

    override fun bindSectionContentViewHolder(holder: WorkoutContentViewHolder, content: WorkoutContent, sectionIndex: Int, expansionState: Int) {

        holder.binding.edtWorkoutWeight.setText(content.reps)
        holder.binding.edtWorkoutReps.setText(content.sets)
        holder.binding.edtWorkoutSets.setText(content.weight)

        //holder.itemView.setOnClickListener(this)
    }

    override fun getDefaultExpansionStateForSection(sectionIndex: Int): Int {
        return EXPANSION_STATE_MINIMIZED
    }

    override fun shouldShowSectionSubHeader(sectionIndex: Int, expansionState: Int): Boolean {
        return false
    }

    override fun getSavedStateForSection(sectionIndex: Int, expansionState: Int): Int {
        if (sectionIndex == 0 || sectionIndex == 2) {
            return EXPANSION_STATE_MINIMIZED
        }

        return if (expansionState == EXPANSION_STATE_EXPANDED) expansionState else EXPANSION_STATE_MINIMIZED
    }

    override fun sectionHeaderClicked(header: WorkoutHeader, sectionIndex: Int, expansionState: Int) {

            when (expansionState) {
            EXPANSION_STATE_EXPANDED -> {
                // section is currently expanded - minimize it
                setExpansionStateMinimized(sectionIndex)
            }
            EXPANSION_STATE_MINIMIZED -> {

                if (sectionHasContent(sectionIndex)) {
                    // We can show the section
                    setExpansionStateExpanded(sectionIndex)
                } else {
                    setExpansionStateLoading(sectionIndex)
                }
            }
            EXPANSION_STATE_ERROR_LOADING -> {
                setExpansionStateLoading(sectionIndex)
            }
        }
    }

    override fun sectionSubHeaderClicked(subHeader: WorkoutSubHeader, sectionIndex: Int, expansionState: Int) {
        when (expansionState) {
            EXPANSION_STATE_EXPANDED -> {
                // section is currently expanded - minimize it
                setExpansionStateMinimized(sectionIndex)
            }
            EXPANSION_STATE_MINIMIZED -> {

                // section is currently minimized
                // Check if there is content already loaded
                if (sectionHasContent(sectionIndex)) {
                    // We can show the section
                    setExpansionStateExpanded(sectionIndex)

                    // At this point you may want to call the activity and refresh the data source as well.
                } else {
                    // Section is empty - set the expansion state and try to load the section
                    setExpansionStateLoading(sectionIndex)
                }
            }
            EXPANSION_STATE_ERROR_LOADING -> {

                // Error occurred when loading - try to reload
                setExpansionStateLoading(sectionIndex)
            }
        }
    }

    override fun sectionContentClicked(p0: WorkoutContent?, p1: Int, p2: Int) {

    }


}

class WorkoutSubHeaderViewHolder(val binding: ContentWorkoutSubheaderBinding) : RecyclerView.ViewHolder(binding.root)
class WorkoutHeaderViewHolder(val binding: ContentWorkoutBinding) : RecyclerView.ViewHolder(binding.root)
class WorkoutContentViewHolder(val binding: ContentWorkoutIntervalBinding) : RecyclerView.ViewHolder(binding.root)

//data class WorkoutContent(var workouts: List<WorkoutRepsSets> = listOf())
data class WorkoutContent(var weight: String = "", var reps: String = "", var sets: String = "")

data class WorkoutSubHeader(var expandString: String = "", val minimizeString: String = "", val loadingString: String = "", val errorString: String = "")
data class WorkoutHeader(var exercise: Exercise = Exercise())



@Parcelize
class WorkoutRepsSets(var weight: String = "", var reps: String = "", var sets: String = "") : Parcelable


//class WorkoutNameViewHolder(val view: View) : GroupViewHolder(view), KoinComponent {
//
//    val exerciseViewModel: ExerciseViewModel by inject()
//    var exercise: Exercise = Exercise("", "", "", false)
//    val workoutName = view.findViewById(R.id.tvWorkoutExerciseName) as TextView
//    private val imageIcon = view.findViewById(R.id.imgArrowIndicator) as ImageView
//
//    override fun expand() {
//        val rotate = RotateAnimation(360f, 180f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
//        rotate.duration = 200
//        rotate.fillAfter = true
//        imageIcon.animation = rotate
//    }
//
//    override fun collapse() {
//        val rotate = RotateAnimation(180f, 360f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
//        rotate.duration = 200
//        rotate.fillAfter = true
//        imageIcon.animation = rotate
//    }
//
//
//    init {
//        val workoutSet = view.findViewById(R.id.imgAddWorkoutSet) as ImageButton
//        workoutSet.setOnClickListener {
//
//            //val currentExercise = it.tag as Exercise
//            exerciseViewModel.currentExerciseSelected = exercise
//            Navigation.findNavController(it).navigate(R.id.intervalContainerFragment)

//val view = workoutSet.parent.parent as RecyclerView
//val tag = (workoutSet.parent as ConstraintLayout).tag as WorkoutNameViewHolder
//
//val workoutName = tag.workoutName.text.toString()


//
//            val adapter = view.adapter as  WorkoutsExpandableListAdapter


//
//            val workoutNameExpandableGroup = adapter.getWorkoutNameExpandableGroup(adapterPosition)
//            workoutNameExpandableGroup.items[adapterPosition] = WorkoutRepsSets("30", "20", "2")
//            //.toMutableList().add(adapterPosition, WorkoutRepsSets("30", "20", "2"))
//            //adapter.addWorkoutToGroup(0, workoutRepsSets)
//            adapter.notifyItemChanged(adapterPosition)
//            adapter.notifyGroupDataChanged(adapter)
//            adapter.notifyDataSetChanged()


//val parentView = it.parent as ConstraintLayout


//val vhTag = parentView.tag


//        }
//    }
//
//}

//class WorkoutRepsViewHolder(view: View) : ChildViewHolder(view) {
//    val edtWorkoutWeight = view.findViewById(R.id.edtWorkoutWeight) as TextView
//    val edtworkoutReps = view.findViewById(R.id.edtWorkoutReps) as TextView
//    val edtworkoutSets = view.findViewById(R.id.edtWorkoutSets) as TextView
//}

//    override fun onBindChildViewHolder(
//        holder: WorkoutRepsViewHolder?,
//        flatPosition: Int,
//        group: ExpandableGroup<*>?,
//        childIndex: Int
//    ) {
//
//        val grp = group as WorkoutNameExpandableGroup
//        val repsSets = grp.items[childIndex]
//        holder?.edtworkoutReps?.text = repsSets.reps
//        holder?.edtworkoutSets?.text = repsSets.sets
//        holder?.edtWorkoutWeight?.text = repsSets.weight
//    }
//
//    override fun onBindGroupViewHolder(
//        holder: WorkoutNameViewHolder?,
//        flatPosition: Int,
//        group: ExpandableGroup<*>?
//    ) {
//        val grp = group as WorkoutNameExpandableGroup
//        holder?.workoutName?.text = grp.title
//        holder?.exercise = grp.exercise
//    }
//
//    override fun onCreateChildViewHolder(parent: ViewGroup?, viewType: Int): WorkoutRepsViewHolder {
//        val view = LayoutInflater.from(parent?.context)
//            .inflate(R.layout.content_workout_interval, parent, false)
//
//        val childViewHolder = WorkoutRepsViewHolder(view)
//        //view.tag = childViewHolder
//
//        return childViewHolder
//    }
//
//    override fun onCreateGroupViewHolder(parent: ViewGroup?, viewType: Int): WorkoutNameViewHolder {
//        val view =
//            LayoutInflater.from(parent?.context).inflate(R.layout.content_workout, parent, false)
//
//        val groupViewHolder = WorkoutNameViewHolder(view)
//
//        //view.tag = groupViewHolder
//
//        return groupViewHolder
//    }
//
//    fun addWorkoutToGroup(position: Int, workoutRepsSets: WorkoutNameExpandableGroup) {
//        listGroups.add(position, workoutRepsSets)
//    }
//
//    fun getWorkoutNameExpandableGroup(position: Int): WorkoutNameExpandableGroup {
//        return listGroups[position]
//    }
//
//    fun notifyGroupDataChanged(adapter: ExpandableRecyclerViewAdapter<WorkoutNameViewHolder, WorkoutRepsViewHolder>) {
//        expandableList.expandedGroupIndexes = BooleanArray(groups.size)
//        for (i in groups.indices) {
//            expandableList.expandedGroupIndexes[i] = false
//        }
//    }
