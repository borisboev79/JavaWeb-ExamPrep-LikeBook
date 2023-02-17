package spring.exam.services.product;

import examprep.shoppinglist.domain.entities.Category;
import examprep.shoppinglist.domain.entities.Product;
import examprep.shoppinglist.domain.entities.User;
import examprep.shoppinglist.domain.enums.CategoryType;
import examprep.shoppinglist.domain.helpers.LoggedUser;
import examprep.shoppinglist.domain.models.ProductAddModel;
import examprep.shoppinglist.domain.models.ProductViewModel;
import examprep.shoppinglist.repositories.CategoryRepository;
import examprep.shoppinglist.repositories.ProductRepository;
import examprep.shoppinglist.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository, LoggedUser loggedUser) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public void addProduct(ProductAddModel productAddModel) {
        this.productRepository.saveAndFlush(Product.builder()
                .name(productAddModel.getName())
                .price(productAddModel.getPrice())
                .description(productAddModel.getDescription())
                .neededBefore(productAddModel.getNeededBefore())
                .category(this.categoryRepository.findByName(productAddModel.getCategory()))
                .user(this.userRepository.findById(this.loggedUser.getId()).orElse(new User()))
                .build());
    }

    @Override
    public List<ProductViewModel> getProductsBySingleCategory(CategoryType categoryType) {
        Category category = this.categoryRepository.findByName(categoryType);

        return this.productRepository.findByCategoryAndUser_Id(category, loggedUser.getId())
                .stream().map(product -> ProductViewModel.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .build())
                .toList();
    }

    @Override
    public List<List<ProductViewModel>> getAllProductsByCategory() {

        List<ProductViewModel> foods = getProductsBySingleCategory(CategoryType.FOOD);
        List<ProductViewModel> drinks = getProductsBySingleCategory(CategoryType.DRINK);
        List<ProductViewModel> household = getProductsBySingleCategory(CategoryType.HOUSEHOLD);
        List<ProductViewModel> others = getProductsBySingleCategory(CategoryType.OTHER);


        return new ArrayList<>(Arrays.asList(foods, drinks, household, others));
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllProductsByUser(Long id) {
        this.productRepository.deleteAllByUser_Id(id);
    }


}
