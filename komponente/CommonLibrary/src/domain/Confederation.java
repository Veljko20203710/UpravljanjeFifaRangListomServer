package domain;

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

public class Confederation implements Serializable, DomainObject{

    private int id;
    private String name;
    private double strenght;

    public Confederation() {
    }

    public Confederation(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.strenght) ^ (Double.doubleToLongBits(this.strenght) >>> 32));
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
        final Confederation other = (Confederation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.strenght) != Double.doubleToLongBits(other.strenght)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "Confederation";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "id,name,strenght";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return id + ",'" + name + "','" + strenght;
    }

    @Override
    public String getAtributeValuesForUpdate() {
        return "id=" + id + ", " + "name='" + name + "', " + "strenght='" + strenght;
    }

    @Override
    public String getWhereConditionById() {
        return "Where id=" + id;
    }

    private HashMap<String, String> conditions;

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
        List<DomainObject> confederations = new ArrayList<>();
        try {
            while (rs.next()) {
                Confederation confederation = new Confederation();
                confederation.setId(rs.getInt(1));
                confederation.setName(rs.getString(2));
                confederation.setStrenght(rs.getDouble(3));
                confederations.add(confederation);
            }
        } catch (Exception e) {
            throw e;
        }
        return confederations;
    }

    @Override
    public DomainObject createObjectFromRS(ResultSet rs) throws Exception {
        try {
            Confederation confederation = new Confederation();
            confederation.setId(rs.getInt(1));
            confederation.setName(rs.getString(2));
            confederation.setStrenght(rs.getDouble(3));
            return confederation;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void setCondtions(HashMap<String, String> conditions) {
        this.conditions = conditions;
    }

}
