package pl.srslycpp.myWeb.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.srslycpp.myWeb.Entity.Product;
import pl.srslycpp.myWeb.commands.ProductCommand;

@Component
    public class ProductToProductCommad implements Converter<Product, ProductCommand> {

        @Override
        public ProductCommand convert(Product product) {
            ProductCommand productForm = new ProductCommand();
            productForm.setId(product.getId());
            productForm.setDescription(product.getDescription());
            productForm.setPrice(product.getPrice());
            productForm.setImageUrl(product.getImageUrl());
            return productForm;
        }

}
