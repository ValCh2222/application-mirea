package com.mirea.chubuka_v_a.mirea_application;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

/**

 */
public class Settings extends Fragment {

    private EditText parameterOneEditText;
    private EditText parameterTwoEditText;
    private SharedPreferences  preferences;

    final String  PARAMETER_ONE = "parameter_one";
    final String  PARAMETER_TWO = "parameter_two";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        parameterOneEditText = view.findViewById(R.id.editTextParameter);
        parameterTwoEditText = view.findViewById(R.id.editTextParameter2);

        view.findViewById(R.id.saveBtn).setOnClickListener(this::onClickSave);

        preferences = getActivity().getPreferences(MODE_PRIVATE);

        return view;
    }


    public void onClickSave(View view){

        try {
            String param1 = parameterOneEditText.getText().toString();
            int param2 =  Integer.parseInt(parameterTwoEditText.getText().toString());

            SharedPreferences.Editor editor = preferences.edit();

            editor.putString(PARAMETER_ONE, param1);
            editor.putInt(PARAMETER_TWO, param2);

            Toast.makeText(getActivity(), "Parameter save", Toast.LENGTH_SHORT).show();
        }catch (NumberFormatException  exception){
            Toast.makeText(getActivity(), "Error in parameter two", Toast.LENGTH_SHORT).show();
        }


    }
}