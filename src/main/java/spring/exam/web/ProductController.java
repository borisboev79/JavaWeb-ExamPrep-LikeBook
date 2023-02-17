package spring.exam.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.exam.domain.models.PostAddModel;
import spring.exam.services.post.PostService;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final PostService postService;

    @Autowired
    public ProductController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/add")
    public String getAddProduct() {
        return "product-add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute(name = "productAddModel") PostAddModel postAddModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("productAddModel", postAddModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.productAddModel", bindingResult);

            return "redirect:add";
        }
        this.postService.addPost(postAddModel);

        return "redirect:/home";
    }

    @ModelAttribute(name = "productAddModel")
    public PostAddModel productAddModel() {
        return new PostAddModel();
    }
}
