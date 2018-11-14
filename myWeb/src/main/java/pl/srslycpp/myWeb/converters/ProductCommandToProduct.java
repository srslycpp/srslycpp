package pl.srslycpp.myWeb.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.srslycpp.myWeb.Entity.Product;
import pl.srslycpp.myWeb.commands.ProductCommand;

@Component
    public class ProductCommandToProduct implements Converter<ProductCommand, Product> {

        @Override
        public Product convert(ProductCommand productForm) {
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
