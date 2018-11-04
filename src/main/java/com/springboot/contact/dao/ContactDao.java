package com.springboot.contact.dao;

import com.springboot.contact.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: chudelong
 * @Date: 2018/11/2 12:16
 */

@Repository
public class ContactDao {
    private JdbcTemplate jdbc;

    @Autowired
    public ContactDao(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    /**
     * 查询联系人
     *
     * @return
     */
    public List<Contact> findAll(){
        return jdbc.query(
                "SELECT id, firstName, lastName, phoneNumber, emailAddress" +
                        "from contacts order by lastName",
                new RowMapper<Contact>() {
                    @Override
                    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Contact contact = new Contact();
                        contact.setId(rs.getLong(1));
                        contact.setFirstName(rs.getString(2));
                        contact.setLastName(rs.getString(3));
                        contact.setPhoneNumber(rs.getString(4));
                        contact.setEmailAddress(rs.getString(5));
                        return contact;
                    }
                }
        );
    }

    /**
     * 添加联系人
     *
     * @param contact
     */
    public void save(Contact contact){
        jdbc.update(
                "INSERT into contacts (firstName, lastName, phoneNumber, emailAddress) VALUES (?, ? , ?, ?)",
                contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(), contact.getEmailAddress()
        );
    }

}
