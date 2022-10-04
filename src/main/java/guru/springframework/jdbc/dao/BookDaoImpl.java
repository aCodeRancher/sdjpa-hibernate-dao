package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao{

    private final EntityManagerFactory emf;

    public BookDaoImpl(EntityManagerFactory emf){
        this.emf = emf;
    }

    @Override
    public Book getById(Long id){

        return getEntityManager().find(Book.class, id);
    }

    @Override
    public Book findBookByTitle(String title){
        TypedQuery<Book> query = getEntityManager().createQuery(
                "Select b from Book b WHERE b.title = :title", Book.class);
        query.setParameter("title", title);
        return query.getSingleResult();
    }

    @Override
    public Book saveNewBook(Book book){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(book);
        em.flush();
        em.getTransaction().commit();
        return book;
    }

    @Override
    public Book updateBook(Book book){

        return null;
    }

    @Override
    public void deleteBookById(Long id){

    }

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
