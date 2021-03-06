package com.niit.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.dao.CartDAO;
import com.niit.dao.CategoryDAO;
import com.niit.model.Cart;
import com.niit.model.CartItem;
import com.niit.model.Category;


@Repository("cartDAO")
@Transactional

	public class CartDAOImpl  implements CartDAO
	{
		@Autowired
		SessionFactory sessionFactory;

	
	@Override
	public boolean addCartItem(CartItem cartItem) 
	{
		try
		{
			sessionFactory.getCurrentSession().save(cartItem);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
			return false;
		}
	}

	@Override
	public boolean deleteCartItem(CartItem cartItem)
	{
		try
		{
			sessionFactory.getCurrentSession().delete(cartItem);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean updateCartItem(CartItem cartItem) 
	{
		try
		{
			sessionFactory.getCurrentSession().update(cartItem);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public List<CartItem> retrieveCartItems(String username) 
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from CartItem where username=:uname and pstatus='N'");
		query.setParameter("uname", username);
		List<CartItem> listCartItem=query.list();
		session.close();
		return listCartItem;
	}

	@Override
	public CartItem getCartItem(int cartItemId) 
	{
		Session session=sessionFactory.openSession();
		CartItem cartItem=session.get(CartItem.class,cartItemId);
		session.close();
		return cartItem;
	}
	
}
