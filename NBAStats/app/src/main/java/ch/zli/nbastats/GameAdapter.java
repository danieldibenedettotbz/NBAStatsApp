package ch.zli.nbastats;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewHolder> {

    private final CustomItemClickListener listener;
    private final ArrayList<Game> gameList;

    public GameAdapter(ArrayList<Game> myValues, CustomItemClickListener listener) {
        this.gameList = myValues;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_game, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(listItem);
        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, myViewHolder.getAdapterPosition());
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Game game = gameList.get(position);
        holder.homeTeamNameTextView.setText(game.getHomeTeamName());
        holder.awayTeamNameTextView.setText(game.getAwayTeamName());
        holder.awayTeamScoreTextView.setText(game.getAwayTeamScore());
        holder.homeTeamScoreTextView.setText(game.getHomeTeamScore());
        holder.awayTeamWinsTextView.setText(game.getAwayTeamWins());
        holder.homeTeamWinsTextView.setText(game.getHomeTeamWins());
        if (game.getNugget().isEmpty())
            holder.nuggetTextView.setVisibility(View.GONE);
        else
            holder.nuggetTextView.setText(game.getNugget());
        holder.clockCount.setText(game.getGameTime());
        if (game.isGameActive())
            holder.clockCount.setTextColor(Color.parseColor("#ff0000"));
        else
            holder.clockCount.setTextColor(Color.parseColor("#B4B4B4"));
        if (!game.getHomeTeamScore().isEmpty() && !game.getAwayTeamScore().isEmpty()) {
            if (Integer.parseInt(game.getHomeTeamScore()) > Integer.parseInt(game.getAwayTeamScore())) {
                holder.homeTeamScoreTextView.setTextColor(Color.parseColor("#ffffff"));
                holder.awayTeamScoreTextView.setTextColor(Color.parseColor("#B4B4B4"));
            } else if (Integer.parseInt(game.getHomeTeamScore()) < Integer.parseInt(game.getAwayTeamScore())) {
                holder.homeTeamScoreTextView.setTextColor(Color.parseColor("#B4B4B4"));
                holder.awayTeamScoreTextView.setTextColor(Color.parseColor("#ffffff"));
            } else {
                holder.homeTeamScoreTextView.setTextColor(Color.parseColor("#B4B4B4"));
                holder.awayTeamScoreTextView.setTextColor(Color.parseColor("#B4B4B4"));
            }
        }

    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView homeTeamNameTextView;
        private final TextView awayTeamNameTextView;
        private final TextView awayTeamWinsTextView;
        private final TextView homeTeamWinsTextView;
        private final TextView nuggetTextView;
        private final TextView clockCount;
        private final TextView awayTeamScoreTextView;
        private final TextView homeTeamScoreTextView;

        MyViewHolder(View itemView) {
            super(itemView);
            awayTeamNameTextView = itemView.findViewById(R.id.awayteamname);
            homeTeamNameTextView = itemView.findViewById(R.id.hometeamname);
            awayTeamWinsTextView = itemView.findViewById(R.id.awayteamwins);
            homeTeamWinsTextView = itemView.findViewById(R.id.hometeamwins);
            awayTeamScoreTextView = itemView.findViewById(R.id.awayteamscore);
            homeTeamScoreTextView = itemView.findViewById(R.id.hometeamscore);
            nuggetTextView = itemView.findViewById(R.id.nuggettext);
            clockCount = itemView.findViewById(R.id.gameTime);
        }
    }
}
