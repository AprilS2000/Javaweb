package test1;

import java.util.ArrayList;

import group_buy.dao.GroupBuyDao;
import group_buy.dao.GroupbuyDaoInMemory;
import group_buy.entity.Cart;
import group_buy.entity.CartItem;
import group_buy.entity.Product;
import group_buy.entity.User;

public class TestGroupBuy {
	
	public static void main(String[] args) {
		GroupBuyDao dao = new GroupbuyDaoInMemory();
		//加入一個 user
		User user1 = new User(1, "John", "1234", 1);
		User user2 = new User(2, "Mary", "5678", 2);
		dao.addUser(user1);
		dao.addUser(user2);
		
		//加入 Product
		Product product1 = new Product(1, "雞腳凍", 50, "包", true);
		Product product2 = new Product(2, "可樂", 150, "箱", true);
		Product product3 = new Product(3, "漢堡", 100, "包", false);
		dao.addProduct(product1);
		dao.addProduct(product2);
		dao.addProduct(product3);
		
		//加入購物車
		Cart cart1 = new Cart(301, 101, false);
		cart1.setUser(user1);   //設定關聯
		CartItem item1 = new CartItem(401, 301, 3, 10);
		CartItem item2 = new CartItem(402, 301, 2, 15);
		cart1.setCartItems(new ArrayList<CartItem>()); 	//設定 cartItem 集合， 鳳來放多筆CartItem
		cart1.getCartItems().add(item1);
		cart1.getCartItems().add(item2);

		dao.addCart(cart1);
		//加入購物車明細
		
		//System.out.println(dao.findAllUsers());
		System.out.println(dao.findAllProducts());
		System.out.println(dao.findAllCarts());
		
		
		System.out.println("-----------------------");
		//Cart cart = dao.findNoneCheckoutCartsByUserId(101).get();
		//System.out.printf("user id: %d username: %s\n", cart.getUserId(), cart.getUser().getUsername());
		/*for(CartItem item: cart.getCartItems()) {
			System.out.printf("Id: %d 商品Id: %d 商品名稱: %s 數量: %d \n", 
					item.getItemId(), 
					item.getProductId(), 
					item.getProduct() == null ?"null" : item.getProduct().getProductName(),
					item.getQuantity());
		}*/
	}
}
