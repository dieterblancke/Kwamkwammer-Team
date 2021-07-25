package be.dieterblancke.kwamkwammer.domain;

import be.dieterblancke.kwamkwammer.enums.MovieStatus;

import javax.persistence.*;

@Entity
@Table( name = "movie" )
public class Movie extends BaseEntity
{

    private String name;

    @Column( name = "movie_status" )
    @Enumerated( EnumType.STRING )
    private MovieStatus status;
    private int position;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public MovieStatus getStatus()
    {
        return status;
    }

    public void setStatus( MovieStatus status )
    {
        this.status = status;
    }

    public int getPosition()
    {
        return position;
    }

    public void setPosition( int position )
    {
        this.position = position;
    }
}
