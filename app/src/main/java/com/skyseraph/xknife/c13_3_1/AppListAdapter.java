package com.skyseraph.xknife.c13_3_1;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyseraph.xknife.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SkySeraph on 2016/5/4.
 */
public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.ViewHolder> {

    private List<AppInfo> appInfoList;

    /**
     * Instantiates a new App list adapter.
     *
     * @param appInfoList the app info list
     */
    public AppListAdapter(List<AppInfo> appInfoList) {
        this.appInfoList = appInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_app_list, parent, false);
        return new ViewHolder(rootView);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageView.setBackground(appInfoList.get(position).getAppIcon());
        holder.textView.setText(appInfoList.get(position).getAppName());
    }

    @Override
    public int getItemCount() {
        return appInfoList.size();
    }

    /**
     * The type View holder.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * The Text view.
         */
        @BindView(R.id.tv)
        TextView textView;
        /**
         * The Image view.
         */
        @BindView(R.id.iv)
        ImageView imageView;

        /**
         * Instantiates a new View holder.
         *
         * @param itemView the item view
         */
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
