package com.myorg.document.models;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class DocumentDao {

	private static final int DATATYPE_ID = 2; 
	
	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Document> getAll() {
		return getSession().createQuery("from Document where dataTypeId = :dataTypeId").setParameter("dataTypeId", DATATYPE_ID).list();
	}
}
