package com.example.habittrackerprojectjavaversion.ui;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.annimon.stream.internal.Compat;
import com.example.habittrackerprojectjavaversion.R;
import com.example.habittrackerprojectjavaversion.data.NameMapping;

import java.util.ArrayList;

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

    public habits habitlist;
    private static boolean isTaskCompleted;
    private static boolean isTaskFailed;

    // self-defined variables
    AlertDialog dialog;
    AlertDialog dialog1;
    Button button;
    Button buttonre;

    Button buttomrefresh;

    ImageButton tickbutton;
    ImageButton crossbutton;
    ProgressBar expbar;

    ArrayList<NameMapping> showhabitlist = new ArrayList<NameMapping>();
    //private habits habitlist;

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

        habitlist.setSelection("favourite");

        HabitAdapter adapter = new HabitAdapter(getActivity(), (ArrayList<NameMapping>) habitlist.getSelectedhabitList());
        ListView listView = getView().findViewById(R.id.listtask);
        listView.setTextFilterEnabled(true);
        listView.setAdapter(adapter);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setTitle("Add recommend");
        //habitlist.setSelection("All");
        //RecomAdapter recomAdapter = new RecomAdapter(getActivity(), (ArrayList<NameMapping>) habitlist.getSelectedhabitList());
        //ListView listView1 = getView().findViewById(R.id.recom);
        //listView1.setTextFilterEnabled(true);
        //listView1.setAdapter(recomAdapter);
        //View view1 = getLayoutInflater().inflate(R.layout.activity_habit, null);
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
                }
                habitlist.setSelection("favourite");
                HabitAdapter adapter = new HabitAdapter(getActivity(), (ArrayList<NameMapping>) habitlist.getSelectedhabitList());
                ListView listView = getView().findViewById(R.id.listtask);
                listView.setTextFilterEnabled(true);
                listView.setAdapter(adapter);

                // notification
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel channel = new NotificationChannel("notifyUser", "notifyUser", NotificationManager.IMPORTANCE_DEFAULT);
                    NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);
                }
                NotificationCompat.Builder builder2 = new NotificationCompat.Builder(getActivity(), "notifyUser");
                builder2.setContentTitle("Hey")
                        .setContentText("I am hungry")
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

        Button addrecom;

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
                if(aUri == null){habitlist.addhabit(addHabitText.getText().toString());}
                else{habitlist.addhabitwithimage(addHabitText.getText().toString(), aUri);}
                habitlist.setSelection("favourite");
                dialog.dismiss();
                HabitAdapter adapter = new HabitAdapter(getActivity(), (ArrayList<NameMapping>) habitlist.getSelectedhabitList());
                ListView listView = getView().findViewById(R.id.listtask);
                listView.setTextFilterEnabled(true);
                listView.setAdapter(adapter);

                // notification
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel channel = new NotificationChannel("notifyUser", "notifyUser", NotificationManager.IMPORTANCE_DEFAULT);
                    NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);
                }
                NotificationCompat.Builder builder2 = new NotificationCompat.Builder(getActivity(), "notifyUser");
                builder2.setContentTitle("Hey")
                        .setContentText("I am hungry")
                        .setSmallIcon(R.drawable.cat)
                        .setAutoCancel(true);


                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity());
                notificationManagerCompat.notify(1, builder2.build());

                Intent intentnoti = new Intent(getActivity(),SetTime.class);
                startActivity(intentnoti);



            }
        });

        /*addrecom = view1.findViewById(R.id.addrecom);
        addrecom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog1.dismiss();
            }
        });*/

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
        //tick & cross not working now

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