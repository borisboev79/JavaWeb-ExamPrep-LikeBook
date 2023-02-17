package spring.exam.web;

import examprep.shoppinglist.domain.helpers.LoggedUser;
import examprep.shoppinglist.domain.models.ProductViewModel;
import examprep.shoppinglist.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private final LoggedUser loggedUser;
    private final ProductService productService;


    @Autowired
    public HomeController(LoggedUser loggedUser, ProductService productService) {
        this.loggedUser = loggedUser;
        this.productService = productService;
    }

    @GetMapping
    public String getIndex() {
        return "index";
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView model) {

        List<List<ProductViewModel>> products = this.productService.getAllProductsByCategory();

        BigDecimal totalPrice = products.stream().map(
                list -> list.stream()
                        .map(ProductViewModel::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        ).reduce(BigDecimal.ZERO, BigDecimal::add);

        model.setViewName("home");
        model.addObject("products", products);
        model.addObject("price", totalPrice);
        model.addObject("loggedUserId", this.loggedUser.getId());

        return model;
    }

    @GetMapping("/buy/{id}")
    public String buyProduct(@PathVariable Long id){
        this.productService.deleteProductById(id);

        return "redirect:/home";
    }

    @GetMapping("/buy/all/{id}")
    public String buyAllProducts(@PathVariable Long id){
        this.productService.deleteAllProductsByUser(id);

        return "redirect:/home";
    }
}
