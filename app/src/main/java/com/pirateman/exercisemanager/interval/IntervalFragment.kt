package com.pirateman.exercisemanager.interval

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pirateman.exercisemanager.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [IntervalFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [IntervalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IntervalFragment : androidx.fragment.app.Fragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_interval, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //        ImageButton imgDeleteInterval = view.findViewById(R.id.imgDeleteInterval);
//        imgDeleteInterval.setOnClickListener(this);
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //fragmentManager.putFragment(outState, "IntervalFragment", this)
    }

    fun onButtonPressed(uri: Uri?) {
//        if (mListener != null) {
//            mListener!!.onFragmentInteraction(uri)
//        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onClick(v: View) {
        //this.fragmentManager.beginTransaction().detach(this).commit()
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri?)
    }

    companion object {

        fun newInstance(): IntervalFragment {
            return IntervalFragment()
        }
    }
}