/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import exceptions.IllegalUserState;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Veljko
 */
public class User implements Serializable, DomainObject {

    private int id;
    private String username;
    private String password;
    private boolean active;
    private boolean administator;
    private static final long serialVersionUID = 11223381L;
    private HashMap<String, String> conditions;

    public User() {
    }

    public User(int id, String username) throws IllegalUserState {
        if (id < 0 || username.isEmpty()) {
            throw new IllegalUserState();
        }
        this.id = id;
        this.username = username;
    }

    public boolean isAdministator() {
        return administator;
    }

    public void setAdministator(boolean administator) {
        this.administator = administator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws IllegalUserState {
        if (username.isEmpty()) {
            throw new IllegalUserState();
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IllegalUserState {
        if (password.isEmpty()) {
            throw new IllegalUserState();
        }
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", active=" + active + '}';
    }

    @Override
    public String getTableName() {
        return "user_table";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "id,username,password,active,administrator";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return id + ", '" + username + "','" + password + "'," + active + ", " + administator + "";
    }

    @Override
    public String getWhereConditionById() {
        return "Where id=" + id;
    }

    @Override
    public String getWhereConditionByAttribute() {
        StringBuilder query = new StringBuilder("WHERE ");
        Set<Map.Entry<String, String>> attributes = conditions.entrySet();

        Iterator iterator = attributes.iterator();

        while (iterator.hasNext()) {
            String condition = iterator.next().toString();
            String[] split = condition.split("=");
            query.append(split[0]).append("=");
            query.append("'").append(split[1]).append("'");
            if (iterator.hasNext()) {
                query.append(" AND ");
            }
        }
        return query.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.username);
        hash = 41 * hash + Objects.hashCode(this.password);
        hash = 41 * hash + (this.active ? 1 : 0);
        hash = 41 * hash + (this.administator ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.active != other.active) {
            return false;
        }
        if (this.administator != other.administator) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String getAtributeValuesForUpdate() {
        return "id=" + id + ", " + "username='" + username + "', " + "password='" + password + "', " + "active=" + active + ", " + "administrator=" + administator;
    }

    @Override
    public List<User> RSToTable(ResultSet rs)throws SQLException {
        List<User> users = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User(rs.getInt(1), rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setActive(rs.getBoolean(4));
                user.setAdministator(rs.getBoolean(5));
                users.add(user);
            }
        } catch (Exception e) {
            throw new SQLException();
        }
        return users;
    }

    @Override
    public DomainObject createObjectFromRS(ResultSet rs) throws Exception {
        try {
            User user = new User(rs.getInt(1), rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setActive(rs.getBoolean(4));
            user.setAdministator(rs.getBoolean(5));
            return user;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void setCondtions(HashMap<String, String> conditions) {
        this.conditions = conditions;
    }

}
