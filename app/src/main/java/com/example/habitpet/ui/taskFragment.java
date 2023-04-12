package com.example.habitpet.ui;

import static android.app.Activity.RESULT_OK;

import static com.example.habitpet.ui.MainActivity.getDb;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.habitpet.R;
import com.example.habitpet.data.NameMapping;
import com.example.habitpet.data.entity.Habit;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link taskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class taskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // self-defined variables
    public habits habitlist;
    private static boolean isTaskCompleted;
    private static boolean isTaskFailed;
    AlertDialog dialog;
    AlertDialog dialog1;
    Button button;
    Button buttonre;
    Button buttomrefresh;

    // One Button
    Button BSelectImage;

    // One Preview Image
    ImageView IVPreviewImage;

    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;
    Uri aUri;
    public taskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment taskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static taskFragment newInstance(String param1, String param2) {
        taskFragment fragment = new taskFragment();
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
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        habitlist = new habits();

        List<Habit> allhab = getDb().habitDao().findAll();
        for(int i=0;i<allhab.size();i++){
            int k=0;
            for (int q=0;q<habitlist.size();q++) {
                if(allhab.get(i).getName().equals(habitlist.get(q))){
                    habitlist.get(q).toggleFavorite();
                    k++;
                }
            }
            if(k==0){
                if(allhab.get(i).getImagePath()!=null)
                    habitlist.addhabitwithimage(allhab.get(i).getName(), Uri.parse(allhab.get(i).getImagePath()));
                else
                    habitlist.addhabit(allhab.get(i).getName());
            }
        }
        habitlist.setSelection("favourite");

        HabitAdapter adapter = new HabitAdapter(getActivity(), (ArrayList<NameMapping>) habitlist.getSelectedhabitList());
        ListView listView = getView().findViewById(R.id.listtask);
        listView.setTextFilterEnabled(true);
        listView.setAdapter(adapter);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setTitle("Add recommend");

        String[] hab = {"Sleep and wake up early","Sport regularly","Football","Basketball","Badminton",
                "Tennis","Table Tennis","Golf","Baseball","Gym","Yoga","Track spending","Check email",
                "Drinking more water","Eating Healthy food","Lazy",
                "No smoking","No alcohol","No soda","No sweets"};
        boolean[] checkedItems = {false, false, false, false, false,
                false, false, false, false, false,
                false, false, false, false, false,
                false, false, false, false, false};
        ArrayList<Integer> change = new ArrayList<Integer>();

        builder1.setMultiChoiceItems(hab, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                // user checked or unchecked a box
                change.add(which);
            }
        });

        // add OK and Cancel buttons
        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user clicked OK
                for (int i = 0;i<change.size();i++){
                    habitlist.setfavor(change.get(i));
                    getDb().habitDao().insert(new Habit(hab[change.get(i)], "", "", false));
                }
                habitlist.setSelection("favourite");
                HabitAdapter adapter = new HabitAdapter(getActivity(), (ArrayList<NameMapping>) habitlist.getSelectedhabitList());
                ListView listView = getView().findViewById(R.id.listtask);
                listView.setTextFilterEnabled(true);
                listView.setAdapter(adapter);

                // notification
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel channel = new NotificationChannel("notifyUser", "notifyUser", NotificationManager.IMPORTANCE_HIGH);
                    NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);
                }
                NotificationCompat.Builder builder2 = new NotificationCompat.Builder(getActivity(), "notifyUser");
                builder2.setContentTitle("Message")
                        .setContentText("Your habits have been set up!")
                        .setSmallIcon(R.drawable.cat)
                        .setAutoCancel(true);

                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity());
                notificationManagerCompat.notify(1, builder2.build());

                Intent intentnoti = new Intent(getActivity(),SetTime.class);
                startActivity(intentnoti);
            }
        });
        builder1.setNegativeButton("Cancel", null);

        // add own habit
        final EditText addHabitText;
        Button addButton;

        // create a alert dialog for entering new habit
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add a new habit");
        // specify the view we are going to access
        View view = getLayoutInflater().inflate(R.layout.add_new_habit, null);

        BSelectImage = view.findViewById(R.id.addImageButton);
        IVPreviewImage = view.findViewById(R.id.imageView2);
        // handle the Choose Image button to trigger
        // the image chooser function
        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        addHabitText = view.findViewById(R.id.addHabit);
        addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aUri == null){
                    habitlist.addhabit(addHabitText.getText().toString());
                    getDb().habitDao().insert(new Habit(addHabitText.getText().toString(), "", "", false));
                }
                else{
                    habitlist.addhabitwithimage(addHabitText.getText().toString(), aUri);
                    getDb().habitDao().insert(new Habit(addHabitText.getText().toString(), "", aUri.toString(), false));
                }
                habitlist.setSelection("favourite");
                dialog.dismiss();
                HabitAdapter adapter = new HabitAdapter(getActivity(), (ArrayList<NameMapping>) habitlist.getSelectedhabitList());
                ListView listView = getView().findViewById(R.id.listtask);
                listView.setTextFilterEnabled(true);
                listView.setAdapter(adapter);

                // notification
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel channel = new NotificationChannel("notifyUser", "notifyUser", NotificationManager.IMPORTANCE_HIGH);
                    NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);
                }
                NotificationCompat.Builder builder2 = new NotificationCompat.Builder(getActivity(), "notifyUser");
                builder2.setContentTitle("Message")
                        .setContentText("Your habits have been set")
                        .setSmallIcon(R.drawable.cat)
                        .setAutoCancel(true);

                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity());
                notificationManagerCompat.notify(1, builder2.build());

                Intent intentnoti = new Intent(getActivity(),SetTime.class);
                startActivity(intentnoti);
            }
        });

        builder.setView(view);
        dialog = builder.create();
        // call a dialog when press the button on the habit screen
        button = getView().findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        //builder1.setView(view1);
        dialog1 = builder1.create();
        buttonre = getView().findViewById(R.id.re);
        buttonre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.show();
            }
        });

        buttomrefresh = getView().findViewById(R.id.refresh);
        buttomrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habitlist.setSelection("favourite");
                HabitAdapter adapter = new HabitAdapter(getActivity(), (ArrayList<NameMapping>) habitlist.getSelectedhabitList());
                ListView listView = getView().findViewById(R.id.listtask);
                listView.setTextFilterEnabled(true);
                listView.setAdapter(adapter);
            }
        });

    }
    public static boolean getIsTaskCompleted() {
        return isTaskCompleted;
    }
    public static void setIsTaskCompleted(boolean value) {
        isTaskCompleted = value;
    }
    public static boolean getIsTaskFailed(){ return isTaskFailed; }
    public static void setIsTaskFailed(boolean value){ isTaskFailed = value; }

    // this function is triggered when
    // the Select Image Button is clicked
    public void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    IVPreviewImage.setImageURI(selectedImageUri);
                    aUri = data.getData();
                }
            }
        }
    }

}