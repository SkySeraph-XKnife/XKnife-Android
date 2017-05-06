package com.skyseraph.xknife.c13_3_2;

import java.util.List;

/**
 * Created by SkySeraph on 2016/5/4.
 */
public class DataModel {

    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectsEntity> subjects;

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Gets start.
     *
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * Sets start.
     *
     * @param start the start
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets total.
     *
     * @param total the total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets subjects.
     *
     * @return the subjects
     */
    public List<SubjectsEntity> getSubjects() {
        return subjects;
    }

    /**
     * Sets subjects.
     *
     * @param subjects the subjects
     */
    public void setSubjects(List<SubjectsEntity> subjects) {
        this.subjects = subjects;
    }

    /**
     * The type Subjects entity.
     */
    public static class SubjectsEntity {

        private RatingEntity rating;
        private String title;
        private int collect_count;
        private String original_title;
        private String subtype;
        private String year;
        private ImagesEntity images;
        private String alt;
        private String id;
        private List<String> genres;
        private List<CastsEntity> casts;
        private List<DirectorsEntity> directors;

        /**
         * Gets rating.
         *
         * @return the rating
         */
        public RatingEntity getRating() {
            return rating;
        }

        /**
         * Sets rating.
         *
         * @param rating the rating
         */
        public void setRating(RatingEntity rating) {
            this.rating = rating;
        }

        /**
         * Gets title.
         *
         * @return the title
         */
        public String getTitle() {
            return title;
        }

        /**
         * Sets title.
         *
         * @param title the title
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * Gets collect count.
         *
         * @return the collect count
         */
        public int getCollect_count() {
            return collect_count;
        }

        /**
         * Sets collect count.
         *
         * @param collect_count the collect count
         */
        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
        }

        /**
         * Gets original title.
         *
         * @return the original title
         */
        public String getOriginal_title() {
            return original_title;
        }

        /**
         * Sets original title.
         *
         * @param original_title the original title
         */
        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        /**
         * Gets subtype.
         *
         * @return the subtype
         */
        public String getSubtype() {
            return subtype;
        }

        /**
         * Sets subtype.
         *
         * @param subtype the subtype
         */
        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        /**
         * Gets year.
         *
         * @return the year
         */
        public String getYear() {
            return year;
        }

        /**
         * Sets year.
         *
         * @param year the year
         */
        public void setYear(String year) {
            this.year = year;
        }

        /**
         * Gets images.
         *
         * @return the images
         */
        public ImagesEntity getImages() {
            return images;
        }

        /**
         * Sets images.
         *
         * @param images the images
         */
        public void setImages(ImagesEntity images) {
            this.images = images;
        }

        /**
         * Gets alt.
         *
         * @return the alt
         */
        public String getAlt() {
            return alt;
        }

        /**
         * Sets alt.
         *
         * @param alt the alt
         */
        public void setAlt(String alt) {
            this.alt = alt;
        }

        /**
         * Gets id.
         *
         * @return the id
         */
        public String getId() {
            return id;
        }

        /**
         * Sets id.
         *
         * @param id the id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * Gets genres.
         *
         * @return the genres
         */
        public List<String> getGenres() {
            return genres;
        }

        /**
         * Sets genres.
         *
         * @param genres the genres
         */
        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        /**
         * Gets casts.
         *
         * @return the casts
         */
        public List<CastsEntity> getCasts() {
            return casts;
        }

        /**
         * Sets casts.
         *
         * @param casts the casts
         */
        public void setCasts(List<CastsEntity> casts) {
            this.casts = casts;
        }

        /**
         * Gets directors.
         *
         * @return the directors
         */
        public List<DirectorsEntity> getDirectors() {
            return directors;
        }

        /**
         * Sets directors.
         *
         * @param directors the directors
         */
        public void setDirectors(List<DirectorsEntity> directors) {
            this.directors = directors;
        }

        /**
         * The type Rating entity.
         */
        public static class RatingEntity {
            /**
             * max : 10
             * average : 7.4
             * stars : 40
             * min : 0
             */

            private int max;
            private double average;
            private String stars;
            private int min;

            /**
             * Gets max.
             *
             * @return the max
             */
            public int getMax() {
                return max;
            }

            /**
             * Sets max.
             *
             * @param max the max
             */
            public void setMax(int max) {
                this.max = max;
            }

            /**
             * Gets average.
             *
             * @return the average
             */
            public double getAverage() {
                return average;
            }

            /**
             * Sets average.
             *
             * @param average the average
             */
            public void setAverage(double average) {
                this.average = average;
            }

            /**
             * Gets stars.
             *
             * @return the stars
             */
            public String getStars() {
                return stars;
            }

            /**
             * Sets stars.
             *
             * @param stars the stars
             */
            public void setStars(String stars) {
                this.stars = stars;
            }

            /**
             * Gets min.
             *
             * @return the min
             */
            public int getMin() {
                return min;
            }

            /**
             * Sets min.
             *
             * @param min the min
             */
            public void setMin(int min) {
                this.min = min;
            }
        }

