package entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("DVD")
public class DVD extends Product{
    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "DVD{" +
                "genre='" + genre + '\'' +
                '}';
    }
}