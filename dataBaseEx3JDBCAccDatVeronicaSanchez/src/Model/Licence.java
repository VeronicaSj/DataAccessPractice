package Model;

import java.sql.Date;

public class Licence {
    private int id;
    private String lType;
    private Date expedition;
    private Date expiration;
    public int getId() {
        return id;
    }
    public String getlType() {
        return lType;
    }
    public Date getExpedition() {
        return expedition;
    }
    public Date getExpiration() {
        return expiration;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setlType(String lType) {
        this.lType = lType;
    }
    public void setExpedition(Date expedition) {
        this.expedition = expedition;
    }
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
    public Licence(int id, String lType, Date expedition, Date expiration) {
        this.id = id;
        this.lType = lType;
        this.expedition = expedition;
        this.expiration = expiration;
    }
    public Licence() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Licence{");
        sb.append("id=").append(id);
        sb.append(", lType=").append(lType);
        sb.append(", expedition=").append(expedition);
        sb.append(", expiration=").append(expiration);
        sb.append('}');
        return sb.toString();
    }

    

}
