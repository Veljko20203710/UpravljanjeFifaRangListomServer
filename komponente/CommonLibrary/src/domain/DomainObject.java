/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Veljko
 */
public interface DomainObject {

      
    public String getTableName();

    public String getAttributeNamesForInsert();

    public String getAttributeValuesForInsert();

    public String getAtributeValuesForUpdate();

    public String getWhereConditionById();

    public String getWhereConditionByAttribute();
    
    public void setCondtions(HashMap<String,String> hashMap);

    public List<? extends DomainObject> RSToTable(ResultSet rs) throws SQLException;
    
    public DomainObject createObjectFromRS(ResultSet rs) throws Exception;

}
