package be.dieterblancke.kwamkwammer.controllers.watchlist;

import be.dieterblancke.kwamkwammer.domain.Movie;
import be.dieterblancke.kwamkwammer.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        modelAndView.addObject( "movies", movieService.findAllMovies() );
        modelAndView.addObject( "series", movieService.findAllSeries() );
        return modelAndView;
    }

    @PostMapping( "create" )
    public ResponseEntity<Movie> createMovie( @RequestBody final WatchListCreateDTO watchListCreateDTO )
    {
        return ResponseEntity.ok( movieService.create( watchListCreateDTO.getMovieType() ) );
    }
}
