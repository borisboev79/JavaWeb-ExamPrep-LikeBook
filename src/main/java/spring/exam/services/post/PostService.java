package spring.exam.services.post;

import spring.exam.domain.enums.MoodType;
import spring.exam.domain.models.PostAddModel;
import spring.exam.domain.models.PostViewModel;

import java.util.List;

public interface PostService {

    void addPost(PostAddModel productAddModel);

    List<PostViewModel> getPostsBySingleMood(MoodType moodType);

    List<List<PostViewModel>> getAllPostsByMood();

    void deletePostById(Long id);

    void deleteAllPostsByUser(Long id);

}
