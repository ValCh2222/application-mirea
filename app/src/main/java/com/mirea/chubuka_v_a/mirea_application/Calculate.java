package com.mirea.chubuka_v_a.mirea_application;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Calculate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calculate extends Fragment {

    private TextView result;
    private String prevNumber = "";
    private String currentNumber = "";
    private Operation prevOperation;
    private boolean operationFinished = false;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LayoutInflater inflater;
    private ViewGroup container;
    private Bundle savedInstanceState;

    public Calculate() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Calculate newInstance(String param1, String param2) {
        Calculate fragment = new Calculate();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_calculate, container, false);
        result = (TextView) view.findViewById(R.id.textView);
        view.findViewById(R.id.one_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.two_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.three_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.four_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.five_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.six_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.seven_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.eight_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.nine_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.null_btm).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.point_btn).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.equals_btn).setOnClickListener(this::onEqualsButtonClick);
        view.findViewById(R.id.div_btn).setOnClickListener(this::onDivideButtonClick);
        view.findViewById(R.id.mltp_btn).setOnClickListener(this::onMultiplicativeButtonClick);
        view.findViewById(R.id.min_btn).setOnClickListener(this::onSubtractButtonClick);
        view.findViewById(R.id.sum_btn).setOnClickListener(this::onAddButtonClick);
        view.findViewById(R.id.ac_btn).setOnClickListener(this::onClearButtonClick);

        return view;
    }


    private void onNumberButtonClick(View view){
        if(operationFinished){
            currentNumber = "";
            operationFinished = false;
        }
        Button button = (Button) view;
        if(currentNumber.equals("") && button.getText().toString().equals(".")){
            currentNumber += "0";
        }
        currentNumber += button.getText().toString();
        result.setText(currentNumber);
    }
    private void performOperation(){
        if (currentNumber.equals("")) currentNumber = "0";
        double resultNumber = 0.0;
        if (!prevNumber.equals("")) {
            if (prevOperation == Operation.PLUS) {
                resultNumber = Double.parseDouble(prevNumber)
                        + Double.parseDouble(currentNumber);

            } else if (prevOperation == Operation.DIVIDE) {
                if ((Double.parseDouble(currentNumber))==0){
                resultNumber=0;}

                resultNumber = Double.parseDouble(prevNumber)
                        / Double.parseDouble(currentNumber);

            } else if (prevOperation == Operation.MULTI) {
                resultNumber = Double.parseDouble(prevNumber)
                        * Double.parseDouble(currentNumber);

            } else if (prevOperation == Operation.MINUS) {
                resultNumber = Double.parseDouble(prevNumber)
                        - Double.parseDouble(currentNumber);
            }
            resultNumber = new BigDecimal(resultNumber)
                    .setScale(2, RoundingMode.HALF_EVEN). doubleValue();
            prevNumber = String.valueOf(resultNumber);
            result.setText(prevNumber);
            currentNumber = "";
            operationFinished = true;
        }
    }


    private void performAction(Operation operation){
        if(prevNumber.equals("")){
            prevOperation = operation;
            prevNumber = currentNumber;
            currentNumber = "";
            result.setText("");
        } else {
            performOperation();
            prevOperation = operation;
        }
    }

    private void onEqualsButtonClick(View view){
        performOperation();
        prevNumber = "";
        currentNumber = result.getText().toString();
    }

    private void onDeleteButtonClick(View view){
        if (currentNumber.length() > 0){
            currentNumber = currentNumber.substring(0, currentNumber.length()-1);
            result.setText(currentNumber);
        }
    }

    private void onAddButtonClick(View view){
        performAction(Operation.PLUS);
    }

    private void onSubtractButtonClick(View view){
        performAction(Operation.MINUS);
    }

    private void onDivideButtonClick(View view){
        performAction(Operation.DIVIDE);
    }

    private void onMultiplicativeButtonClick(View view){
        performAction(Operation.MULTI);
    }

    private void onClearButtonClick(View view){
        prevNumber = "";
        currentNumber = "";
        result.setText(currentNumber);
        prevOperation = null;
    }



}