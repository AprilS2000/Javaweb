package guestbook.model;

import java.util.Date;

//定義每一筆留言資訊(POJO)
public class Guestbook {
	private Integer id;
	private String nickname;
	private Integer age;
	private String sex;
	private String message;
	private Date date;
	
	public Guestbook() {
		
	}
	
	public Guestbook(Integer id, String nickname, Integer age, String sex, String message, Date date) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.age = age;
		this.sex = sex;
		this.message = message;
		this.date = date;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
