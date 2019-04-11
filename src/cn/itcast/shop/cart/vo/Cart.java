package cn.itcast.shop.cart.vo;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 */
public class Cart implements Serializable {

    //购物项集合
    private Map<Integer,CartItem> map = new LinkedHashMap<Integer, CartItem>();
    //购物总计
    private double total;

    public double getTotal() {
        return total;
    }

    public java.util.Collection<CartItem> getCartItems() {
        return map.values();
    }

    //购物车功能
    //1、将购物项添加到购物车
    public void addCart(CartItem cartItem){
        Integer pid = cartItem.getProduct().getPid();
        if (map.containsKey(pid)){
            int count = map.get(pid).getCount() + cartItem.getCount();
            map.get(pid).setCount(count);
        } else {
            map.put(pid,cartItem);
        }
        total += cartItem.getSubtotal();
    }
    //2、从购物车一处购物项
    public void removeCart(Integer pid){
        CartItem cartItem = map.remove(pid);
        total -= cartItem.getSubtotal();
    }
    //3、清空购物车
    public void clearCart(){
        //将购物项清空
        map.clear();
        //将总计置0
        total = 0;
    }


}
