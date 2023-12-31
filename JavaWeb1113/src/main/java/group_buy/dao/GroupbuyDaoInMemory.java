package group_buy.dao;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import static java.util.stream.Collectors.toList;

import group_buy.entity.Cart;
import group_buy.entity.CartItem;
import group_buy.entity.Product;
import group_buy.entity.User;


public class GroupbuyDaoInMemory implements GroupBuyDao {
	
	
	//In-Memory List
	private static List<User> users = new CopyOnWriteArrayList<>();
	private static List<Product> products = new CopyOnWriteArrayList<>();
	private static List<Cart> carts = new CopyOnWriteArrayList<>();
	private static List<CartItem> cartItems = new CopyOnWriteArrayList<>();

	@Override
	public List<User> findAllUsers() {
		return users;
	}

	@Override
	public void addUser(User user) {
		users.add(user);
	}

	@Override
	public Boolean updateUserPasswordById(Integer userId, String newPassword) {
		/*Optional<User> userOpt = users.stream()
				.filter(user -> user.getUserId().equals(userId))
				.findAny();
		if(userOpt.isPresent()) {
			userOpt.get().setPassword(newPassword);
			return true;
		}
		return false;*/
		return users.stream()
				.filter(user -> user.getUserId().equals(userId))
				.peek(user -> user.setPassword(newPassword))
				.findAny()
				.isPresent();
	}

	@Override
	public Optional<User> findUserByName(String username) {
		return users.stream().filter(user -> user.getUsername().equals(username)).findAny();
	}

	@Override
	public Optional<User> findUserByName(Integer userId) {
		return users.stream().filter(user -> user.getUserId().equals(userId)).findAny();
	}

	@Override
	public List<Product> findAllProducts() {
		return products;
	}

	@Override
	public Optional<Product> findProductById(Integer productId) {
		return products.stream().filter(product -> product.getProductId().equals(productId)).findAny();
	}

	@Override
	public void addProduct(Product product) {
		products.add(product);
	}

	@Override
	public Boolean updageProductLaunchById(Integer productId, Boolean isLaunch) {
		return products.stream()
						.filter(product -> product.getProductId().equals(productId))
						.peek(product -> product.setIsLaunch(isLaunch))
						.findAny()
						.isPresent();
	}

	@Override
	public void addCart(Cart cart) {
		carts.add(cart);
	}

	@Override
	public void addCartItem(CartItem cartItem) {
		cartItems.add(cartItem);
	}
	
	@Override
	public List<Cart> findAllCarts() {
		return carts;
	}
	
	@Override
	public Optional<Cart> findCartById(Integer cartId) {
		return carts.stream().filter(cart -> cart.getCartId().equals(cartId)).findAny();
	}

	@Override
	public Optional<CartItem> findCartItemById(Integer itemId) {
		return cartItems.stream().filter(cartItem -> cartItem.getItemId().equals(itemId)).findAny();
	}

	@Override
	public List<Cart> findCartsByUserId(Integer userId) {
		return carts.stream()
					.filter(cart -> cart.getUserId().equals(userId))
					.collect(toList());
		}

	@Override
	public List<Cart> findCartsByUserIdAndCheckOutStatus(Integer userId, Boolean isCheckout) {
		return carts.stream()
					.filter(cart->cart.getUserId().equals(userId) && cart.getIsCheckout().equals(isCheckout))
					.collect(toList());
	}

	@Override
	public Optional<Cart> findNoneCheckoutCartsByUserId(Integer userId) {
		return carts.stream()
				    .filter(cart->cart.getUserId().equals(userId))
				    .filter(cart->cart.getIsCheckout() == null || cart.getIsCheckout())
				    .findAny();
	}

	@Override
	public Boolean checkoutCartByUserId(Integer userId) {
		// 尋找該使用者尚未結帳購物車，才可結帳
		Optional<Cart> cartOpt = findNoneCheckoutCartsByUserId(userId);
		if(cartOpt.isPresent()) {
			cartOpt.get().setIsCheckout(true);
			return true;
		}
		return false;
	}

	@Override
	public Boolean checkoutCartById(Integer cartId) {
		return carts.stream()
					.filter(cart->cart.getCartId().equals(cartId))
					.peek(cart->cart.setIsCheckout(true)) //結帳
					.findAny()
					.isPresent();
	}

	@Override
	public Boolean removeCartItemById(Integer itemId) {
		Optional<CartItem> cartItemOpt = cartItems.stream()
				.filter(cartItem -> cartItem.getItemId().equals(itemId))
				.findAny();
		if(cartItemOpt.isPresent()) {
			cartItems.remove(cartItemOpt.get()); //移除物件
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateCartItemQuantityById(Integer itemId, Integer quantity) {
		return  cartItems.stream()
				.filter(cartItem -> cartItem.getItemId().equals(itemId))
				.peek(cartItem -> cartItem.setQuantity(quantity))
				.findAny()
				.isPresent();
	}

}
