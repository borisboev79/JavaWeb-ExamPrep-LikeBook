package spring.exam.services.post;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.exam.domain.entities.Mood;
import spring.exam.domain.entities.Post;
import spring.exam.domain.entities.User;
import spring.exam.domain.enums.MoodType;
import spring.exam.domain.helpers.LoggedUser;
import spring.exam.domain.models.PostAddModel;
import spring.exam.domain.models.PostViewModel;
import spring.exam.repositories.MoodRepository;
import spring.exam.repositories.PostRepository;
import spring.exam.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final MoodRepository moodRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, MoodRepository moodRepository, UserRepository userRepository, LoggedUser loggedUser) {
        this.postRepository = postRepository;
        this.moodRepository = moodRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public void addPost(PostAddModel postAddModel) {
        this.postRepository.saveAndFlush(Post.builder()
                .content(postAddModel.getContent())
                .mood(this.moodRepository.findByName(postAddModel.getMood()))
                .user(this.userRepository.findById(this.loggedUser.getId()).orElse(new User()))
                .userLikes(new ArrayList<>())
                .build());
    }

    @Override
    public List<PostViewModel> getPostsBySingleMood(MoodType moodType) {
        Mood mood = this.moodRepository.findByName(moodType);

        return this.postRepository.findByMoodAndUser_Id(mood, loggedUser.getId())
                .stream().map(product -> PostViewModel.builder()
                        /*   .id(product.getId())
                           .name(product.getName())
                           .price(product.getPrice())*/
                        .build())
                .toList();
    }

    @Override
    public List<PostViewModel> getAllLoggedUserPosts() {
        return this.postRepository.findAllByUser_Id(this.loggedUser.getId()).orElse(null).stream()
                .map(this::viewModel).toList();

    }

    @Override
    public List<PostViewModel> getAllNonLoggedUserPosts() {
        return this.postRepository.findAllByUser_IdNot(this.loggedUser.getId()).orElse(null).stream()
                .map(this::viewModel).toList();
    }

    @Override
    @Transactional
    public void likePost(Long id) {
        Post postToLike = this.postRepository.findById(id).orElse(null);


        if (postToLike != null && !postToLike.getUserLikes().contains(this.userRepository.findById(this.loggedUser.getId()).get())) {
            postToLike.getUserLikes().add(this.userRepository.findById(this.loggedUser.getId()).get());
        }
    }


    @Override
    @Transactional
    public void deletePostById(Long id) {
        this.postRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllPostsByUser(Long id) {
        this.postRepository.deleteAllByUser_Id(id);
    }

    private PostViewModel viewModel(Post post) {
        return PostViewModel.builder()
                .id(post.getId())
                .mood(post.getMood())
                .username(post.getUser().getUsername())
                .content(post.getContent())
                .userLikes(post.getUserLikes().size())
                .build();
    }
}
