package session05.tx.dao;

public interface BookDao {
	// 一般服務
	Integer getBookPrice(Integer bookId);
	Integer getBookStock(Integer bookId);
	Integer getWalletBalance(String username); //得客戶目前餘額
	
	//交易服務
	Integer reduceBookStock(Integer bookId, Integer amountToReduce); // 更新書本庫存(減量)
	Integer reduceWalletBalance(String username, Integer bookPrice); // 更新錢包餘額(減量)
	
	//Integer incrementBookStock(Integer bookId, Integer amountToReduce); // 更新書本庫存(增量)
	//Integer incrementWalletBalance(String username, Integer bookPrice); // 更新錢包餘額(增量)
}
