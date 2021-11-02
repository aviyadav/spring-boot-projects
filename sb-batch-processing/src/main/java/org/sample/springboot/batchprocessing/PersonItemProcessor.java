package org.sample.springboot.batchprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public Person process(final Person item) throws Exception {
        String firstName = item.getFirstName().toUpperCase();
        String lastName = item.getLastName().toUpperCase();

        final Person transformedPerson = new Person(firstName, lastName);
        log.info("Converting (" + item + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }
}
