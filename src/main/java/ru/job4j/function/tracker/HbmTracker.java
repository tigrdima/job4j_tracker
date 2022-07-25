package ru.job4j.function.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();

        session.save(item);

        session.getTransaction().commit();
        session.close();

        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Session session = sf.openSession();
        session.beginTransaction();

        int rsl = session
                .createQuery("update Item i set i.name = :iName, i.created = :iCreat where i.id = :iId")
                .setParameter("iName", item.getName())
                .setParameter("iCreat", item.getCreated())
                .setParameter("iId", id)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();

        return rsl != 0;
    }

    @Override
    public boolean delete(int id) {
        Session session = sf.openSession();
        session.beginTransaction();

        int rsl = session
                .createQuery("delete Item where id = :iId")
                .setParameter("iId", id)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();

        return rsl != 0;
    }

    @Override
    public List<Item> findAll() {

        Session session = sf.openSession();
        session.beginTransaction();

        List rsl = session.createQuery("from Item").list();

        session.getTransaction().commit();
        session.close();

        return rsl;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        session.beginTransaction();

        List rsl = session.createQuery("from Item i where i.name = :iName").setParameter("iName", key).list();

        session.getTransaction().commit();
        session.close();

        return rsl;
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();

        Item item = (Item) session
                .createQuery("from Item i where i.id = :iId")
                .setParameter("iId", id)
                .uniqueResult();

        session.getTransaction().commit();
        session.close();

        return item;
    }

    @Override
    public void findAllByReact(Observe<Item> observe) {

    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
