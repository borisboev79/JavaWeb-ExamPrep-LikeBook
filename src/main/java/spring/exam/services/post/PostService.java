package spring.exam.services.post;

import spring.exam.domain.enums.MoodType;
import spring.exam.domain.models.PostAddModel;
import spring.exam.domain.models.PostViewModel;

import java.util.List;

public interface PostService {

    void addPost(PostAddModel productAddModel);

    List<PostViewModel> getPostsBySingleMood(MoodType moodType);

    List<PostViewModel> getAllLoggedUserPosts();
    List<PostViewModel> getAllNonLoggedUserPosts();

    void likePost(Long id);

    void deletePostById(Long id);

    void deleteAllPostsByUser(Long id);

}
