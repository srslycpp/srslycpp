package pl.srslycpp.myWeb.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.srslycpp.myWeb.Entity.Product;
import pl.srslycpp.myWeb.commands.ProductFormCommand;

@Component
    public class ProductToProductForm implements Converter<Product, ProductFormCommand> {

        @Override
        public ProductFormCommand convert(Product product) {
            ProductFormCommand productForm = new ProductFormCommand();
            productForm.setId(product.getId());
            productForm.setDescription(product.getDescription());
            productForm.setPrice(product.getPrice());
            productForm.setImageUrl(product.getImageUrl());
            return productForm;
        }

}
