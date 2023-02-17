package spring.exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.exam.domain.entities.Mood;
import spring.exam.domain.enums.MoodType;

@Repository
public interface MoodRepository extends JpaRepository<Mood, Long> {
    <Optional>Mood findByName(MoodType moodType);
}
