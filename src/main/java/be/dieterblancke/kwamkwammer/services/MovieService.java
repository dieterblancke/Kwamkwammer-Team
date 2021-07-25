package be.dieterblancke.kwamkwammer.services;

import be.dieterblancke.kwamkwammer.domain.Movie;
import be.dieterblancke.kwamkwammer.enums.MovieType;
import be.dieterblancke.kwamkwammer.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService
{

    private final MovieRepository movieRepository;

    public List<Movie> findAllMovies()
    {
        return movieRepository.findAllByType( MovieType.MOVIE );
    }

    public List<Movie> findAllSeries()
    {
        return movieRepository.findAllByType( MovieType.SERIE );
    }

    public Movie create( final MovieType movieType )
    {
        final Movie movie = new Movie();

        movie.setMovieType( movieType );
        movie.setName( "enter name" );

        return movieRepository.save( movie );
    }
}
