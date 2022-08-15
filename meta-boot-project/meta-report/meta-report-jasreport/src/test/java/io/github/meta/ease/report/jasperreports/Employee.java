package io.github.meta.ease.report.jasperreports;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {

	private int id;
	private String name;
	private String role;
	private String address;

	public Employee(int id, String name, String role, String address) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.address = address;
	}
}