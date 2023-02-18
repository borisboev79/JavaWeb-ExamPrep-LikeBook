package spring.exam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.exam.domain.helpers.LoggedUser;
import spring.exam.domain.models.PostViewModel;
import spring.exam.services.post.PostService;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private final LoggedUser loggedUser;
    private final PostService postService;


    @Autowired
    public HomeController(LoggedUser loggedUser, PostService postService) {
        this.loggedUser = loggedUser;
        this.postService = postService;
    }

    @GetMapping
    public String getIndex() {
        return "index";
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView model) {

        List<PostViewModel> posts = this.postService.getAllLoggedUserPosts();
        List<PostViewModel> otherPosts = this.postService.getAllNonLoggedUserPosts();

        model.setViewName("home");
        model.addObject("posts", posts);
        model.addObject("otherPosts", otherPosts);
        model.addObject("loggedUserId", this.loggedUser.getId());
        return model;
    }

    @GetMapping("/like/{id}")
    public String likePost(@PathVariable Long id){

        this.postService.likePost(id);

        return "redirect:/home";
    }
    @GetMapping("/remove/{id}")
    public String buyProduct(@PathVariable Long id){
        this.postService.deletePostById(id);

        return "redirect:/home";
    }

}
