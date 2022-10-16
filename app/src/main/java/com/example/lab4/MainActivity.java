package com.example.lab4;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    int comp_num;
    int maxAttempts;
    EditText editNum;
    TextView hint,attempts_left, userName;
    Button restart, guess, buttonShare, butNotes, butName;
    private ViewModel model;
    private static final int NOTIFY_ID = 404;
    private static String CHANNEL_ID = "Victory Channel";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public void SendNotification(String str)
    {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this,CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle(getString(R.string.game_result))
                        .setContentText(str)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManager notificationManager =
                (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        createChannelIfNeeded(notificationManager);
        notificationManager.notify(NOTIFY_ID, builder.build());
    }
    public void createChannelIfNeeded(NotificationManager notificationManager)
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,CHANNEL_ID,NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.RedContextMenuItem:
                model.setHintColor("#C83737");
                hint.setTextColor(Color.parseColor("#C83737"));
                break;
            case R.id.VioletContextMenuItem:
                model.setHintColor("#6E27B3");
                hint.setTextColor(Color.parseColor("#6E27B3"));
                break;
        }
        return super.onContextItemSelected(item);
    }

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.settingMenuItem:
                settingsHandler(item.getActionView());
                break;
            case R.id.aboutMenuItem:
                Intent intent = new Intent(this, DeveloperActivity.class);
                if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG,"Угадай число. onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG,"Угадай число. onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG,"Угадай число. onDestroy()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG,"Угадай число. onResume()");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(LOG_TAG,"Угадай число. onSaveInstanceState()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG,"Угадай число. onRestart()");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(LOG_TAG,"Угадай число. OnCreate()");
        model = new ViewModelProvider(this).get(ViewModel.class);
        editNum = (EditText) findViewById(R.id.edit_num);
        userName = (TextView) findViewById(R.id.userName);
        hint = (TextView) findViewById(R.id.show_hint);attempts_left = (TextView) findViewById(R.id.show_attempts_left);
        restart = (Button) findViewById(R.id.button_restart); guess = (Button) findViewById(R.id.button_guess);
        buttonShare = (Button) findViewById(R.id.button_Share);butNotes = (Button) findViewById(R.id.notes);
        butName = (Button) findViewById(R.id.buttonName);
        guess.setOnClickListener(this::guess);
        restart.setOnClickListener(this::restart);
        buttonShare.setOnClickListener(this::Share);
        butNotes.setOnClickListener(this::SaveNotes);
        butName.setOnClickListener(this::ChangeName);
        comp_num=model.getComp_num();
        maxAttempts= model.getMaxAttempts();
        int count = model.getCount(), butId = model.getButId();
        if(count==0||butId==R.string.show_win_label)guess.setEnabled(false);
        guess.setText(getString(butId));
        attempts_left.setText(Integer.toString(count));
        hint.setText(getString(model.getMassId()));
        hint.setTextColor(Color.parseColor(model.getHintColor()));
        registerForContextMenu(hint);
        userName.setText(model.getName());
    }
    public void settingsHandler(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.settings);
        builder.setItems(R.array.diaps_array, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch(i)
                {
                    case 0: maxAttempts =5;  GuessNum.setMin(10); GuessNum.setMax(99);
                        model.setBaseMassId(R.string.show_hint_label);
                        break;
                    case 1: maxAttempts =7; GuessNum.setMin(100); GuessNum.setMax(999);
                        model.setBaseMassId(R.string.show_hint_label3);
                        break;
                    case 2: maxAttempts =10; GuessNum.setMin(1000); GuessNum.setMax(9999);
                        model.setBaseMassId(R.string.show_hint_label4);
                        break;
                }
                model.setMaxAttempts(maxAttempts);
                restart(view);
            }
        });
        builder.setNegativeButton(R.string.Negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
    public void guess(View view) {
        try {

            int nowNum = Integer.parseInt(editNum.getText().toString());
            int count = Integer.parseInt(attempts_left.getText().toString());
            if(count > 0) {
                count--;
                if (nowNum == comp_num) {
                    guess.setText(R.string.show_win_label);
                    model.setButId(R.string.show_win_label);
                    guess.setEnabled(false);
                    SendNotification(getString(R.string.win_label));
                    Toast toast = Toast.makeText(this, R.string.win_label, Toast.LENGTH_LONG);
                    toast.show();
                }else {
                        if (nowNum > comp_num) {
                            model.setMassId(R.string.less);
                        }else model.setMassId(R.string.more);
                    hint.setText(getString(model.getMassId()));
                    if(count==0){guess.setEnabled(false);
                        SendNotification(getString(R.string.attempts_are_over));
                        Toast toast = Toast.makeText(this, R.string.attempts_are_over, Toast.LENGTH_LONG);
                        toast.show();}
                }
                attempts_left.setText(Integer.toString(count));
                model.setCount(count);
            }
            editNum.setText("");
        }catch(NumberFormatException exception)
        {
            editNum.setError(getText(R.string.error));
        }
    }
    public void Share(View view)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        int id = model.getButId();
        if(id!=R.string.show_win_label)id=R.string.not_guessed;
        Date date = new Date();
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.mystery_number)+ " " + model.getComp_num() +'\n'
                +getString(R.string.attempt_number) +" "+ (model.getMaxAttempts() -model.getCount())+'\n' + getString(id) + '\n'+date);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose client:"));
    }
    public void ChangeName(View view)
    {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (data == null) {return;}
        String name = data.getStringExtra("name");
        userName.setText(name);
        model.setName(name);
    }
    public void SaveNotes(View view)
    {

        Intent intent = new Intent(Intent.ACTION_SEND);
        int id = model.getButId();
        if(id!=R.string.show_win_label)id=R.string.not_guessed;
        Date date = new Date();
        intent.setPackage("com.miui.notes");
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.mystery_number)+ " " + model.getComp_num() +'\n'
                +getString(R.string.attempt_number) +" "+ (model.getMaxAttempts() -model.getCount())+'\n' + getString(id) + '\n'+date);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "Notes"));
    }

    public void restart(View view) {
        guess.setText(R.string.button_guess_label);
        hint.setText(getString(model.getBaseMassId()));
        attempts_left.setText(Integer.toString(maxAttempts));
        editNum.setText("");
        comp_num=GuessNum.rnd_comp_num();
        model.setButId(R.string.button_guess_label);
        model.setMassId(model.getBaseMassId());
        model.setCount(maxAttempts);
        model.setComp_num(comp_num);
        guess.setEnabled(true);

    }
}