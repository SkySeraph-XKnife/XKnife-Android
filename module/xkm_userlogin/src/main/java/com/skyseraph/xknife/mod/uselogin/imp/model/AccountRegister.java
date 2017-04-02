package com.skyseraph.xknife.mod.uselogin.imp.model;

/**
 * Created by SkySeraph on 2016/10/23.
 */
public class AccountRegister {

    private String phone;
    private String password;
    private String nickname;
    private String verificode;

    /**
     * Instantiates a new Account register.
     *
     * @param phone      the phone
     * @param password   the password
     * @param nickname   the nickname
     * @param verificode the verificode
     */
    public AccountRegister(String phone, String password, String nickname, String verificode) {
        this.phone = phone;
        this.password = password;
        this.nickname = nickname;
        this.verificode = verificode;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets nickname.
     *
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Gets verificode.
     *
     * @return the verificode
     */
    public String getVerificode() {
        return verificode;
    }
}
