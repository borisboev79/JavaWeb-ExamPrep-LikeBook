package spring.exam.services.product;

import examprep.shoppinglist.domain.enums.CategoryType;
import examprep.shoppinglist.domain.models.ProductAddModel;
import examprep.shoppinglist.domain.models.ProductViewModel;

import java.util.List;

public interface ProductService {

    void addProduct(ProductAddModel productAddModel);

    List<ProductViewModel> getProductsBySingleCategory(CategoryType categoryType);

    List<List<ProductViewModel>> getAllProductsByCategory();

    void deleteProductById(Long id);

    void deleteAllProductsByUser(Long id);

}
