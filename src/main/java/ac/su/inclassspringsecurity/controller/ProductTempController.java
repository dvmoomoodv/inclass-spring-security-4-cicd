package ac.su.inclassspringsecurity.controller;

import ac.su.inclassspringsecurity.domain.Product;
import ac.su.inclassspringsecurity.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductTempController {
    private final ProductService productService;

    @GetMapping("/thymeleaf/ex01")
    public String ex01(Model model) {
        Product product = new Product();
        product.setName("this is sample product");
        model.addAttribute("data", "This is assigned from controller!!");
        model.addAttribute("data1", product);
        model.addAttribute("data2", "second data");
        model.addAttribute("data3", "third data");
        return "thymeleaf/ex01";
    }

    @GetMapping("/thymeleaf/products-page")
    public String productPage(
        @RequestParam(value = "page", required = false, defaultValue = "0") int page,
        @RequestParam(value = "size", required = false, defaultValue = "15") int size,
        Model model
    ) {
        // List<Product> products = productService.getProductsPage(page, size);
        List<Product> products = productService.getValidProductsList(page, size);
        model.addAttribute("products", products);
        return "thymeleaf/products-page";
    }

    // layout 적용된 products-list 화면 랜딩 handler 추가!
    @GetMapping("/products-layout")
    public String productLayoutPage(
        @RequestParam(value = "page", required = false, defaultValue = "0") int page,
        @RequestParam(value = "size", required = false, defaultValue = "15") int size,
        Model model
    ) {
        List<Product> products = productService.getValidProductsList(page, size);
        model.addAttribute("products", products);
        return "products-layout-applied";
    }

    @GetMapping("/products-pagenav")
    public String productPageNav(
        @RequestParam(value = "page", required = false, defaultValue = "0") int page,
        @RequestParam(value = "size", required = false, defaultValue = "15") int size,
        Model model
    ) {
        Page<Product> productsPage = productService.getValidProductsPage(page, size);
        model.addAttribute("productsPage", productsPage);
        model.addAttribute("pageNum", page);
        model.addAttribute("pageSize", size);
        return "products-pagenav";
    }
}
