package pl.srslycpp.myWeb.Service;

import org.springframework.stereotype.Service;
import pl.srslycpp.myWeb.Entity.Product;
import pl.srslycpp.myWeb.commands.ProductCommand;
import pl.srslycpp.myWeb.converters.ProductFormToProduct;
import pl.srslycpp.myWeb.converters.ProductToProductForm;
import pl.srslycpp.myWeb.repositories.ProductRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private ProductToProductForm productToProductForm;
    private ProductFormToProduct productFormToProduct;

    public ProductServiceImpl(ProductRepository productRepository, ProductToProductForm productToProductForm, ProductFormToProduct productFormToProduct) {
        this.productRepository = productRepository;
        this.productToProductForm = productToProductForm;
        this.productFormToProduct = productFormToProduct;
    }


    @Override
    public List<Product> getAllProduct() {
        List<Product> productList = new LinkedList<>();

        productRepository.findAll().iterator().forEachRemaining(productList::add);
        return productList;
    }

    @Override
    public Product getById(String id) {
        return null;
    }

    @Override
    public void removeById(String id) {

    }

    @Override
    public Product saveOrEditProduct(ProductCommand productForm) {

        Product converted = ProductToProductForm.

        return null;
    }
}
