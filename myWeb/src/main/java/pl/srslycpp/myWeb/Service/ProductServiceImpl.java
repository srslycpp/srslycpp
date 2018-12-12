package pl.srslycpp.myWeb.Service;

import org.springframework.stereotype.Service;
import pl.srslycpp.myWeb.Entity.Product;
import pl.srslycpp.myWeb.commands.ProductCommand;
import pl.srslycpp.myWeb.converters.ProductCommandToProduct;
import pl.srslycpp.myWeb.repositories.ProductRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private ProductCommandToProduct productCommandToProduct;

    public ProductServiceImpl(ProductRepository productRepository, ProductCommandToProduct productCommandToProduct) {
        this.productRepository = productRepository;
        this.productCommandToProduct = productCommandToProduct;
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
    public Product saveOrUpdate(Product product){
        productRepository.save(product);
        return product;
    }

    @Override
    public Product saveOrEditProduct(ProductCommand productCommand) {

        Product saveProduct = saveOrUpdate(productCommandToProduct.convert(productCommand));
        System.out.println("Product's saved id= "+ saveProduct.getId());
        return saveProduct;
    }


}
