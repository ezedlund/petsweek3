/**
 * @author Elijah Edlund - ezedlund
 * CIS175 - Fall 2021
 * Sep 12, 2022
 */
package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.PetItem;

public class ListItemHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PetsWeek3");

	public void insertItem(PetItem pi) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(pi);
		em.getTransaction().commit();
		em.close();
	}

	// show all
	public List<PetItem> showAllItems() {
		EntityManager em = emfactory.createEntityManager();
		List<PetItem> allItems = em.createQuery("SELECT i FROM PetItem i").getResultList();
		return allItems;
	}

	// delete
	public void deleteItem(PetItem toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetItem> typedQuery = em.createQuery(
				"select pi from PetItem pi where pi.owner = :selectedOwner and pi.name = :selectedName and pi.type = :selectedType",
				PetItem.class);
		typedQuery.setParameter("selectedOwner", toDelete.getOwner());
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedType", toDelete.getType());
		typedQuery.setMaxResults(1);
		PetItem result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	// search by id
	public PetItem searchForPetById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		PetItem found = em.find(PetItem.class, idToEdit);
		em.close();
		return found;
	}

	// update
	public void updatePet(PetItem toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	// search by owner
	public List<PetItem> searchForPetByOwner(String owner) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetItem> typedQuery = em.createQuery("select pi from PetItem pi where pi.owner = :selectedOwner",
				PetItem.class);
		typedQuery.setParameter("selectedOwner", owner);
		List<PetItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	// search by name
	public List<PetItem> searchForPetByName(String name) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetItem> typedQuery = em.createQuery("select pi from PetItem pi where pi.name = :selectedName",
				PetItem.class);
		typedQuery.setParameter("selectedName", name);
		List<PetItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	// search by type
	public List<PetItem> searchForPetByType(String type) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetItem> typedQuery = em.createQuery("select pi from PetItem pi where pi.type = :selectedType",
				PetItem.class);
		typedQuery.setParameter("selectedType", type);
		List<PetItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public void cleanUp() {
		emfactory.close();
	}
}
