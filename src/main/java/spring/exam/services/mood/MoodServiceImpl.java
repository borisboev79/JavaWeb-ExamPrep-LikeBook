package spring.exam.services.mood;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.exam.domain.entities.Mood;
import spring.exam.domain.enums.MoodType;
import spring.exam.repositories.MoodRepository;
import spring.exam.services.DatabaseInitialization;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoodServiceImpl implements MoodService, DatabaseInitialization {
    private final MoodRepository moodRepository;

    @Autowired
    public MoodServiceImpl(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @PostConstruct
    @Override
    public void dbInit() {
        if(!isDbInit()) {

            List<Mood> categories = new ArrayList<>();
            categories.add(Mood.builder().name(MoodType.HAPPY).description("Happy mood").build());
            categories.add(Mood.builder().name(MoodType.SAD).description("Sad mood").build());
            categories.add(Mood.builder().name(MoodType.INSPIRED).description("Inspired mood").build());
            this.moodRepository.saveAllAndFlush(categories);
        }
    }

    @Override
    public boolean isDbInit() {
        return this.moodRepository.count() > 0;
    }
}
