package be.dieterblancke.kwamkwammer.repository;

import be.dieterblancke.kwamkwammer.domain.Movie;
import be.dieterblancke.kwamkwammer.enums.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>
{
    @Query("select m from Movie m where movieType = :type")
    List<Movie> findAllByType( @Param( "type" ) MovieType type );
}
