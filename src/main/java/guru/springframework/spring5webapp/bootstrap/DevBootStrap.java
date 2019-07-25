package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository=  publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    initData();
    }

    private void initData(){
        Author eric= new Author("Eric", "Evans");
        Publisher p1= new Publisher("Harper Collins","UK");
        Book ddd = new Book("Domain Driven Design","1234", p1);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        authorRepository.save(eric);
        publisherRepository.save(p1);
        bookRepository.save(ddd);



        Author rod= new Author("Rod", "Jhonson");
        Publisher p2=new Publisher("Wrox","America");
        Book noEJB = new Book("J2EE Development without EJB","2344", p2);
        rod.getBooks().add(noEJB);
        authorRepository.save(rod);
        publisherRepository.save(p2);
        bookRepository.save(noEJB);



    }
}
