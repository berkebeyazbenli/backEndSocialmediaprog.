package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		String jdbcUrl = "jdbc:mysql://localhost:3306/your_database_name";
		String username = "your_username";
		String password = "your_password";

		try {
			// JDBC sürücüsünü yükle
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Veritabanı bağlantısını oluştur
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Bağlantı başarılı olduğunda buraya ulaşılır
			System.out.println("Veritabanı bağlantısı başarılı!");

			// Bağlantıyı kapat
			connection.close();
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC sürücüsü bulunamadı.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Veritabanı bağlantısı başarısız!");
			e.printStackTrace();
		}
	}
}
