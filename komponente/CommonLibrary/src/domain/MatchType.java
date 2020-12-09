package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MatchType implements Serializable, DomainObject {

    private int id;
    private String name;
    private double strenght;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStrenght() {
        return strenght;
    }

    public void setStrenght(double strenght) {
        this.strenght = strenght;
    }

    public MatchType() {
    }

    public MatchType(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getTableName() {
        return "MatchType";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "id,name,strenght";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return id + ", " + name + ", " + strenght;
    }

    @Override
    public String getAtributeValuesForUpdate() {
        return "id=" + id + ", " + "name=" + name + ", " + "strenght=" + strenght;
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
    public List<MatchType> RSToTable(ResultSet rs) throws SQLException {
        List<MatchType> matchTypes = new ArrayList<>();
        try {
            while (rs.next()) {
                MatchType matchType = new MatchType();
                matchType.setId(rs.getInt(1));
                matchType.setName(rs.getString(2));
                matchType.setStrenght(rs.getDouble(3));

                matchTypes.add(matchType);
            }
        } catch (Exception e) {
            throw e;
        }
        return matchTypes;
    }

    @Override
    public DomainObject createObjectFromRS(ResultSet rs) throws Exception {
        try {
            MatchType matchType = new MatchType();
            matchType.setId(rs.getInt(1));
            matchType.setName(rs.getString(2));
            matchType.setStrenght(rs.getDouble(3));
            return matchType;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private HashMap<String, String> conditions;

    @Override
    public void setCondtions(HashMap<String, String> conditions) {
        this.conditions = conditions;
    }

}
