/*
 * Copyright By Noor Nabiul Alam Siddiqui  (c) 2017.
 *  www.fb.com/sazal.ns
 */

package com.rtsoftbd.siddiqui.campaign;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rtsoftbd.siddiqui.campaign.model.ApiUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.nameEditText) EditText ms_NameEditText;
    @BindView(R.id.phoneEditText) EditText ms_PhoneEditText;
    @BindView(R.id.emailEditText) EditText ms_EmailEditText;
    @BindView(R.id.maleRadioButton) RadioButton ms_MaleRadioButton;
    @BindView(R.id.femaleRadioButton) RadioButton ms_FemaleRadioButton;
    @BindView(R.id.upozilaSpinner) Spinner ms_UpozilaSpinner;
    @BindView(R.id.unionSpinner) Spinner ms_UnionSpinner;
    @BindView(R.id.wordSpinner) Spinner ms_WordSpinner;
    @BindView(R.id.submitButton) AppCompatButton ms_SubmitButton;

    private String name, phone, email, gander, upozila, union, word;

    private List<String> upozilas = new ArrayList<>();
    private List<String> unions = new ArrayList<>();
    private List<String> words = new ArrayList<>();

    private ArrayAdapter<String> spinnerAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RegFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegFragment newInstance(String param1, String param2) {
        RegFragment fragment = new RegFragment();
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
        View view = inflater.inflate(R.layout.fragment_reg, container, false);
        ButterKnife.bind(this, view);


        loadSpinners(ApiUrl.TABLE_UPOZILA, 1);

        loadSpinners(ApiUrl.TABLE_UNION, 2);

        loadSpinners(ApiUrl.TABLE_WORD, 3);

        ms_UpozilaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                upozila = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ms_UnionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                union = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ms_WordSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                word = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ms_SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickData();
                submitData();
            }
        });

        return view;
    }

    private void loadSpinners(final String key, final int who) {
       StringRequest request = new StringRequest(Request.Method.POST, ApiUrl.BASE_URL, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               try {
                   JSONObject jsonObject = new JSONObject(response);

                   Iterator keys = jsonObject.keys();
                   while (keys.hasNext()) {
                       String dynamicKey = (String) keys.next();

                       if (!dynamicKey.contains("error")) {
                           JSONObject object = jsonObject.getJSONObject(dynamicKey);

                           if (who == 1) {
                               upozilas.add(object.getString("upozila"));
                           } else if (who == 2) {
                               unions.add(object.getString("union_name"));

                           } else if (who == 3) {
                               words.add(object.getString("word"));
                           }
                       }
                   }

                   if (who == 1) {
                       spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, upozilas);
                       ms_UpozilaSpinner.setAdapter(spinnerAdapter);
                   } else if (who == 2) {
                       spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, unions);
                       ms_UnionSpinner.setAdapter(spinnerAdapter);
                   } else if (who == 3) {
                       spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, words);
                       ms_WordSpinner.setAdapter(spinnerAdapter);
                   }

                   spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
           }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Log.e("Error",error.toString());
           }
       }){
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String, String> params = new HashMap<>();
               params.put(ApiUrl.KEY_DATA_REQUEST, key);

               return params;
           }
       };

        Volley.newRequestQueue(getContext()).add(request);
    }


    private void submitData() {
        if (!validate()) return;

        StringRequest request = new StringRequest(Request.Method.POST, ApiUrl.BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.contains("false"))
                new AlertDialog.Builder(getContext())
                        .setTitle(getResources().getString(R.string.replay))
                        .setMessage(getResources().getString(R.string.trueAns))
                        .show();
                else
                    new AlertDialog.Builder(getContext())
                        .setTitle(getResources().getString(R.string.replay))
                        .setMessage(getResources().getString(R.string.falseAnd))
                        .show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(ApiUrl.KEY_STORE_REQUEST, ApiUrl.TABLE_VOTER_INFO);
                params.put(ApiUrl.KEY_USER_NAME, name);
                params.put(ApiUrl.KEY_PHONE, phone);
                params.put(ApiUrl.KEY_EMAIL, email);
                params.put(ApiUrl.KEY_GENDER, gander);
                params.put(ApiUrl.KEY_UPOZILA, upozila);
                params.put(ApiUrl.KEY_UNION, union);
                params.put(ApiUrl.KEY_WORD, word);
                params.put(ApiUrl.KEY_TYPE, "user");

                return params;
            }
        };

        Volley.newRequestQueue(getContext()).add(request);

    }

    private boolean validate() {
        boolean ok = true;

        if (name.isEmpty() || name.length() > 256){
            ms_NameEditText.setError(getResources().getString(R.string.nameError));
            ok = false;
        } else ms_NameEditText.setError(null);

        if (phone.isEmpty() || phone.length()!= 11){
            ms_PhoneEditText.setError(getResources().getString(R.string.phoneError));
            ok = false;
        } else ms_PhoneEditText.setError(null);

        if (email.isEmpty() || !email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            ms_EmailEditText.setError(getResources().getString(R.string.emailError));
            ok = false;
        }else ms_EmailEditText.setError(null);

        if (gander.isEmpty()){
            ms_MaleRadioButton.setBackgroundColor(getResources().getColor(R.color.material_red_900));
            ms_FemaleRadioButton.setBackgroundColor(getResources().getColor(R.color.material_red_900));
            ok = false;
        }else {
            ms_MaleRadioButton.setBackgroundColor(getResources().getColor(android.R.color.white));
            ms_FemaleRadioButton.setBackgroundColor(getResources().getColor(android.R.color.white));
        }

        return ok;
    }



    private void pickData() {
        name = ms_NameEditText.getText().toString().trim();
        phone = ms_PhoneEditText.getText().toString().trim();
        email = ms_EmailEditText.getText().toString().trim();
        if (ms_MaleRadioButton.isChecked()) gander = "male";
        else if (ms_FemaleRadioButton.isChecked()) gander = "female";
        else gander = "";
    }


    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @OnClick({R.id.maleRadioButton, R.id.femaleRadioButton})
    public void onRadioButtonClicked(View view) {

        ms_MaleRadioButton.setBackgroundColor(getResources().getColor(android.R.color.white));
        ms_FemaleRadioButton.setBackgroundColor(getResources().getColor(android.R.color.white));
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
