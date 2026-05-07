package it.zancuo.katabirthday.repository.impl;

import it.zancuo.katabirthday.model.Person;
import it.zancuo.katabirthday.repository.BirthdayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Profile("jdbc")
@RequiredArgsConstructor
public class JdbcBirthdayRepository implements BirthdayRepository {

    private final JdbcTemplate jdbcTemplate;

	@Override
	public List<Person> findAll() {
		String sql = """
						SELECT id, surname, name, birth_date, email
						FROM persons
					 """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Person(
                        rs.getLong("id"),
                        rs.getString("surname"),
                        rs.getString("name"),
                        rs.getDate("birth_date").toLocalDate(),
                        rs.getString("email")));
    }
	
}