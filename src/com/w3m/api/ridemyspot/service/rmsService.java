package com.w3m.api.ridemyspot.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JDOCursorHelper;
import com.w3m.api.ridemyspot.entity.Comments;
import com.w3m.api.ridemyspot.entity.PMF;
import com.w3m.api.ridemyspot.entity.Spots;
import com.w3m.api.ridemyspot.entity.Users;

@Api(name = "rmsendpoint",version="v1",description="API of RideMySpot including spots and comments", namespace = @ApiNamespace(ownerDomain = "w3m.com", ownerName = "w3m.com", packagePath = "api.ridemyspot.entity"))
public class rmsService {

	
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listSpots")
	public CollectionResponse<Spots> listSpots(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		PersistenceManager mgr = null;
		Cursor cursor = null;
		List<Spots> execute = null;

		try {
			mgr = getPersistenceManager();
			Query query = mgr.newQuery(Spots.class);
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				HashMap<String, Object> extensionMap = new HashMap<String, Object>();
				extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
				query.setExtensions(extensionMap);
			}

			if (limit != null) {
				query.setRange(0, limit);
			}

			execute = (List<Spots>) query.execute();
			cursor = JDOCursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Spots obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Spots> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getSpots")
	public Spots getSpots(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		Spots spots = null;
		try {
			spots = mgr.getObjectById(Spots.class, id);
		} finally {
			mgr.close();
		}
		return spots;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param spots the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertSpots")
	public Spots insertSpots(Spots spots) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if(spots.getId() != null){
				if (containsSpots(spots)) {
					
					throw new EntityExistsException("Object already exists");
				}
			}
			mgr.makePersistent(spots);
		} finally {
			mgr.close();
		}
		return spots;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param spots the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateSpots")
	public Spots updateSpots(Spots spots) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if (!containsSpots(spots)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.makePersistent(spots);
		} finally {
			mgr.close();
		}
		return spots;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeSpots")
	public void removeSpots(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			Spots spots = mgr.getObjectById(Spots.class, id);
			mgr.deletePersistent(spots);
		} finally {
			mgr.close();
		}
	}

	private boolean containsSpots(Spots spots) {
		PersistenceManager mgr = getPersistenceManager();
		boolean contains = true;
		try {
			mgr.getObjectById(Spots.class, spots.getId());
		} catch (javax.jdo.JDOObjectNotFoundException ex) {
			contains = false;
		} finally {
			mgr.close();
		}
		return contains;
	}

