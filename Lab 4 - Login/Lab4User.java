package lab4;


public class Lab4User {

	public static int counter = 1;
	
	public int id;
	
	public String name;
	
	public String userName;
	
	public String password;
	
	public Lab4User(int id, String name, String userName, String password){
		super();
		this.id=counter++;
		this.name=name;
		this.userName=userName;
		this.password=password;
		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return userName;
	}
	public void setUsername(String username) {
		this.userName = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
