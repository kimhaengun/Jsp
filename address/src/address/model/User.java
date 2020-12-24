package address.model;

public class User {
	private int id;
	private String name;
	private String phone;
	private String address;
	private String relation; // 친구, 학교, 회사, 가족
	
	@Override
	public String toString() {
		return id+"."+name;
	}
	
	public User(int id, String name, String phone, String address, String relation) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.relation = relation;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	
	
	
}
