package pl.srslycpp.myWeb.Entity;


import com.sun.corba.se.spi.ior.ObjectId;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    private ObjectId id;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String someUrl) {
        this.imageUrl = someUrl;
    }
}