	////////////////////////////////////////users//////////////////////////
	

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listUsers")
	public CollectionResponse<Users> listUsers(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		PersistenceManager mgr = null;
		Cursor cursor = null;
		List<Users> execute = null;

		try {
			mgr = getPersistenceManager();
			Query query = mgr.newQuery(Users.class);
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				HashMap<String, Object> extensionMap = new HashMap<String, Object>();
				extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
				query.setExtensions(extensionMap);
			}

			if (limit != null) {
				query.setRange(0, limit);
			}

			execute = (List<Users>) query.execute();
			cursor = JDOCursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Users obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Users> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getUsers")
	public Users getUsers(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		Users users = null;
		try {
			users = mgr.getObjectById(Users.class, id);
		} finally {
			mgr.close();
		}
		return users;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param users the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertUsers")
	public Users insertUsers(Users users) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if(users.getId() != null){
				if (containsUsers(users)) {
					throw new EntityExistsException("Object already exists");
				}
			}
			mgr.makePersistent(users);
		} finally {
			mgr.close();
		}
		return users;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param users the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateUsers")
	public Users updateUsers(Users users) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if (!containsUsers(users)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.makePersistent(users);
		} finally {
			mgr.close();
		}
		return users;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeUsers")
	public void removeUsers(@Named("id") Long id) {
		//TODO supress comment for this user too!
		
		PersistenceManager mgr = getPersistenceManager();
		try {
			Users users = mgr.getObjectById(Users.class, id);
			mgr.deletePersistent(users);
		} finally {
			mgr.close();
		}
	}

	private boolean containsUsers(Users users) {
		PersistenceManager mgr = getPersistenceManager();
		boolean contains = true;
		try {
			mgr.getObjectById(Users.class, users.getId());
		} catch (javax.jdo.JDOObjectNotFoundException ex) {
			contains = false;
		} finally {
			mgr.close();
		}
		return contains;
	}
	
	////////////////////////////////////////comments//////////////////////////
	
	/**
	   * This method lists all the entities inserted in datastore.
	   * It uses HTTP GET method and paging support.
	   *
	   * @return A CollectionResponse class containing the list of all entities
	   * persisted and a cursor to the next page.
	   */
	  @SuppressWarnings({"unchecked", "unused"})
	  @ApiMethod(name = "listComments")
	  public CollectionResponse<Comments> listComments(
		@Nullable @Named("pidSpot") Long idSpot,
	    @Nullable @Named("cursor") String cursorString,
	    @Nullable @Named("limit") Integer limit) {

	    PersistenceManager mgr = null;
	    Cursor cursor = null;
	    List<Comments> execute = null;

	    try{
	      mgr = getPersistenceManager();
	      Query query = mgr.newQuery(Comments.class);
	      if (cursorString != null && cursorString != "") {
	        cursor = Cursor.fromWebSafeString(cursorString);
	        HashMap<String, Object> extensionMap = new HashMap<String, Object>();
	        extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
	        query.setExtensions(extensionMap);
	      }

	      if (limit != null) {
	        query.setRange(0, limit);
	      }
	      
	      if (idSpot != null){
		      query.setFilter("idSpot == pIdSpot");
		      query.declareParameters("Long pIdSpot");
	      }
		      
	      execute = (List<Comments>) query.execute(idSpot);
	      cursor = JDOCursorHelper.getCursor(execute);
	      if (cursor != null) cursorString = cursor.toWebSafeString();

	      // Tight loop for fetching all entities from datastore and accomodate
	      // for lazy fetch.
	      for (Comments obj : execute){
	    	  try{
	    		  obj.setUser(getUsers(obj.getIdUser()).getName());
	    	  } catch (Exception e){
	    		  obj.setUser("");
	    	  }
	      }
	    } finally {
	      mgr.close();
	    }

	    return CollectionResponse.<Comments>builder()
	      .setItems(execute)
	      .setNextPageToken(cursorString)
	      .build();
	  }

	  /**
	   * This method gets the entity having primary key id. It uses HTTP GET method.
	   *
	   * @param id the primary key of the java bean.
	   * @return The entity with primary key id.
	   */
	  @ApiMethod(name = "getComments")
	  public Comments getComments(@Named("id") Long id) {
	    PersistenceManager mgr = getPersistenceManager();
	    Comments comments  = null;
	    try {
	      comments = mgr.getObjectById(Comments.class, id);
	      comments.setUser(getUsers(comments.getIdUser()).getName());
	    } finally {
	      mgr.close();
	    }
	    return comments;
	  }

	  /**
	   * This inserts a new entity into App Engine datastore. If the entity already
	   * exists in the datastore, an exception is thrown.
	   * It uses HTTP POST method.
	   *
	   * @param comments the entity to be inserted.
	   * @return The inserted entity.
	   */
	  @ApiMethod(name = "insertComments")
	  public Comments insertComments(Comments comments) {
	    PersistenceManager mgr = getPersistenceManager();
	    try {
	    	if(comments.getId() != null){
		      if(containsComments(comments)) {
		        throw new EntityExistsException("Object already exists");
		      }
	    	}
	      mgr.makePersistent(comments);
	      Spots spot = getSpots(comments.getIdSpot());
	      spot.addNote(comments.getNote());
	      updateSpots(spot);
	    } finally {
	      mgr.close();
	    }
	    return comments;
	  }

	  /**
	   * This method is used for updating an existing entity. If the entity does not
	   * exist in the datastore, an exception is thrown.
	   * It uses HTTP PUT method.
	   *
	   * @param comments the entity to be updated.
	   * @return The updated entity.
	   */
	  @ApiMethod(name = "updateComments")
	  public Comments updateComments(Comments comments) {
	    PersistenceManager mgr = getPersistenceManager();
	    try {
	      if(!containsComments(comments)) {
	        throw new EntityNotFoundException("Object does not exist");
	      }
	      mgr.makePersistent(comments);
	    } finally {
	      mgr.close();
	    }
	    return comments;
	  }

	  /**
	   * This method removes the entity with primary key id.
	   * It uses HTTP DELETE method.
	   *
	   * @param id the primary key of the entity to be deleted.
	   */
	  @ApiMethod(name = "removeComments")
	  public void removeComments(@Named("id") Long id) {
	    PersistenceManager mgr = getPersistenceManager();
	    try {
	      Comments comments = mgr.getObjectById(Comments.class, id);
	      mgr.deletePersistent(comments);
	    } finally {
	      mgr.close();
	    }
	  }

	  private boolean containsComments(Comments comments) {
	    PersistenceManager mgr = getPersistenceManager();
	    boolean contains = true;
	    try {
	      mgr.getObjectById(Comments.class, comments.getId());
	    } catch (javax.jdo.JDOObjectNotFoundException ex) {
	      contains = false;
	    } finally {
	      mgr.close();
	    }
	    return contains;
	  }


	private static PersistenceManager getPersistenceManager() {
		return PMF.get().getPersistenceManager();
	}
}
