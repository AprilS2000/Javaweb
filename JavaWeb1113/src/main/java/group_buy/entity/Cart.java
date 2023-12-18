package group_buy.entity;

import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

/*
 * 3. 購物車主檔(Master)
 +--------+----------+-----------+------------+--------------+
| cartId |  userId  | cartItems | isCheckout | checkoutTime |
+--------+----------+-----------+------------+--------------+
|  201   |   101    | [1, 2]    |    true    | /-/-/  0:0:0 |
|  202   |   102    | [3]       |    false   |              |
|  203   |   103    | [4, 5]    |    true    | /-/-/  0:0:0 |
|  204   |   103    | []        |    false   |              |
|  205   |   101    | [6]       |    true    | /-/-/  0:0:0 |
+--------+----------+-----------+------------+--------------+
 * 
 * 
 */


public class Cart {
	// Entity 資料
	private Integer cartId;
	private Integer userId;
	private User user;  //使用者物件
	private List<CartItem> cartItems;  //購物車明細
	private Boolean isCheckout;  //是否結帳
	private Date checkoutTime;
	
	
	public Cart(Integer cartId, Integer userId, Boolean isCheckout) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		//利用 userId 查 User 物件
		
		//利用cartId 查 CartItems
		
		this.isCheckout = isCheckout;
	}


	public Integer getCartId() {
		return cartId;
	}


	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<CartItem> getCartItems() {
		return cartItems;
	}


	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}


	public Boolean getIsCheckout() {
		return isCheckout;
	}


	public void setIsCheckout(Boolean isCheckout) {
		this.isCheckout = isCheckout;
		//商業邏輯
		if(isCheckout) {
			if(isCheckout) {
				//若 isCheckout == true，則設定 checkoutTime
				setCheckoutTime(new Date());
			}
		}
	}


	public Date getCheckoutTime() {
		return checkoutTime;
	}


	private void setCheckoutTime(Date checkoutTime) {
		this.checkoutTime = checkoutTime;
	}


	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + ", user=" + user + ", cartItems=" + cartItems
				+ ", isCheckout=" + isCheckout + "]";
	}
	
	
}
