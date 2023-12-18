package guestbook.model;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GuestbookDaoInMemory implements GuestbookDao{
	//模擬資料庫
	private static List<Guestbook> guestbooks;
	static { //類別成員初始區段
		guestbooks = new CopyOnWriteArrayList<Guestbook>();
		// 預設4筆資料
		guestbooks.add(new Guestbook(1,"John",18,"M","今天天氣不好",new Date()));
		guestbooks.add(new Guestbook(2,"May", 19,"F","Happy Birthday!",new Date()));
		guestbooks.add(new Guestbook(3,"Jack",28,"M","123456",new Date()));
		guestbooks.add(new Guestbook(4,"Rose",23,"F","天天天藍",new Date()));
	}
	
	public void create(Guestbook guestbook) {
		guestbooks.add(guestbook);
		
	}

	public List<Guestbook> readAll() {
		return guestbooks   ;
	}

}
