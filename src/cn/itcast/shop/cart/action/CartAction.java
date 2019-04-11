package cn.itcast.shop.cart.action;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.product.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

/**
 * 购物车Action
 */
public class CartAction extends ActionSupport {

    //接收pid
    private Integer pid;
    //接收数量count
    private Integer count;
    //注入商品Service
    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    //将购物项添加入购物车
    public String addCart(){
        //封装一个cartItem对象
        CartItem cartItem = new CartItem();
        //设置数量
        cartItem.setCount(count);
        //根据商品pid查询商品
        //设置商品
        cartItem.setProduct(productService.findByPid(pid));
        //将购物项添加到购物车
        Cart cart = getCart();
        cart.addCart(cartItem);
        return "addCart";
    }

    //清空购物车
    public String clearCart(){
        Cart cart = getCart();
        cart.clearCart();
        return "clearCart";
    }

    //从购物车中移除购物项
    public String removeCart(){
        //获得购物车对象
        Cart cart = getCart();
        cart.removeCart(pid);
        return "removeCart";
    }

    //我的购物车
    public String myCart(){
        return "myCart";
    }

    /**
     * 从session中获得购物车
     */
    private Cart getCart() {
        Cart cart = (Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            ServletActionContext.getRequest().getSession().setAttribute("cart",cart);
        }
        return cart;
    }

}
