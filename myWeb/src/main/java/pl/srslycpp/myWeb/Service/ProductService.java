package pl.srslycpp.myWeb.Service;

import pl.srslycpp.myWeb.Entity.Product;
import pl.srslycpp.myWeb.commands.ProductForm;

import java.util.List;

public interface ProductService  {

    List<Product> getAllProduct();

    Product getById(String id);

    void removeById(String id);

    Product saveOrEditProduct(ProductForm productForm);

}
