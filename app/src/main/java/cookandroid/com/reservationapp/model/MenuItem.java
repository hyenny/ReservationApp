package cookandroid.com.reservationapp.model;


import java.io.Serializable;

public class MenuItem implements Serializable {

    String img;
    String name;
    String price;

    public MenuItem(String img, String name, String price) {
        this.img = img;
        this.name = name;
        this.price = price;
    }

    public String getImg() { return img; }

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
}
