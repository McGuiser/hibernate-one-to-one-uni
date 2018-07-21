package com.corey.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.corey.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// Create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// Create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// Start a transaction
			session.beginTransaction();
			
			// Query students
			List<Student> theStudents = session.createQuery("from Student").list();
			
			// Display the students
			displayStudents(theStudents);
			
			// Query students: lastName='Scott'
			theStudents = session.createQuery("from Student s where s.lastName='Scott'").list();
			
			// Display the students
			System.out.println("\n\nStudents who have last name of Scott: ");
			displayStudents(theStudents);
			
			// Query students: lastName='Halpert' OR firstName='Pam'
			theStudents = session.createQuery("from Student s where"
					+ " s.lastName='Halpert' OR s.firstName='Pam'").list();
			
			// Display the students
			System.out.println("\n\nStudents who have last name of Halpert of first name of Pam: ");
			displayStudents(theStudents);
			
			// Query students where email LIKE '%gmail.com'
			theStudents = session.createQuery("from Student s where s.email"
					+ " LIKE '%gmail.com'").list();
			
			// Display the students
			System.out.println("\n\nStudents whose email that ends in gmail.com: ");
			displayStudents(theStudents);
			
			// Commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
