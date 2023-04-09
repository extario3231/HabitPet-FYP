package com.example.habitpet.ui;

import static com.example.habitpet.ui.MainActivity.getDb;
import static com.example.habitpet.ui.taskFragment.setIsTaskCompleted;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.habitpet.R;
import com.example.habitpet.data.NameMapping;
import com.example.habitpet.data.entity.PetProgress;
import com.example.habitpet.data.repo.PetProgressRepo;

import java.util.ArrayList;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class HabitAdapter extends ArrayAdapter<NameMapping> {
    private PetProgressRepo progressRepo = new PetProgressRepo(getDb());
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public HabitAdapter(Activity context, ArrayList<NameMapping> showhabitlist){
        super(context,0, showhabitlist);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        final NameMapping currentName = getItem(position);

        TextView default_text_view = listItemView.findViewById(R.id.default_text_view);
        default_text_view.setText(currentName.getHabitname());

        ImageView image = listItemView.findViewById(R.id.image);
        if(currentName.getImageResId() == 0){
            image.setImageURI(currentName.getmUri());
        }
        else{
            image.setImageResource(currentName.getImageResId());
        }

        Button button = listItemView.findViewById(R.id.remove);
        //button.setOnClickListener(new ItemButton_Click(position));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentName.NFavorite();
            }
        });


        ImageButton tickButton = (ImageButton) listItemView.findViewById(R.id.tick);
        Button removeButton = (Button) listItemView.findViewById(R.id.remove);
        tickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You gained 2 EXP for your pet!",
                        Toast.LENGTH_SHORT).show();
//                Disposable updateProgress = progressRepo.getProgress().subscribe(p -> {
//                           p.setProgress(p.getProgress() + 2);
//                            Disposable disposable = progressRepo.update(p).subscribe(() -> Log.i(TAG, "progress updated."));
//                            compositeDisposable.add(disposable);
//                        }

//                );
                PetProgress pp = getDb().petProgressDao().findPetName("cat");
                PetProgress pp2 = getDb().petProgressDao().findPetName("dog");
                PetProgress pp3 = getDb().petProgressDao().findPetName("bird");
                if(getDb().petProgressDao().isPet("cat") == true){
                    pp.setProgress(getDb().petProgressDao().findExp("cat")+2);
                    getDb().petProgressDao().updateProgress(pp);
                }
                else if(getDb().petProgressDao().isPet("dog") == true){
                    pp2.setProgress(getDb().petProgressDao().findExp("dog")+2);
                    getDb().petProgressDao().updateProgress(pp2);
                }
                else if(getDb().petProgressDao().isPet("bird") == true){
                    pp3.setProgress(getDb().petProgressDao().findExp("bird")+2);
                    getDb().petProgressDao().updateProgress(pp3);
                }
//                compositeDisposable.add(updateProgress);
                setIsTaskCompleted(true);
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You lost 2 EXP for your pet!",
                        Toast.LENGTH_SHORT).show();

                PetProgress pp = getDb().petProgressDao().findPetName("cat");
                PetProgress pp2 = getDb().petProgressDao().findPetName("dog");
                PetProgress pp3 = getDb().petProgressDao().findPetName("bird");

                if(getDb().petProgressDao().isPet("cat") == true){
                    pp.setProgress(getDb().petProgressDao().findExp("cat")-2);
                    getDb().petProgressDao().updateProgress(pp);
                }
                else if(getDb().petProgressDao().isPet("dog") == true){
                    pp2.setProgress(getDb().petProgressDao().findExp("dog")-2);
                    getDb().petProgressDao().updateProgress(pp2);
                }
                else if(getDb().petProgressDao().isPet("bird") == true){
                    pp3.setProgress(getDb().petProgressDao().findExp("bird")-2);
                    getDb().petProgressDao().updateProgress(pp3);
                }

                setIsTaskCompleted(false);
            }
        });

        return listItemView;
    }
    /*
    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
    */
}

