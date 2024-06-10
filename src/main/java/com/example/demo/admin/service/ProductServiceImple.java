package com.example.demo.admin.service;

import com.example.demo.admin.repository.ProductRepository1;
import com.example.demo.admin.repository.ProductViewRepository1;
import com.example.demo.buyer.entity.ProductView;
import com.example.demo.seller.domain.Product;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImple implements ProductService {

    private ProductRepository1 productRepository1;
    private ProductViewRepository1 productViewRepository1;

    public ProductServiceImple(ProductRepository1 productRepository1,
                               ProductViewRepository1 productViewRepository1) {
        this.productRepository1 = productRepository1;
        this.productViewRepository1 = productViewRepository1;
    }

    @Override
    public List getProductList(int activation) {
        List<Product> productList = productRepository1.findByproductActivation(activation);
        return productList;
    }

    @Transactional
    @Override
    public List<String> ProductApproval(String productId) {

        List<String> flag = new ArrayList<>();
        String[] ids = productId.split("/");

        for (int i = 0; i < ids.length; i++) {
            ids[i] = ids[i].trim();
        }

        for (String idpart : ids) {
            long id = Long.parseLong(idpart);
            Product product = productRepository1.findByproductId(id).orElse(null);

            if (product != null) {
                System.out.println("null 아니에용");
                product.setProductActivation(1);
                flag.add("1");
            } else {
                System.out.println("null 이에용");
                flag.add("0");
            }
        }

        return flag;
    }

    @Transactional
    @Override
    public List<String> ProductStop(String productId) {

        List<String> flag = new ArrayList<>();
        String[] ids = productId.split("/");

        for (int i = 0; i < ids.length; i++) {
            ids[i] = ids[i].trim();
        }

        for (String idpart : ids) {
            long id = Long.parseLong(idpart);
            Product product = productRepository1.findByproductId(id).orElse(null);

            if (product != null) {
                System.out.println("null 아니에용");
                product.setProductActivation(0);
                flag.add("1");
            } else {
                System.out.println("null 이에용");
                flag.add("0");
            }
        }

        return flag;
    }

    @Override
    public List<ProductView> productView(List<Product> product) {
        List<ProductView> productViews = new ArrayList<>();

        for (int i = 0; i < product.size(); i++) {
            Product test = product.get(i);
            ProductView view = productViewRepository1.findByproductId((int) test.getProductId()).orElse(null);
            productViews.add(view);
        }

        return productViews;
    }

}
