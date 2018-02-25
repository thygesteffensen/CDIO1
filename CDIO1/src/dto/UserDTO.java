package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDTO implements Serializable, Cloneable, Comparable<UserDTO>{
	private static final long serialVersionUID = 4545864587995944260L;
	private int	id = 0;                     
	private String name = "";                
	private String initials = "";
	private String cpr = "";
	private String password = "";
	private List<String> roles = new ArrayList<>();
	
	public UserDTO() {	
		
	}
	
	public UserDTO(int id, String name, 
				   String initials, String cpr, 
				   String password, List<String> roles) {
		setId(id);
		setName(name);
		setInitials(initials);
		setCpr(cpr);
		setPassword(password);
		setRoles(roles);
	}
	
	private void setPassword(String password) {
		this.password = password;		
	}

	private void setCpr(String cpr) {
		this.cpr = cpr;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIni() {
		return initials;
	}
	public void setInitials(String initials) {
		this.initials = initials;
	}

	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public void addRole(String role){
		this.roles.add(role);
	}
	
	/**
	 * Removes role from the role list
	 * @param role
	 * @return true if role existed, false if not
	 */
	public boolean removeRole(String role){
		return this.roles.remove(role);
	}

	@Override
	public String toString() {
		return String.format("UserDTO [id= %d, name= %s, initials= %s, roles= %s, password= %s]"
								, id, name, initials, roles, password);
	}
	
	@Override
	public UserDTO clone() {
		List<String> cloneRoles = new ArrayList<>();
	    for(String s : roles) {
	    	cloneRoles.add(s);
	    }
		return new UserDTO(id,name,initials, cpr, password, cloneRoles); 
	}
	
	public void Update(UserDTO user) {
		name = user.getName();                
		roles = user.getRoles();
	}	
	
	public boolean hasId(int id) {
		return getId() == id;
	}
	
	@Override
	public int compareTo(UserDTO otherUser) {
		return ( getId() < otherUser.getId() ? -1 : (getId() == otherUser.getId() ? 0 : 1) ); 
	}
}
