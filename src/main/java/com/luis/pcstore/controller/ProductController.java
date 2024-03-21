package com.luis.pcstore.controller;

import com.luis.pcstore.document.Product;
import com.luis.pcstore.dto.CreatedProductDto;
import com.luis.pcstore.dto.ProductDto;
import com.luis.pcstore.service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping(value = "/products")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    //----------------------------------------------------------------------------
    @GetMapping({"" , "/"})
    public String showProductList(Model model, @PageableDefault(size = 6, sort = "name") Pageable pageable){
        try {
            Page<ProductDto> productsDtoList = productService.showAllProducts(pageable);
            model.addAttribute("page",productsDtoList);
            log.info("El controlador ProductController ha sido ejecutado correctamente y ha devuelto la lista de productos.");
        } catch (Exception e) {
            log.error("Error al obtener la lista de productos: ", e);
            model.addAttribute("errorMessage", "No se pueden cargar los productos en este momento.");
            return "error";
        }
        return "products/productList";
    }

    //----------------------------------------------------------------------------
    @GetMapping("/category/{category}")
    public String showProductsByCategory(Model model,
                                         @PathVariable String category,
                                         @PageableDefault(size = 5, sort = "name") Pageable pageable) {
        try {
            Page<ProductDto> productDtoCategory = productService.findProductsByCategory(category, pageable);
            model.addAttribute("page", productDtoCategory);
            log.info("La funcion showProductsByCategory Se ha ejecutado correctamente y ha devuelto la lista de productos por categoria.");
        } catch (Exception e) {
            log.error("Error al obtener la lista de productos: ", e);
            model.addAttribute("errorMessage", "No se pueden cargar los productos en este momento.");
            return "error";
        }
        return "products/productList";
    }

    //----------------------------------------------------------------------------
   @GetMapping("/create")
    public String showCreatePage(Model model){
       CreatedProductDto createdProductDto = new CreatedProductDto();
        model.addAttribute("productDto", createdProductDto);
        return "products/CreateProduct";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute CreatedProductDto createdProductDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "products/CreateProduct";
        }

        productService.saveProduct(createdProductDto, result);
        log.info("El endpoint create se ha ejecutado correctamente y ha guardado el nuevo producto");
        redirectAttributes.addFlashAttribute("successMessage", "Product successfully created!");
        return "redirect:/products";
    }

    //----------------------------------------------------------------------------
  @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam(name = "id") UUID id_product){
        log.info("Editing product with ID: " + id_product);
        try{
            Product product = productService.findProduct(id_product);

            log.info("Product found: " + product.getName());
            model.addAttribute("product", product);

            ProductDto productDto = ProductDto.builder()
                    .name(product.getName())
                    .brand(product.getBrand())
                    .category(product.getCategory())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .build();

            model.addAttribute("productDto", productDto);

        }catch (Exception e){
            log.error("Exception: " + e.getMessage());
            return "products/EditProduct";
        }

        return "products/EditProduct";
    }

    @PostMapping("/edit")
    public String updateProduct(
            Model model,
            @RequestParam(name = "id") UUID id_product,
            @Valid @ModelAttribute CreatedProductDto productDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            Product product = productService.findProduct(id_product);
            model.addAttribute("product", product);
            return "products/EditProduct";
        }

        try {
            productService.updateProduct(productDto, id_product, result);

            redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully!");
        } catch (Exception ex) {
            log.error("Exception: " + ex.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating product: " + ex.getMessage());
        }

        return "redirect:/products";
    }

    //----------------------------------------------------------------------------
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam(name = "id") UUID id_product, RedirectAttributes redirectAttributes) {
        try {
            Product product = productService.findProduct(id_product);
            // La lógica para eliminar la imagen se mueve al servicio.
            productService.deleteImageFile(product.getImageFileName());

            productService.deleteProduct(id_product);
            redirectAttributes.addFlashAttribute("successMessage", "Producto eliminado con éxito.");
        } catch (Exception e) {
            log.error("Error al eliminar el producto: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar el producto.");
        }

        return "redirect:/products";
    }

}

