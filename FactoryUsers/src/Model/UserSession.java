package Model;

public class UserSession {

	private static UserSession instance;
	private String role;

	// Private constructor to prevent instantiation
	private UserSession(String role) {
		this.role = role;
	}

	// Static method to initialize or get the instance
	public static UserSession getInstance(String role) {
		if (instance == null) {
			instance = new UserSession(role);
		}
		return instance;
	}

	// Overload for just accessing the session
	public static UserSession getInstance() {
		if (instance == null) {
			throw new IllegalStateException("User session has not been initialized.");
		}
		return instance;
	}

	public String getRole() {
		return role;
	}

	// Method to destroy session (e.g., on logout)
	public void destroy() {
		instance = null;
	}
}