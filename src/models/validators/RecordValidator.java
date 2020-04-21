package models.validators;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Record;

public class RecordValidator {
    public static List<String> validate(Record r) {
        List<String> errors = new ArrayList<String>();

        String date_error = _validateDate(r.getDate());
        if(!date_error.equals("")) {
            errors.add(date_error);
        }

        String content_error = _validateContent(r.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        String amount_error = _validateAmount(r.getAmount());
        if(!amount_error.equals("")) {
            errors.add(amount_error);
        }

        return errors;
    }

    private static String _validateDate(Date date) {
        if(date == null || date.equals("")) {
            return "日付を入力してください。";
        }
        return "";
    }

    private static String _validateContent(String content) {
        if(content == null || content.equals("")) {
            return "支出入区分を入力してください。";
        }
        return "";
    }

    private static String _validateAmount(Integer amount) {
        if(amount == null) {
            return "金額を入力してください。";
        }
        return "";
    }
}
