package be.dieterblancke.kwamkwammer.repository;

import be.dieterblancke.kwamkwammer.domain.Movie;
import be.dieterblancke.kwamkwammer.enums.MovieStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>
{

    @Query("from Movie order by position")
    List<Movie> findAll();

    @Query( "select m from Movie m where status = :status" )
    List<Movie> findAllByStatus( @Param( "status" ) MovieStatus status );
}
