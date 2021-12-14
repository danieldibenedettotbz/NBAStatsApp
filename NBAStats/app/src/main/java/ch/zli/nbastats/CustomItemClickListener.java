package ch.zli.nbastats;

import android.view.View;

public interface CustomItemClickListener {
    void onItemClick(View v, int position);

    void onItemLongClick(View v, int position);
}
