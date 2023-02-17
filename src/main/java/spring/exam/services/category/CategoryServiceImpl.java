package spring.exam.services.category;

import examprep.shoppinglist.domain.entities.Category;
import examprep.shoppinglist.domain.enums.CategoryType;
import examprep.shoppinglist.repositories.CategoryRepository;
import examprep.shoppinglist.services.DatabaseInitialization;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService, DatabaseInitialization {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    @Override
    public void dbInit() {
        if(!isDbInit()) {

            List<Category> categories = new ArrayList<>();
            categories.add(Category.builder().name(CategoryType.FOOD).description("Food for home").build());
            categories.add(Category.builder().name(CategoryType.HOUSEHOLD).description("Some stuff for the kitchen and bathroom").build());
            categories.add(Category.builder().name(CategoryType.DRINK).description("Booze and sodas").build());
            categories.add(Category.builder().name(CategoryType.OTHER).description("Stationery, hobby, tools").build());

            this.categoryRepository.saveAllAndFlush(categories);
        }
    }

    @Override
    public boolean isDbInit() {
        return this.categoryRepository.count() > 0;
    }
}
