package be.dieterblancke.kwamkwammer.controllers.watchlist;

import be.dieterblancke.kwamkwammer.enums.MovieStatus;
import lombok.Data;

@Data
public class WatchListCreateDTO
{
    private String name;
    private MovieStatus status;
}
