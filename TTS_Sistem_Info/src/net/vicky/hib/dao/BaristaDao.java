package net.vicky.hib.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.vicky.hib.model.Barista;
import net.vicky.hib.util.HibUtil;

public class BaristaDao {
	public void saveUser(Barista user) {
		Transaction transaction = null;
		try (Session session = HibUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			
			session.save(user);
			
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	 public boolean validate(String userName, String password) {

	        Transaction transaction = null;
	        Barista user = null;
	        try (Session session = HibUtil.getSessionFactory().openSession()) {
	           
	            transaction = session.beginTransaction();
	            
	            user = (Barista) session.createQuery("FROM User U WHERE U.username = :userName").setParameter("userName", userName)
	                .uniqueResult();

	            if (user != null && user.getPassword().equals(password)) {
	                return true;
	            }
	            
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	        return false;
	 }
}
