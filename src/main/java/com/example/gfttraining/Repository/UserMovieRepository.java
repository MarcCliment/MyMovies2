package com.example.gfttraining.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gfttraining.Entity.UserMovie;

public interface UserMovieRepository extends JpaRepository<UserMovie, Long>{

   public Optional<UserMovie> findByUsernameAndMovie ( String username , String movie_id ) ;
   
}
