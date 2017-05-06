package com.skyseraph.xknife.c13_3_2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skyseraph.xknife.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SkySeraph on 2016/5/4.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.Holder> {
    private List<DataModel.SubjectsEntity> mData;

    /**
     * Instantiates a new Data adapter.
     *
     * @param data the data
     */
    public DataAdapter(List<DataModel.SubjectsEntity> data) {
        this.mData = data;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_douban_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        DataModel.SubjectsEntity entity = this.mData.get(position);
        holder.tvTitle.setText(entity.getTitle());

        StringBuffer name = new StringBuffer();
        for (DataModel.SubjectsEntity.CastsEntity cast : entity.getCasts()) {
            name.append(cast.getName());
            name.append("、");
        }
        name.deleteCharAt(name.length() - 1);
        holder.tvName.setText(String.format(holder.itemView.getResources().getString(R.string.text_moive_actor_name), name));
    }

    @Override
    public int getItemCount() {
        return this.mData.size();
    }

    /**
     * The type Holder.
     */
    static class Holder extends RecyclerView.ViewHolder {
        /**
         * The Text view.
         */
        @BindView(R.id.tv_title)
        TextView tvTitle;
        /**
         * The Image view.
         */
        @BindView(R.id.tv_name)
        TextView tvName;

        /**
         * Instantiates a new Holder.
         *
         * @param itemView the item view
         */
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}