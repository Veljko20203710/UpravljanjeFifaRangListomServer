package domain;

import exceptions.IllegalDateException;
import exceptions.IllegalGoalsException;
import exceptions.IllegalMatchStateException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Match implements Serializable, DomainObject {

    private int id;
    private Date date;
    private Selection host;
    private Selection away;
    private int hostGoals;
    private int awayGoals;
    private MatchType matchType;
    private User user;

    private static final long serialVersionUID = 123212312L;

    public Match() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) throws IllegalDateException {
        if (date == null || date.after(new Date())) {
            throw new IllegalDateException();
        }
        this.date = date;
    }

    public Selection getHost() {
        return host;
    }

    public void setHost(Selection host) throws IllegalMatchStateException {
        if (host == null) {
            throw new IllegalMatchStateException();
        }
        this.host = host;
    }

    public Selection getAway() {
        return away;
    }

    public void setAway(Selection away) throws IllegalMatchStateException {
        if (away == null) {
            throw new IllegalMatchStateException();
        }
        this.away = away;
    }

    public int getHostGoals() {
        return hostGoals;
    }

    public void setHostGoals(int hostGoals) throws IllegalGoalsException {
        if (hostGoals < 0) {
            throw new IllegalGoalsException();
        }
        this.hostGoals = hostGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) throws IllegalGoalsException {
        if (awayGoals < 0) {
            throw new IllegalGoalsException();
        }
        this.awayGoals = awayGoals;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(MatchType matchType) throws IllegalMatchStateException {
        if (matchType == null) {
            throw new IllegalMatchStateException();
        }
        this.matchType = matchType;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

  

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Match)) {
            return false;
        }
        Match m = (Match) obj;
        return this.getDate().equals(m.getDate()) && this.getHost().equals(m.getHost())
                && this.getAway().equals(m.getAway()) && this.getHostGoals() == m.hostGoals
                && this.getAwayGoals() == m.getAwayGoals();
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(id);
        result = result * 31 + date.hashCode();
        result = result * 31 + host.hashCode();
        result = result * 31 + away.hashCode();
        result = result * 31 + Integer.hashCode(awayGoals);
        result = result * 31 + Integer.hashCode(hostGoals);
        return result;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTableName() {
        return "match_table";
    }

    @Override
    public String getAttributeNamesForInsert() {
        return "id,host,away,hostGoals,awayGoals,date,matchtype,userID";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return id + ", " + host.getId() + ", " + away.getId() + ", " + hostGoals + "," + awayGoals
                + ", '" + new java.sql.Date(date.getTime()) + "', '" + matchType.getId() + "', " + user.getId();
    }

    @Override
    public String getAtributeValuesForUpdate() {
        return "id=" + id + ", " + "host=" + host.getId() + ", " + "away=" + away.getId() + ", "
                + "hostGoals=" + hostGoals + ", " + "awayGoals=" + awayGoals + ", date='" + new java.sql.Date(date.getTime()) + "', matchType='" + matchType.getId() + "', userID=" + user.getId();
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
    public List<Match> RSToTable(ResultSet rs) throws SQLException {
        List<Match> matches = new ArrayList<>();
        try {
            while (rs.next()) {
                Match match = new Match();
                match.setId(rs.getInt(1));

                Selection matchHost = new Selection();
                matchHost.setId(rs.getInt(2));
                match.setHost(matchHost);

                Selection matchAway = new Selection();
                matchAway.setId(rs.getInt(3));
                match.setAway(matchAway);

                match.setHostGoals(rs.getInt(4));
                match.setAwayGoals(rs.getInt(5));
                match.setDate(rs.getDate(6));
                match.setMatchType(new MatchType(rs.getInt(7)));

                User matchUser = new User();
                matchUser.setId(rs.getInt(8));
                match.setUser(matchUser);

                matches.add(match);
            }
        } catch (Exception e) {
            throw new SQLException();
        }
        return matches;
    }

    @Override
    public DomainObject createObjectFromRS(ResultSet rs) throws Exception {
        try {
            Match match = new Match();
            match.setId(rs.getInt(1));

            Selection matchHost = new Selection();
            matchHost.setId(rs.getInt(2));
            match.setHost(matchHost);

            Selection matchAway = new Selection();
            matchAway.setId(rs.getInt(3));
            match.setAway(matchAway);

            match.setHostGoals(rs.getInt(4));
            match.setAwayGoals(rs.getInt(5));
            match.setDate(rs.getDate(6));
            match.setMatchType(new MatchType(rs.getInt(7)));

            User matchUser = new User();
            matchUser.setId(rs.getInt(8));
            match.setUser(matchUser);
            return match;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void setCondtions(HashMap<String, String> conditions) {
        this.conditions = conditions;
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", date=" + date + ", host=" + host + ", away=" + away + ", hostGoals=" + hostGoals + ", awayGoals=" + awayGoals + ", matchType=" + matchType + ", user=" + user + ", conditions=" + conditions + '}';
    }
}
