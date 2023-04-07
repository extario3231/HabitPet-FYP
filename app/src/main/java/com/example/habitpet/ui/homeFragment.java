package com.example.habitpet.ui;

import static com.example.habitpet.ui.Choosepet.getToBird;
import static com.example.habitpet.ui.Choosepet.getToCat;
import static com.example.habitpet.ui.Choosepet.getToDog;
import static com.example.habitpet.ui.MainActivity.getDb;
import static com.example.habitpet.ui.taskFragment.getIsTaskCompleted;
import static com.example.habitpet.ui.taskFragment.setIsTaskCompleted;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.habitpet.R;
import com.example.habitpet.data.entity.PetProgress;
import com.example.habitpet.data.repo.PetProgressRepo;
import com.example.habitpet.data.SummaryNameMapping;

import java.util.ArrayList;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class homeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters


    private String mParam1;
    private String mParam2;

    // self-defined parameters
    private TextView quotetv;
    private Button buttonch;
    public ProgressBar pgBar;

    private ArrayList<String> quoteArrayList = new ArrayList<>();
    private ListView listView;
    public ImageView imageView;
    private Handler handler = new Handler();

    ArrayList<SummaryNameMapping> showhabitlist = new ArrayList<>();
    private summary habitlist;

    public homeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static homeFragment newInstance(String param1, String param2) {
        homeFragment fragment = new homeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        habitlist = new summary();
        habitlist.setSelection("favourite");

        SummaryAdapter adapter = new SummaryAdapter(getActivity(), (ArrayList<SummaryNameMapping>) habitlist.getSelectedhabitList());
        ListView listView = view.findViewById(R.id.summaryList);
        listView.setTextFilterEnabled(true);
        listView.setAdapter(adapter);

        imageView = view.findViewById(R.id.petImageView);
        pgBar = view.findViewById(R.id.determinateBar);
        Thread thread = new Thread(()-> {
            if (getDb().petProgressDao().isPet("cat") == true) {
                if (getDb().petProgressDao().findLv("cat") == 0) {
                    imageView.setImageResource(R.drawable.testingimage);
                } else {
                    imageView.setImageResource(R.drawable.cat);
                }
                pgBar.setProgress(getDb().petProgressDao().findExp("cat"));
            } else if (getDb().petProgressDao().isPet("dog") == true) {
                if (getDb().petProgressDao().findLv("dog") == 0) {
                    imageView.setImageResource(R.drawable.babydog);
                } else {
                    imageView.setImageResource(R.drawable.dog);
                }
                pgBar.setProgress(getDb().petProgressDao().findExp("dog"));
            } else if (getDb().petProgressDao().isPet("bird") == true) {
                if (getDb().petProgressDao().findLv("bird") == 0) {
                    imageView.setImageResource(R.drawable.babybird);
                } else {
                    imageView.setImageResource(R.drawable.bird);
                }
                pgBar.setProgress(getDb().petProgressDao().findExp("bird"));
            }
        });
        if (getDb().petProgressDao().findExp("cat") >= 100) {
            PetProgress pg = getDb().petProgressDao().findPetName("cat");
            pg.setProgress(0);
            pg.setPetLv(1);
            getDb().petProgressDao().updateProgress(pg);
        }
        else if (getDb().petProgressDao().findExp("dog") >= 100) {
            PetProgress pg = getDb().petProgressDao().findPetName("dog");
            pg.setProgress(0);
            pg.setPetLv(1);
            getDb().petProgressDao().updateProgress(pg);
        }
        else if (getDb().petProgressDao().findExp("bird") >= 100) {
            PetProgress pg = getDb().petProgressDao().findPetName("bird");
            pg.setProgress(0);
            pg.setPetLv(1);
            getDb().petProgressDao().updateProgress(pg);
        }

        thread.start();
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        // generate quote
        generateQuote();

        // choose habit pet
        buttonch = (Button) getView().findViewById(R.id.choosebutton);
        buttonch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Choosepet.class);
                startActivity(intent);
            }
        });

        // show summary list
        listView = getView().findViewById(R.id.summaryList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CalendarDisplay.class);
                startActivity(intent);
            }
        });
    }
    public void generateQuote(){
        quoteArrayList.add("Hard work beats talent when talent doesn’t work hard.");
        quoteArrayList.add("I am who I am today because of the choices I made yesterday.");
        quoteArrayList.add("Whatever we believe about ourselves and our ability comes true for us");
        quoteArrayList.add("If you really look closely, most overnight successes took a long time.");
        quoteArrayList.add("Setting goals is the first step in turning the invisible into the visible.");
        quoteArrayList.add("Impossible is just an opinion.");
        quoteArrayList.add("The greater the difficulty, the more the glory in surmounting it.");

        quotetv = (TextView) getView().findViewById(R.id.quoteTextView);
        int quoteIndex = (int) (Math.random() * 6);
        quotetv.setText(quoteArrayList.get(quoteIndex));
    }
}