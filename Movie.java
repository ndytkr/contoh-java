package id.ac.umn.movie;

public class Movie {
    private int id;
    private int year;
    private String genre;
    private String title;

    //TODO Ganti package name sesuai package kalian

    public Movie(int id, int year, String title, String genre) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
