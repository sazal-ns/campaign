/*
 * Copyright By Noor Nabiul Alam Siddiqui  (c) 2017.
 *  www.fb.com/sazal.ns
 */

package com.rtsoftbd.siddiqui.engrhossain;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rtsoftbd.siddiqui.engrhossain.helper.LoadDropDown;
import com.rtsoftbd.siddiqui.engrhossain.model.Union_MS;
import com.rtsoftbd.siddiqui.engrhossain.model.Upozila_MS;
import com.rtsoftbd.siddiqui.engrhossain.model.Word_MS;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SendSmsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SendSmsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.upozilaSpinner)
    AppCompatSpinner ms_UpozilaSpinner;
    Unbinder unbinder;
    @BindView(R.id.unionSpinner)
    AppCompatSpinner ms_UnionSpinner;
    @BindView(R.id.wordSpinner)
    AppCompatSpinner ms_WordSpinner;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SendSmsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SendSmsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SendSmsFragment newInstance(String param1, String param2) {
        SendSmsFragment fragment = new SendSmsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send_sms, container, false);
        unbinder = ButterKnife.bind(this, view);
        new LoadDropDown(getContext());

        ArrayAdapter<Upozila_MS> upozilaAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, Upozila_MS.getUpozila_msList());
        upozilaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ms_UpozilaSpinner.setAdapter(upozilaAdapter);

        ArrayAdapter<Union_MS> union_msArrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item, Union_MS.getUnion_msList());
        union_msArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ms_UnionSpinner.setAdapter(union_msArrayAdapter);

        ArrayAdapter<Word_MS> word_msArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, Word_MS.getWord_msList());
        word_msArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ms_WordSpinner.setAdapter(word_msArrayAdapter);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
