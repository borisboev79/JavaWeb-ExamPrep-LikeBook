package spring.exam.web;

import examprep.shoppinglist.domain.models.ProductAddModel;
import examprep.shoppinglist.services.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String getAddProduct(){
        return "product-add";
    }

    @PostMapping("/add")
        public String addProduct(@Valid @ModelAttribute(name="productAddModel") ProductAddModel productAddModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("productAddModel", productAddModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.productAddModel", bindingResult);

            return "redirect:add";
        }
        this.productService.addProduct(productAddModel);

        return "redirect:/home";
    }

    @ModelAttribute(name="productAddModel")
    public ProductAddModel productAddModel(){
        return new ProductAddModel();
    }
}
