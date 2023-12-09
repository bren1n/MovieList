package br.ufrn.imd.movielist.main.movieHelper;

public class Movie {
    private int id;
    private String name;
    private String desc;
    private int releaseDate;
    private String imageUrl;
    private int rate;
    private String review;


    public Movie(int id, String name, int releaseDate, String desc, String imageUrl, int rate, String review) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.rate = rate;
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseDate() { return releaseDate; }

    public void setReleaseDate(int releaseDate) { this.releaseDate = releaseDate; }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public int getRate() { return rate; }

    public void setRate(int rate) { this.rate = rate; }

    public String getReview() { return review; }

    public void setReview(String review) { this.review = review; }

    public Movie(int id) {
        this.id = id;

    }
}
