package com.dino.sshop._domain.identity.factory;

import com.dino.sshop._domain.common.exception.AppException;
import com.dino.sshop._domain.common.exception.ErrorCode;
import com.dino.sshop._domain.common.util.AppUtils;
import com.dino.sshop._domain.identity.model.entity.Account;
import com.dino.sshop._domain.identity.model.entity.Shop;
import com.dino.sshop._domain.identity.model.entity.Token;
import com.dino.sshop._domain.shopping.factory.CartFactory;
import com.dino.sshop._domain.shopping.model.entity.Cart;

import java.util.HashSet;
import java.util.Set;

public class AccountFactory {
    public static Account createAccount(Account account) {
        //status
        account.setStatus(Account.StatusType.LIVE.toString());
        //username
        if (AppUtils.isEmpty(account.getUsername())) {
            account.setUsername("user" + System.currentTimeMillis());
        }
        //password
        if (!account.getPassword().startsWith("$2a$")) //$2a$ present for the bcrypt algorithm
            throw new AppException(ErrorCode.ACCOUNT__NOT_HASH_PASSWORD);
        //dob
        if (AppUtils.isEmpty(account.getDob()))
            account.setStatus(Account.StatusType.LACK_INFO.toString());
        //role
        Set<String> roles = account.getRoles();
        if (AppUtils.isEmpty(roles))
            throw new AppException(ErrorCode.ACCOUNT__LACK_INFO);

        Set<Account.RoleType> roleTypes = new HashSet<>();
        roles.stream().parallel().forEach(role -> {
            try {
                Account.RoleType roleType = Account.RoleType.valueOf(role);
                roleTypes.add(roleType);
            } catch (IllegalArgumentException e) {
                throw new AppException(ErrorCode.SYSTEM__KEY_UNSUPPORTED);
            }
        });
        //token
        account.setToken(createToken(Token.builder().build(), account));
        //buyer
        if (roleTypes.contains(Account.RoleType.BUYER))
            account.setCart(CartFactory.createCart(Cart.builder().build(), account));
        //seller
        if (roleTypes.contains(Account.RoleType.SELLER))
            account.setShop(ShopFactory.createShop(Shop.builder().build(), account));
        //response
        return account;
    }

    public static Account updateAccount(Account account) {
        //status
        account.setStatus(Account.StatusType.LIVE.toString());
        //username
        if (AppUtils.isEmpty(account.getUsername())) {
            account.setUsername("user" + System.currentTimeMillis());
        }
        //password
        if (!account.getPassword().startsWith("$2a$")) //$2a$ present for the bcrypt algorithm
            throw new AppException(ErrorCode.ACCOUNT__NOT_HASH_PASSWORD);
        //dob *change
        if (AppUtils.isEmpty(account.getDob()))
            throw new AppException(ErrorCode.ACCOUNT__LACK_INFO);
        //role *change
        Set<String> roles = account.getRoles();
        if (AppUtils.isEmpty(roles))
            throw new AppException(ErrorCode.ACCOUNT__LACK_INFO);
        roles.stream().parallel().forEach(role -> {
            try {
                Account.RoleType roleType = Account.RoleType.valueOf(role);
            } catch (IllegalArgumentException e) {
                throw new AppException(ErrorCode.SYSTEM__KEY_UNSUPPORTED);
            }
        });
        //response
        return account;
    }

    public static Account responseAccount(Account account) {
        account.setPassword(null);
        account.setPhone(null);
        account.setEmail(null);
        return account;
    }

    private static Token createToken(Token token, Account account) {
        token.setAccount(account);
        return token;
    }
}
