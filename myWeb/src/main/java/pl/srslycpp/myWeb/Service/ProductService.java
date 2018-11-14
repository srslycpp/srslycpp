package pl.srslycpp.myWeb.Service;

import pl.srslycpp.myWeb.Entity.Product;
import pl.srslycpp.myWeb.commands.ProductCommand;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Product getById(String id);

    Product saveOrUpdate(Product product);

    void removeById(String id);

    Product saveOrEditProduct(ProductCommand productForm);

}
