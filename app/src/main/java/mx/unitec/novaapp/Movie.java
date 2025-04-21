package mx.unitec.novaapp;

public class Movie {
    private String title;
    private String director;
    private int year;
    private String description;
    private String cover;

    public Movie(String title, String director, int year, String description, String cover) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.description = description;
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public String getCover() {
        return cover;
    }
}
