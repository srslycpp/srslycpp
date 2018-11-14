package pl.srslycpp.myWeb.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.srslycpp.myWeb.Entity.Product;
import pl.srslycpp.myWeb.commands.ProductFormCommand;

@Component
    public class ProductFormToProduct implements Converter<ProductFormCommand, Product> {

        @Override
        public Product convert(ProductFormCommand productForm) {
            Product product = new Product();
            if (productForm.getId() != null  && !StringUtils.isEmpty(productForm.getId())) {
                product.setId(new String(productForm.getId()));
            }
            product.setDescription(productForm.getDescription());
            product.setPrice(productForm.getPrice());
            product.setImageUrl(productForm.getImageUrl());
            return product;
        }

}
