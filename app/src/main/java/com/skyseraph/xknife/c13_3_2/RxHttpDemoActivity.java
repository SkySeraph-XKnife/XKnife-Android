package com.skyseraph.xknife.c13_3_2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.skyseraph.xknife.R;
import com.skyseraph.xknife.lib.xnet.subscriber.RxProgressSubscriber;
import com.skyseraph.xknife.lib.xnet.subscriber.RxSubscriber;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by SkySeraph on 2016/5/4.
 */
public class RxHttpDemoActivity extends Activity {

    /**
     * The Rl content.
     */
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    /**
     * The Rv.
     */
    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxhttp);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void bindData(List<DataModel.SubjectsEntity> data) {
        DataAdapter adapter = new DataAdapter(data);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    /**
     * Request.
     *
     * @param v the v
     */
    public void request(View v) {

        requestByProgress();
    }

    /**
     * Request by progress.
     */
    public void requestByProgress() {

        Subscriber<DataModel> subscriber = new RxProgressSubscriber<DataModel>(this) {


            @Override
            public void _onNext(Object o) {
                bindData(((DataModel) o).getSubjects());
            }

            @Override
            public void _onError(String msg) {

            }

        };

        HttpMethods.getInstance().getHotMovie(subscriber);
    }

    private void requestNomal() {
        Subscriber<DataModel> subscriber = new RxSubscriber<DataModel>() {

            @Override
            public void onStart() {
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void _onNext(DataModel dataModel) {
                bindData(dataModel.getSubjects());
            }

            @Override
            public void _onError(String msg) {
            }
        };
//        HttpMethods.getInstance().getTopMovie(subscriber, 0, 30);
        HttpMethods.getInstance().getHotMovie(subscriber);
    }


    /**
     * On button click.
     *
     * @param view the view
     */
    @OnClick({R.id.btn_get})
    void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                Toast.makeText(this, "Request Button Click!", Toast.LENGTH_SHORT).show();
                requestNomal();
                break;
        }
    }
}
