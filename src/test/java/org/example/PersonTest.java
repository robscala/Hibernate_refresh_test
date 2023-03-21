package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonTest
{
    private static final long PERSON_ID = 1L;
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory( "refresh_test" );
    }

    @After
    public void destroy() {
        entityManagerFactory.close();
    }

    @Test
    public void refresh_test() throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Person person1 = entityManager.find(Person.class, PERSON_ID);
        System.out.println("person1 before change: " + person1.getFirstName());

        String newFirstName = changeNameInSecondEntityManager();
        System.out.println("changed name to: " + newFirstName);

        entityManager.refresh(person1);    // Should retrieve new name
        System.out.println("person1 after change and refresh: " + person1.getFirstName());

        Person person2 = entityManager.find(Person.class, PERSON_ID);
        System.out.println("person2 from find: " + person2.getFirstName());
        System.out.println("person1 == person2: " + (person1 == person2));

        assertEquals("Get latest name", newFirstName, person1.getFirstName());
        assertTrue("Find returns same entity", (person1 == person2));

        entityManager.close();
    }

    private String changeNameInSecondEntityManager()
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Person person = entityManager.find(Person.class, PERSON_ID);
        entityManager.getTransaction().begin();
        String newFirstName = "Liza".equals(person.getFirstName()) ? "Henry" : "Liza";
        person.setFirstName(newFirstName);
        entityManager.getTransaction().commit();
        entityManager.close();
        return newFirstName;
    }
}