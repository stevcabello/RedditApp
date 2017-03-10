package com.rappi.test.redditapp.adapters;


import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rappi.test.redditapp.utils.Globals;
import com.rappi.test.redditapp.R;
import com.rappi.test.redditapp.fragments.DescriptionFragment;
import com.rappi.test.redditapp.models.Feed;

import java.util.ArrayList;

/**
 * Adapter for the listview of feeds in the FeedFragment
 */
public class FeedAdapter extends ArrayAdapter<Feed> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<Feed> data = new ArrayList();

    public FeedAdapter(Context context, int resource, ArrayList<Feed> data) {
        super(context, resource,data);
        this.layoutResourceId = resource;
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Feed getItem (int position){
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public int getPosition(Feed item) {
        return super.getPosition(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((FragmentActivity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) row.findViewById(R.id.tv_title);
            holder.user = (TextView) row.findViewById(R.id.tv_user);
            holder.subscribers = (TextView) row.findViewById(R.id.tv_subscribers);
            holder.comments = (TextView) row.findViewById(R.id.tv_comments);


            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        final Feed item = (Feed) data.get(position);

        holder.title.setText(item.getTitle());
        holder.user.setText(item.getUser());
        holder.subscribers.setText(String.valueOf(item.getSubscribers()));
        holder.comments.setText(String.valueOf(item.getComments()));

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDescription(item.getTitle(),item.getDetail());
            }
        });

        return row;
    }

    static class ViewHolder {
        TextView title;
        TextView user;
        TextView subscribers;
        TextView comments;
        TextView description;

    }

    /**
     * Show the description of the feed item passed as @param.
     * @param description
     */
    public void showDescription(String title,String description) {
        FragmentTransaction fragmentTransaction = Globals.mainActivity.getSupportFragmentManager().beginTransaction();
        DescriptionFragment df= DescriptionFragment.newInstance(title,description);
        fragmentTransaction.replace(R.id.fly_feed_fragment, df);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
