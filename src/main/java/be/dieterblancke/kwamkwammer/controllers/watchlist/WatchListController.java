package be.dieterblancke.kwamkwammer.controllers.watchlist;

import be.dieterblancke.kwamkwammer.domain.Movie;
import be.dieterblancke.kwamkwammer.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping( "/watchlist" )
public class WatchListController
{

    private final MovieService movieService;

    @GetMapping
    public ModelAndView getWatchListPage()
    {
        final ModelAndView modelAndView = new ModelAndView( "watchlist" );
        modelAndView.addObject( "movies", movieService.findAll() );
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<List<Movie>> createMovie( @RequestBody final List<WatchListCreateDTO> watchListCreateDTOList )
    {
        final List<Movie> createdMovies = watchListCreateDTOList.stream()
                .map( dto -> movieService.create( dto.getName(), dto.getStatus() ) )
                .collect( Collectors.toList() );

        return ResponseEntity.ok( createdMovies );
    }

    @PutMapping
    public ResponseEntity<Long> updateMovie( @RequestBody final WatchListUpdateDTO watchListUpdateDTO )
    {
        movieService.setMovieStatus( watchListUpdateDTO.getMovie().getId(), watchListUpdateDTO.getMovie().getStatus() );
        watchListUpdateDTO.getMoviePositions().forEach( movie -> movieService.updateMoviePosition( movie.getId(), movie.getPosition() ) );
        return ResponseEntity.ok( watchListUpdateDTO.getMovie().getId() );
    }

    @DeleteMapping
    public ResponseEntity<Long> deleteMovie( @RequestBody final Long id )
    {
        try
        {
            movieService.delete( id );
            return ResponseEntity.ok( id );
        }
        catch ( Exception e )
        {
            return ResponseEntity.status( 400 ).build();
        }
    }
}
