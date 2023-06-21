package sg.edu.nus.iss.paf_day22Workshop.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_day22Workshop.model.RSVP;

@Repository
public class RSVPRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String countSQL = "select count(*) from rsvp";
    private final String findAllSQL = "select * from rsvp";
    private final String findbyIdSQL = "select * from rsvp where id = ?";
    private final String insertSQL = "insert into rsvp (full_name, email, phone, confirmation_date, comments) values = (?, ?, ?, ?, ?)";
    private final String updateSQL = "update rsvp set email = ?, phone = ?, confirmation_date = ? where id = ?";

    public int count(){
        int result = jdbcTemplate.queryForObject(countSQL, Integer.class);
        return result;
    }

    public List<RSVP> findAll(){
        List<RSVP> rsvps = new ArrayList<>();
        rsvps = jdbcTemplate.queryForList(findAllSQL, RSVP.class);
        return rsvps;
    }

    public RSVP findById(int id){
        RSVP rsvp = jdbcTemplate.queryForObject(findbyIdSQL,
            BeanPropertyRowMapper.newInstance(RSVP.class), id);
        return rsvp;
    }

    public boolean saveRecord(RSVP rsvp){
        int result = 0;
        result = jdbcTemplate.update(insertSQL, 
            rsvp.getFullName(), rsvp.getEmail(), rsvp.getPhone(),
            rsvp.getConfirmationDate(), rsvp.getComments());
        return result > 0 ? true : false;
    }

    public boolean updateRecord(RSVP rsvp){
        int result = 0;
        result = jdbcTemplate.update(updateSQL,
            rsvp.getEmail(), rsvp.getPhone(), 
            rsvp.getConfirmationDate(), rsvp.getId());
        return result > 0 ? true : false;
    }

    // write a function for batch insert (slide 17)
}
