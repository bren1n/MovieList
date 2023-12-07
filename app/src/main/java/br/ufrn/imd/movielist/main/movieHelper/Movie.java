package br.ufrn.imd.movielist.main.movieHelper;

public class Movie {
    private int id;
    private String name;
    private String desc;
    private String releaseDate;
    private String imageUrl;


    public Movie(int id, String name, String releaseDate, String desc, String imageUrl) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.desc = desc;
        this.imageUrl = imageUrl;
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

    public String getReleaseDate() { return releaseDate; }

    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Movie(int id) {
        this.id = id;

    }
}
