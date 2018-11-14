package pl.srslycpp.myWeb.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.srslycpp.myWeb.Entity.Product;

public interface ProductRepository extends CrudRepository<Product, String> {
}
