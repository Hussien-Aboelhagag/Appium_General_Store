package Pages.MobileBrowser.Utilts;

import Pages.MobileBrowser.API.POGO.UserDetails;
import com.github.javafaker.Faker;

public class UserUtils {
    public UserDetails generateUsers(){
        String firstName=new Faker().name().firstName();
        String lastName=new Faker().name().lastName();
        String email=new Faker().internet().emailAddress();
        String password=new Faker().internet().password();
        UserDetails userDetails=new UserDetails(email,firstName,lastName,password);
        return userDetails;
    }
}
