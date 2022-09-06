package com.example.countdown;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class countdownFragment extends Fragment implements DataRepository.DataLoadListener, DataRepository.DataReloadListener {
    ProgressBar btprogress;
    ViewPager2 viewPager2;
    CountDownAdapter countDownAdapter;
    FloatingActionButton btfloat;
    int currentSize;
    boolean theme=false;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_countdown, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DataRepository.getInstance().setListener(this, this);
        currentSize= DataRepository.getInstance().getList().size();
        countDownAdapter= new CountDownAdapter(DataRepository.getInstance().getList());
        viewPager2=view.findViewById(R.id.pager);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setAdapter(countDownAdapter);
        btprogress=view.findViewById(R.id.btprogress);
        btfloat=view.findViewById(R.id.btfloat);
        btfloat.setOnClickListener(v -> {
            Navigation.findNavController(getView()).navigate(R.id.action_countdownFragment_to_menufragment);
        });
        if (theme){
            btfloat.setEnabled(true);
            btprogress.setVisibility(View.GONE);
        }




    }

    @Override
    public void onResume() {
        super.onResume();
        if(currentSize!=DataRepository.getInstance().getList().size())
            countDownAdapter.notifyDataSetChanged();

    }

    @Override
    public void onThemeLoaded() {
        Log.d("DATA_LOADED","true");
        theme=true;

        btfloat.setEnabled(true);
        btprogress.setVisibility(View.GONE);
    }

    @Override
    public void onDataReceived(ArrayList<EventEntry> list) {
        countDownAdapter.setList(list);
    }
}