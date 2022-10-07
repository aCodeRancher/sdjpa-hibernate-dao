package guru.springframework.jdbc;

import guru.springframework.jdbc.dao.AuthorDao;
import guru.springframework.jdbc.dao.AuthorDaoImpl;
import guru.springframework.jdbc.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@Import({AuthorDaoImpl.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SdjpaJdbcApplicationTests {

	@Autowired
	AuthorDao authorDao;

	@Test
	void testFindAllAuthors() {
		List<Author> authors = authorDao.findAll();

		assertThat(authors).isNotNull();
		assertThat(authors.size()).isEqualTo(3);
	}

	@Test
	void testSaveAuthor() {
		Author author = new Author();
		author.setFirstName("Peter");
		author.setLastName("Schor");
		Author saved = authorDao.saveNewAuthor(author);

		assertThat(saved).isNotNull();
		assertThat(saved.getId()).isNotNull();
	}

}
