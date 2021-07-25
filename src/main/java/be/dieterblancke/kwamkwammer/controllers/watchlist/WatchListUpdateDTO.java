package be.dieterblancke.kwamkwammer.controllers.watchlist;

import be.dieterblancke.kwamkwammer.enums.MovieStatus;
import lombok.Data;

import java.util.List;

@Data
public class WatchListUpdateDTO
{

    private WatchListUpdateMovieDTO movie;
    private List<WatchListUpdateMoviePositionsDTO> moviePositions;

    @Data
    public static class WatchListUpdateMovieDTO {
        private Long id;
        private MovieStatus status;
    }

    @Data
    public static class WatchListUpdateMoviePositionsDTO {
        private Long id;
        private Integer position;
    }
}
