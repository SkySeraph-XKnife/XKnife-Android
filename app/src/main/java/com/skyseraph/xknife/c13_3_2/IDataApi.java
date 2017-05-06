package com.skyseraph.xknife.c13_3_2;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by SkySeraph on 2016/5/4.
 */
public interface IDataApi {

    /**
     * 获取热门电影
     *
     * @return hot movie
     */
    @GET("movie/in_theaters")
    Observable<DataModel> getHotMovie();

    /**
     * 获取豆瓣电影Top 100
     *
     * @param start the start
     * @param count the count
     * @return top movie
     */
    @GET("movie/top100")
    Observable<DataModel> getTopMovie(@Query("start") int start, @Query("count") int count);

    /**
     * 根据id获取电影信息
     *
     * @param id 路径参数:电影id
     * @return movie detail
     */
    @GET("movie/subject/{id}")
    Observable<DataModel> getMovieDetail(@Path("id") String id);


    /**
     * 根据关键字搜索电影
     *
     * @param q 参数:关键字
     * @return search movie
     */
    @GET("movie/search")
    Observable<DataModel> getSearchMovie(@Query("q") String q);
}
