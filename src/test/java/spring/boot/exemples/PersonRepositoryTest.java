package spring.boot.exemples;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {

	@Autowired
	private PersonRepository lPersonRepository;

	private String lUsername = "username";
	private String lPassword = "password";

	private Person lPerson;

	@Before
	public void setup() {

		lPerson = new Person(lUsername, lPassword);

	}

	@Test
	public void testPersonSave() {

		System.out.println("<<<------ Before save ------>>>");
		System.out.println(lPerson.toString());

		Person savedPerson = lPersonRepository.save(lPerson);

		System.out.println("<<<------ After save ------>>>");
		System.out.println(savedPerson.toString());

		Long id = savedPerson.getId();

		Person loadedPerson = lPersonRepository.findOne(id);

		System.out.println("<<<------ After load ------>>>");
		System.out.println(loadedPerson.toString());

	}

}
