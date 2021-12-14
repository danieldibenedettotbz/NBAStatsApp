package ch.zli.nbastats;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static Calendar myCalendar;
    public TextView noteTextView;
    public TextView textView;
    public String url;
    private View dateChooser;
    private View loadingPanel;
    private RecyclerView myRecyclerView;
    private ArrayList<Game> gamesArray;
    private GameCardsCreator gameCardsCreator;
    private JSONObject jsonObject;

    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) NbaApp.getCurrentActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void setUrl(final String dateUrl) {
        noteTextView.setVisibility(View.GONE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                url = "http://data.nba.net/10s/prod/v1/" + dateUrl + "/scoreboard.json";
                final RequestQueue requestQueue = Volley.newRequestQueue(NbaApp.getCurrentActivity());
                JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        jsonObject = response;
                        gameCardsCreator = new GameCardsCreator(jsonObject);
                        if (gameCardsCreator.isGameNight()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    gameCardsCreator.populateCards();
                                    setCardsCreator();
                                }
                            });
                        } else {
                            noteTextView.setVisibility(View.VISIBLE);
                            noteTextView.setText(R.string.no_games_tonight);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        noteTextView.setVisibility(View.VISIBLE);
                        noteTextView.setText(R.string.error_getting_info);
                    }
                });
                requestQueue.add(objectRequest);
            }
        }).start();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setCardsCreator() {
        gamesArray = new ArrayList<>();
        gamesArray = gameCardsCreator.getGameArrayList();
        GameAdapter adapter = new GameAdapter(gamesArray, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (isNetworkConnected()) {
                    Intent myIntent = new Intent(MainActivity.this, GameActivity.class);
                    Bundle extras = new Bundle();
                    extras.putString("gameDate", gamesArray.get(position).getGameDate());
                    extras.putString("gameId", gamesArray.get(position).getGameId());
                    extras.putString("homeTeamWins", gamesArray.get(position).getHomeTeamWins());
                    extras.putString("awayTeamWins", gamesArray.get(position).getAwayTeamWins());
                    extras.putBoolean("isGameActivated", gamesArray.get(position).isGameActive());
                    extras.putString("homeTeamName", gamesArray.get(position).getHomeTeamName());
                    extras.putString("awayTeamName", gamesArray.get(position).getAwayTeamName());
                    extras.putBoolean("isGameOver", gamesArray.get(position).isGameOver());
                    myIntent.putExtras(extras);
                    MainActivity.this.startActivity(myIntent);
                } else {
                    Toast.makeText(NbaApp.getCurrentActivity(), "Check your internet connection", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItemLongClick(View v, int position) {
            }
        });
    }


    private String getCurrentTextViewDate(Calendar calendar) {
        String myFormat = "dd/MMM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        return "Date: " + sdf.format(calendar.getTime());
    }

    public void changeDateYesterday() {
        noteTextView.setVisibility(View.GONE);
        myCalendar.add(Calendar.DAY_OF_YEAR, -1);
        String myFormat = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        textView.setText(getCurrentTextViewDate(myCalendar));
        setUrl(sdf.format(myCalendar.getTime()));
    }

    public void changeDateTomorrow() {
        noteTextView.setVisibility(View.GONE);
        myCalendar.add(Calendar.DAY_OF_YEAR, 1);
        String myFormat = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        textView.setText(getCurrentTextViewDate(myCalendar));
        setUrl(sdf.format(myCalendar.getTime()));
    }


    public void changeDate(View view) {
        switch (view.getId()) {
            case (R.id.leftArrow):
                changeDateYesterday();
                break;
            case (R.id.rightArrow):
                changeDateTomorrow();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotification();
    }

    public void createNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("NBAStats")
                .setContentText("You have opened the app!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        builder.setVibrate(new long[] {1000});

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());

    }
}