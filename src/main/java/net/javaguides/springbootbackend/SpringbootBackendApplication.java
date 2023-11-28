package net.javaguides.springbootbackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.javaguides.springbootbackend.model.Employee;
import net.javaguides.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
//		Employee employee = new Employee();
//		employee.setFirstName("Ramesh");
//		employee.setLastName("Fadatare");
//		employee.setEmailId("ramesh@gmail.com");
//		employeeRepository.save(employee);
//
//		Employee employee1 = new Employee();
//		employee1.setFirstName("John");
//		employee1.setLastName("Cena");
//		employee1.setEmailId("cena@gmail.com");
//		employeeRepository.save(employee1);

		// Load data from JSON file
		List<Employee> employees = loadDataFromJson();

		// Save data to the repository
		employeeRepository.saveAll(employees);
	}
	private List<Employee> loadDataFromJson() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		InputStream inputStream = new ClassPathResource("data.json").getInputStream();

		// Read data from JSON file into a list of Employee objects
		Employee[] employeesArray = objectMapper.readValue(inputStream, Employee[].class);

		return Arrays.asList(employeesArray);
	}
}