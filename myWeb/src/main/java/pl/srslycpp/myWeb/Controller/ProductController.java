package pl.srslycpp.myWeb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.srslycpp.myWeb.Service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/projects/products/allProducts")
    String getAllProducts(Model model){
         model.addAttribute("products", productService.getAllProduct());
         return "product/list";
    }

}
