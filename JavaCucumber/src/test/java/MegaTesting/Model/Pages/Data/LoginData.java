package MegaTesting.Model.Pages.Data;

public class LoginData {

        public String Username;
        public String Password;


        public LoginData WithUsername(String username)
        {
            this.Username = username;
            return this;
        }

        public LoginData WithPassword(String password)
        {
            this.Password = password;
            return this;
        }
}

