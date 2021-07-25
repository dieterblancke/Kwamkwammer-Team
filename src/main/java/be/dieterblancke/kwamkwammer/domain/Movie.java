package be.dieterblancke.kwamkwammer.domain;

import be.dieterblancke.kwamkwammer.enums.MovieType;

import javax.persistence.*;

@Entity
@Table( name = "movie" )
public class Movie extends BaseEntity
{

    private String name;
    @Column( name = "movie_type" )
    @Enumerated( EnumType.STRING )
    private MovieType movieType;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public MovieType getMovieType()
    {
        return movieType;
    }

    public void setMovieType( MovieType movieType )
    {
        this.movieType = movieType;
    }
}
