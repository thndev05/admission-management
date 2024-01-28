package model;

public class Admin {
    private String username;
    private String password;
    private String fullname;

    public Admin(String username, String password, String fullname) {
        this.setUsername(username);
        this.setPassword(password);
        this.setFullname(fullname);
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

}