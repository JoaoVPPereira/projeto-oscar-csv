package br.com.letscode.java;

public class MenuItem {
    private Integer year;
    private Integer age;
    private String name;
    private String movie;

    public MenuItem(Integer year, Integer age, String name, String movie) {
        this.year = year;
        this.age = age;
        this.name = name;
        this.movie = movie;
    }

    public static MenuItem of(String line) {
        String[] split = line.split(";\\s");
        return new MenuItem(
                Integer.parseInt(split[1]),
                Integer.parseInt(split[2]),
                split[3],
                split[4]
        );
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "year=" + year +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", movie='" + movie + '\'' +
                '}';
    }
}
