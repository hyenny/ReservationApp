package cookandroid.com.reservationapp.model;


import java.io.Serializable;

public class MenuItem implements Serializable {

    private String img;
    private String name;
    private String price;
    private String type;

    public MenuItem(String img, String name, String price, String type) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.type = type;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }




}
