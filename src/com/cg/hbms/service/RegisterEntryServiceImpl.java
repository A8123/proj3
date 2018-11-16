package com.cg.hbms.service;

import java.util.List;

import com.cg.lms.dao.IRegisterEntryDao;
import com.cg.lms.dao.RegisterEntryDaoImpl;
import com.cg.lms.exception.LMSException;
import com.cg.lms.model.RegisterEntry;

public class RegisterEntryServiceImpl implements IRegisterEntryService {

	private IRegisterEntryDao entryDao;
	
	public RegisterEntryServiceImpl() {
		entryDao=new RegisterEntryDaoImpl();
	}
	
	@Override
	public List<RegisterEntry> listReservedBooks() throws LMSException {
		return entryDao.listReservedBooks();
	}

	@Override
	public List<RegisterEntry> listIssuedBooks() throws LMSException {
		return entryDao.listIssuedBooks();
	}

	@Override
	public boolean reserveBook(RegisterEntry regEntry) throws LMSException {
		return entryDao.reserveBook(regEntry);
	}

	@Override
	public boolean issueBook(RegisterEntry regEntry) throws LMSException {
		return entryDao.issueBook(regEntry);
	}

	@Override
	public boolean returnBook(RegisterEntry regEntry) throws LMSException {
		return entryDao.returnBook(regEntry);
	}

	@Override
	public RegisterEntry findEntry(int logid) throws LMSException {
		return entryDao.findEntry(logid);
	}
}
