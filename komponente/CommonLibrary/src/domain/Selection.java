package domain;

import exceptions.IllegalPointsException;
import exceptions.IllegalRankingException;
import exceptions.IllegalSelectionException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Selection implements Serializable, DomainObject {

    private int id;
    private Confederation confederation;
    private String name;
    private int rang;
    private int points;
    private boolean active;
    private static final long serialVersionUID = 12029997L;
    private User user;

    private HashMap<String, String> conditions;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Selection(String name, Confederation confederation) throws IllegalSelectionException {
        if (name.isEmpty() || confederation == null) {
            throw new IllegalSelectionException();
        }
        this.name = name;
        this.confederation = confederation;
        this.rang = 0;
        this.points = 0;
    }

    public Selection() {
    }

    public Selection(String name) throws IllegalSelectionException {
        if (name.isEmpty()) {
            throw new IllegalSelectionException();
        }
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Confederation getConfederation() {
        return confederation;
    }

    public void setConfederation(Confederation confederation) throws IllegalSelectionException {
        if (confederation == null) {
            throw new IllegalSelectionException();
        }
        this.confederation = confederation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalSelectionException {
        if (name.isEmpty()) {
            throw new IllegalSelectionException();
        }
        this.name = name;
    }

    public int getRang() {
        return rang;
    }
    

    public void setRang(int rang) throws IllegalRankingException {
        if (rang < 0) {
            throw new IllegalRankingException();
        }
        this.rang = rang;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) throws IllegalPointsException {
        if (points < 0) {
            throw new IllegalPointsException();
        }
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Selection)) {
            return false;
        }
        Selection s = (Selection) o;
        return this.getId()==(s.getId());
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(id);
        result = 31 * result + name.hashCode();
        result = 31 * result + confederation.hashCode();
        result = 31 * result + Integer.hashCode(rang);
        result = 31 * result + Integer.hashCode(points);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
   

    @Override
    public String getTableName() {
        return "Selection";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "id,name,confederation,rang,points,active,userID";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return id + ",'" + name + "','" + confederation.getId() + "', " + rang + ", " + points + "," + active
                + "," + user.getId();
    }

    @Override
    public String getAtributeValuesForUpdate() {
        return "id=" + id + ", " + "name='" + name + "', " + "confederation='" + confederation.getId() + "', " + "rang=" + rang
                + ",points=" + points + ", " + "active=" + active + "," + "userID=" + user.getId();
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
            query.append(iterator.next());
            if (iterator.hasNext()) {
                query.append(" AND ");
            }
        }
        return query.toString();

    }

    @Override
    public List<DomainObject> RSToTable(ResultSet rs) throws SQLException {
        List<DomainObject> selections = new ArrayList<>();
        try {
            while (rs.next()) {
                Selection selection = new Selection();
                selection.setId(rs.getInt(1));
                selection.setName(rs.getString(2));
                selection.setConfederation(new Confederation(rs.getInt(3)));
                selection.setRang(rs.getInt(4));
                selection.setPoints(rs.getInt(5));
                selection.setActive(rs.getBoolean(6));

                User selectionUser = new User();
                selectionUser.setId(rs.getInt(7));
                selection.setUser(selectionUser);

                selections.add(selection);
            }
        } catch (Exception e) {
            throw new SQLException();
        }
        return selections;
    }

    @Override
    public DomainObject createObjectFromRS(ResultSet rs) throws Exception {
        try {
            Selection selection = new Selection();
            selection.setId(rs.getInt(1));
            selection.setName(rs.getString(2));
            selection.setConfederation(new Confederation(rs.getInt(3)));
            selection.setRang(rs.getInt(4));
            selection.setRang(rs.getInt(5));
            selection.setActive(rs.getBoolean(6));

            return selection;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void setCondtions(HashMap<String, String> conditions) {
        this.conditions = conditions;
    }

}
