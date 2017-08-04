package android.anagramapplication.View;

import android.anagramapplication.Model.RecyclerViewItem;
import android.anagramapplication.R;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nathnael on 8/4/2017.
 */

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder>{


    class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.solution_item);

        }
    }

    List<RecyclerViewItem> mItems;
    Context mContext;
    boolean onLongPressReceived = false;

    public GameAdapter(Context context, List<RecyclerViewItem> items){
        mContext = context;
        mItems = items;
    }

    @Override
    public GameAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.view_item, parent, false);
        //set the margin if any, will be discussed in next blog
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GameAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        RecyclerViewItem item = mItems.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(item.getSolution());
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }



}




