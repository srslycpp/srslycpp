package pl.srslycpp.myWeb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.srslycpp.myWeb.Entity.Product;
import pl.srslycpp.myWeb.Service.ProductService;
import pl.srslycpp.myWeb.commands.ProductCommand;

import javax.validation.Valid;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/projects/products/allProducts")
    public String getAllProducts(Model model){
         model.addAttribute("products", productService.getAllProduct());
         return "product/list";
    }

    @GetMapping("/projects/products/newProduct")
    public String newProduct(Model model){
        model.addAttribute("productForm", new Product());
        return "product/productform";
    }

    @PostMapping("/projects/products/save")
    public String saveOrUpdate(@Valid ProductCommand productCommand, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "product/productform";
        }
        Product saveProduct = productService.saveOrEditProduct(productCommand);

        return "redirect:/product/show/"+ saveProduct.getId();
    }
}
