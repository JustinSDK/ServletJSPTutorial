package cc.openhome.controller;

import javax.validation.constraints.Pattern;

public class RegisterForm {
    @Pattern(
        regexp = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$", 
        message = "未填寫郵件或格式不正確"
    )
    private String email;
    
    @Pattern(regexp = "^\\w{1,16}$", message = "未填寫使用者名稱或格式不正確")
    private String username;
    
    @Pattern(regexp = "^\\w{8,16}$", message = "請確認密碼符合格式")
    private String password;
    
    private String password2;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
