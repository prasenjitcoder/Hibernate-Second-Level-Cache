package com.mahato.hibernate;

import org.hibernate.Session;

public class AppTest {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		//Here We are creating a User in DB - Start
        session.beginTransaction();
        User user = new User();
        user.setName("PrasenjitMahato");
        user.setEmail("PrasenjitMahato@yopmail.com");
        session.persist(user);
        session.getTransaction().commit();
        session.close();
      //Here We are creating a User in DB - End. 
        
        //Caching Part - 2nd level cache - Start
        Session session1 = HibernateUtil.getSessionFactory().openSession();
        User myUser = null;
        myUser = (User)session1.get(User.class, new Long(1));//Hibernate is putting This result in 2st level Cache for the all session
        System.out.println(myUser);
        myUser = (User)session1.get(User.class, new Long(1));//SO here we will get the Result from session , as cache is applicable for all the session
        System.out.println(myUser);
        
        
        Session session2 = HibernateUtil.getSessionFactory().openSession();
        myUser = (User)session2.get(User.class, new Long(1));//SO here we will get the Result from session , as cache is applicable for all the session
        System.out.println(myUser);
        Session session3 = HibernateUtil.getSessionFactory().openSession();
        myUser = (User)session3.get(User.class, new Long(1));//SO here we will get the Result from session , as cache is applicable for all the session
        System.out.println(myUser);
        //Caching Part - 2nd level cache - End
	}

}
