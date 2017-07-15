package com.mohamed265.weatherchecker.dao.daoImpl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mohamed265.weatherchecker.dao.NoteDao;
import com.mohamed265.weatherchecker.entity.Note;

/**
 * 
 * @author mohamed265
 *
 */
@Repository("NoteDao")
public class NoteDaoImpl implements NoteDao {
	final private static Logger logger = Logger.getLogger(DefaultDaoImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void save(Note note) throws Exception {
		em.persist(note);
	}

	@Override
	public List<Note> getAllNoteByDate(Date date) {
		Query query = em.createNamedQuery("Note.getAllByDate", Note.class);
		query.setParameter("cdate", date);
		try {
			return query.getResultList();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Note> getAllNotesByUser(int id) {
		Query query = em.createNamedQuery("Note.getAllNotesByUser", Note.class);
		query.setParameter("userId", id);
		try {
			return query.getResultList();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
}