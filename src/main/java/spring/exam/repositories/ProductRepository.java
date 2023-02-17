package spring.exam.repositories;

import examprep.shoppinglist.domain.entities.Category;
import examprep.shoppinglist.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByCategoryAndUser_Id(Category category, Long userId);

    void deleteAllByUser_Id(Long id);

}
