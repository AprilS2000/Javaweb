package session01.test;

public class User {

	private final String name;
	private final int age;
	private final String email;
	
	public User(Builder builder) {
		this.name = builder.name;
		this.age = builder.age;
		this.email = builder.email;
	}
	
	public User(String name, int age, String email) {
		super();
		this.name = name;
		this.age = age;
		this.email = email;
	}
	
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String name;
		private int age;
		private String email;
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder age(int age) {
			this.age = age;
			return this;
		}
		
		public Builder email(String email) {
			this.email = email;
			return this;
		}

		
		public User build() {
			return new User(this);
		}
		
		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		public String getEmail() {
			return email;
		}
	}
}
