package com.example.countdown;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class menufragment extends Fragment {
    RecyclerView recyclerView;

    EditText name;
    EditText date;
    ImageView imageCount;
    Button add;
    RadioAdapter radioAdapter;

    int selectedTheme = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menufragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = view.findViewById(R.id.tvname);
        date = view.findViewById(R.id.tvdate);
        add = view.findViewById(R.id.btaddevent);
        imageCount = view.findViewById(R.id.imageCount);
        recyclerView=view.findViewById(R.id.rec1);
        RecyclerView.LayoutManager manager= new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(manager);
        radioAdapter=new RadioAdapter(DataRepository.getInstance().getThemeList());
        recyclerView.setAdapter(radioAdapter);
        date.setOnClickListener(v -> {
            //
            DatePickerDialog dialog = new DatePickerDialog(getContext());
            dialog.setOnDateSetListener((view1, year, month, dayOfMonth) -> {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), (view2, hourOfDay, minute) -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);

                    date.setText(DateUtils.getDate(calendar));

                }, 0, 0, true);
                timePickerDialog.show();
            });
            dialog.show();
        });

        add.setOnClickListener(v -> {
            String userinput = name.getText().toString();
            name.setText("");
            String userinput2 = date.getText().toString();
            date.setText("");

            DataRepository.getInstance().addData(userinput, userinput2, radioAdapter.selectedTheme);
            Navigation.findNavController(getView()).popBackStack();

        });

    }


}