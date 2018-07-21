package com.corey.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.corey.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {

		// Create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// Create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// Create a student object
			System.out.println("Creating a new student object...");
			Student tempStudent = new Student("Michael", "Scott", "michael@dmifflin.com");
			
			// Start a transaction
			session.beginTransaction();
			
			// Save the student object
			System.out.println("Saving the student...");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			// Commit transaction
			session.getTransaction().commit();
			
			// Retrieve object from database
			
			// Find out the student's id: primary key
			System.out.println("Saved student. Generated id: " + tempStudent.getId());
			
			// Get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// Retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get complete: " + myStudent);
			
			// Commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
			
		} finally {
			factory.close();
		}

	}

}
