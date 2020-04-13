package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.BookUser;
import utils.DBUtil;

public class BookUserValidator {
    public static List<String> validate(BookUser u, Boolean code_duplicate_check_flag, Boolean password_check_flag) {
        List<String> errors = new ArrayList<String>();

        String code_error = _validateCode(u.getCode(), code_duplicate_check_flag);
        if(!code_error.equals("")) {
            errors.add(code_error);
        }

        String name_error = _validateName(u.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String password_error = _validatePassword(u.getPassword(), password_check_flag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;
    }

    private static String _validateCode(String code, Boolean code_duplicate_check_flag) {
        if(code == null || code.equals("")) {
            return "ユーザーコードを入力してください。";
        }

        if(code_duplicate_check_flag) {
            EntityManager em = DBUtil.createEntityManager();
            long users_count = (long)em.createNamedQuery("checkregisteredCode", Long.class)
                                        .setParameter("code", code)
                                        .getSingleResult();
            em.close();
            if(users_count > 0) {
                return "入力されたユーザーコードはすでに使用されています。";
            }
        }

        return "";
    }

    private static String _validateName(String name) {
        if(name == null || name.equals("")) {
            return "ユーザー名を入力してください。";
        }

        return "";
    }

    private static String _validatePassword(String password, Boolean password_check_flag) {
        if(password_check_flag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }

        return "";
    }
}
