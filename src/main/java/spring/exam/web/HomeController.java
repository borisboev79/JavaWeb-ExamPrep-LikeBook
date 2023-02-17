package spring.exam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.exam.domain.helpers.LoggedUser;
import spring.exam.services.post.PostService;

@Controller
@RequestMapping("/")
public class HomeController {
    private final LoggedUser loggedUser;
    private final PostService productService;


    @Autowired
    public HomeController(LoggedUser loggedUser, PostService productService) {
        this.loggedUser = loggedUser;
        this.productService = productService;
    }

    @GetMapping
    public String getIndex() {
        return "index";
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView model) {

       /* List<List<PostViewModel>> products = this.productService.getAllProductsByCategory();

        BigDecimal totalPrice = products.stream().map(
                list -> list.stream()
                        .map(PostViewModel::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        ).reduce(BigDecimal.ZERO, BigDecimal::add);

        model.setViewName("home");
        model.addObject("products", products);
        model.addObject("price", totalPrice);
        model.addObject("loggedUserId", this.loggedUser.getId());
*/
        return model;
    }

    @GetMapping("/buy/{id}")
    public String buyProduct(@PathVariable Long id){
        this.productService.deletePostById(id);

        return "redirect:/home";
    }

    @GetMapping("/buy/all/{id}")
    public String buyAllProducts(@PathVariable Long id){
        this.productService.deleteAllPostsByUser(id);

        return "redirect:/home";
    }
}
