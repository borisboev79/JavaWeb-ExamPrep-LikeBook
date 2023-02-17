package spring.exam.repositories;

import examprep.shoppinglist.domain.entities.Category;
import examprep.shoppinglist.domain.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    <Optional>Category findByName(CategoryType categoryType);
}
