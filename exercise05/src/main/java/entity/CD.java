package entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="\"Order\"")
@DiscriminatorValue("CD")
public class CD extends Product{
    private String artist;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "CD{" +
                "artist='" + artist + '\'' +
                '}';
    }
}