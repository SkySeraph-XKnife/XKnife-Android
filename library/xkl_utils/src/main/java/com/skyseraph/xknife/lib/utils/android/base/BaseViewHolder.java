package com.skyseraph.xknife.lib.utils.android.base;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by SkySeraph on 2015/4/4.
 * 在getView里 ImageView xxView = BaseViewHolder.get(convertView,R.id.xx);
 */
public class BaseViewHolder {
    private BaseViewHolder() {
        super();
    }

    /**
     * Get t.
     *
     * @param <T>  the type parameter
     * @param view the view
     * @param id   the id
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
