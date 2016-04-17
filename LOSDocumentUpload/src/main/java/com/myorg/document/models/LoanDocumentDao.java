package com.myorg.document.models;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class LoanDocumentDao {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<LoanDocument> getByLoanId(int loanId) {
		return getSession().createQuery("from LoanDocument where loanId = :loanId").setParameter("loanId", loanId)
				.list();
	}
}
