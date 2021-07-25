package be.dieterblancke.kwamkwammer.controllers.watchlist;

import be.dieterblancke.kwamkwammer.enums.MovieType;
import lombok.Data;

@Data
public class WatchListCreateDTO
{
    private MovieType movieType;
}
