package forkjoin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuxuan on 1/29/15.
 */
public class ProductGenerator {
    public List<Product> generate(int size){
        List<Product> products=new ArrayList<Product>(size);
        for(int i=0;i<size;i++){
            Product product=new Product();
            product.setName("Product"+i);
            product.setPrice(10);
            products.add(product);
        }
        return products;
    }
}
