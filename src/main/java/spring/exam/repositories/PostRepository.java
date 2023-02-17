package spring.exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.exam.domain.entities.Mood;
import spring.exam.domain.entities.Post;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByMoodAndUser_Id(Mood mood, Long userId);

    void deleteAllByUser_Id(Long id);

}
