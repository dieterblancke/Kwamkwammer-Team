package be.dieterblancke.kwamkwammer.services;

import be.dieterblancke.kwamkwammer.domain.Movie;
import be.dieterblancke.kwamkwammer.enums.MovieStatus;
import be.dieterblancke.kwamkwammer.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService
{

    private final MovieRepository movieRepository;

    public Movie create( final String name, final MovieStatus status )
    {
        final Movie movie = new Movie();

        movie.setStatus( status );
        movie.setName( name );

        return movieRepository.save( movie );
    }

    public List<Movie> findAll()
    {
        return movieRepository.findAll();
    }

    public void delete( final Long id )
    {
        movieRepository.deleteById( id );
    }

    public void setMovieStatus( final Long id, final MovieStatus status )
    {
        movieRepository.findById( id ).ifPresent( movie ->
        {
            movie.setStatus( status );
            this.save( movie );
        } );
    }

    public Movie save( final Movie movie )
    {
        return movieRepository.save( movie );
    }

    public void updateMoviePosition( final Long id, final Integer position )
    {
        movieRepository.findById( id ).ifPresent( movie ->
        {
            movie.setPosition( position );
            this.save( movie );
        } );
    }
}
