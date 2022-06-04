package ru.job4j.function.tracker;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) throws Exception {
        try {
            Session session = sf.openSession();
            session.beginTransaction();

            session.save(item);

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return item;

    }

    @Override
    public boolean replace(int id, Item item) throws Exception {
        boolean rsl = false;
        try {
            Session session = sf.openSession();
            session.beginTransaction();

            Query query = session.createQuery("update Item i set i.name = :iName, i.created = :iCreat where i.id = :iId");
            query.setParameter("iName", item.getName());
            query.setParameter("iCreat", item.getCreated());
            query.setParameter("iId", id);
            rsl = query.executeUpdate() == 1;

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) throws Exception {
        boolean rsl = false;
        try {
            Session session = sf.openSession();
            session.beginTransaction();

            Query query = session.createQuery("delete Item where id = :iId");
            query.setParameter("iId", id);
            rsl = query.executeUpdate() == 1;

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() throws Exception {
        List rsl = new ArrayList<>();

        try {
            Session session = sf.openSession();
            session.beginTransaction();

            Query query = session.createQuery("from Item");
            rsl =  query.list();

            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return rsl;
    }

    @Override
    public List<Item> findByName(String key) throws Exception {
        List rsl = new ArrayList<>();
        try {
            Session session = sf.openSession();
            session.beginTransaction();

            Query query = session.createQuery("from Item i where i.name = :iName");
            query.setParameter("iName", key);
            rsl = query.list();

            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return rsl;
    }

    @Override
    public Item findById(int id) throws Exception {
        Item item = new Item();
        try {
            Session session = sf.openSession();
            session.beginTransaction();

            Query query = session.createQuery("from Item i where i.id = :iId");
            query.setParameter("iId", id);
            item = (Item) query.uniqueResult();

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return item;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