        /**
         * The type Images entity.
         */
        public static class ImagesEntity {

            private String small;
            private String large;
            private String medium;

            /**
             * Gets small.
             *
             * @return the small
             */
            public String getSmall() {
                return small;
            }

            /**
             * Sets small.
             *
             * @param small the small
             */
            public void setSmall(String small) {
                this.small = small;
            }

            /**
             * Gets large.
             *
             * @return the large
             */
            public String getLarge() {
                return large;
            }

            /**
             * Sets large.
             *
             * @param large the large
             */
            public void setLarge(String large) {
                this.large = large;
            }

            /**
             * Gets medium.
             *
             * @return the medium
             */
            public String getMedium() {
                return medium;
            }

            /**
             * Sets medium.
             *
             * @param medium the medium
             */
            public void setMedium(String medium) {
                this.medium = medium;
            }
        }

        /**
         * The type Casts entity.
         */
        public static class CastsEntity {

            private String alt;
            private AvatarsEntity avatars;
            private String name;
            private String id;

            /**
             * Gets alt.
             *
             * @return the alt
             */
            public String getAlt() {
                return alt;
            }

            /**
             * Sets alt.
             *
             * @param alt the alt
             */
            public void setAlt(String alt) {
                this.alt = alt;
            }

            /**
             * Gets avatars.
             *
             * @return the avatars
             */
            public AvatarsEntity getAvatars() {
                return avatars;
            }

            /**
             * Sets avatars.
             *
             * @param avatars the avatars
             */
            public void setAvatars(AvatarsEntity avatars) {
                this.avatars = avatars;
            }

            /**
             * Gets name.
             *
             * @return the name
             */
            public String getName() {
                return name;
            }

            /**
             * Sets name.
             *
             * @param name the name
             */
            public void setName(String name) {
                this.name = name;
            }

            /**
             * Gets id.
             *
             * @return the id
             */
            public String getId() {
                return id;
            }

            /**
             * Sets id.
             *
             * @param id the id
             */
            public void setId(String id) {
                this.id = id;
            }

            /**
             * The type Avatars entity.
             */
            public static class AvatarsEntity {

                private String small;
                private String large;
                private String medium;

                /**
                 * Gets small.
                 *
                 * @return the small
                 */
                public String getSmall() {
                    return small;
                }

                /**
                 * Sets small.
                 *
                 * @param small the small
                 */
                public void setSmall(String small) {
                    this.small = small;
                }

                /**
                 * Gets large.
                 *
                 * @return the large
                 */
                public String getLarge() {
                    return large;
                }

                /**
                 * Sets large.
                 *
                 * @param large the large
                 */
                public void setLarge(String large) {
                    this.large = large;
                }

                /**
                 * Gets medium.
                 *
                 * @return the medium
                 */
                public String getMedium() {
                    return medium;
                }

                /**
                 * Sets medium.
                 *
                 * @param medium the medium
                 */
                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }

        /**
         * The type Directors entity.
         */
        public static class DirectorsEntity {

            private String alt;
            private AvatarsEntityX avatars;
            private String name;
            private String id;

            /**
             * Gets alt.
             *
             * @return the alt
             */
            public String getAlt() {
                return alt;
            }

            /**
             * Sets alt.
             *
             * @param alt the alt
             */
            public void setAlt(String alt) {
                this.alt = alt;
            }

            /**
             * Gets avatars.
             *
             * @return the avatars
             */
            public AvatarsEntityX getAvatars() {
                return avatars;
            }

            /**
             * Sets avatars.
             *
             * @param avatars the avatars
             */
            public void setAvatars(AvatarsEntityX avatars) {
                this.avatars = avatars;
            }

            /**
             * Gets name.
             *
             * @return the name
             */
            public String getName() {
                return name;
            }

            /**
             * Sets name.
             *
             * @param name the name
             */
            public void setName(String name) {
                this.name = name;
            }

            /**
             * Gets id.
             *
             * @return the id
             */
            public String getId() {
                return id;
            }

            /**
             * Sets id.
             *
             * @param id the id
             */
            public void setId(String id) {
                this.id = id;
            }

            /**
             * The type Avatars entity x.
             */
            public static class AvatarsEntityX {

                private String small;
                private String large;
                private String medium;

                /**
                 * Gets small.
                 *
                 * @return the small
                 */
                public String getSmall() {
                    return small;
                }

                /**
                 * Sets small.
                 *
                 * @param small the small
                 */
                public void setSmall(String small) {
                    this.small = small;
                }

                /**
                 * Gets large.
                 *
                 * @return the large
                 */
                public String getLarge() {
                    return large;
                }

                /**
                 * Sets large.
                 *
                 * @param large the large
                 */
                public void setLarge(String large) {
                    this.large = large;
                }

                /**
                 * Gets medium.
                 *
                 * @return the medium
                 */
                public String getMedium() {
                    return medium;
                }

                /**
                 * Sets medium.
                 *
                 * @param medium the medium
                 */
                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }
    }
}
