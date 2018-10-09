package com.maryanto.dimas.example.service;

import com.maryanto.dimas.example.entity.DataType;
import com.maryanto.dimas.example.entity.TableRow;
import com.maryanto.dimas.example.entity.TransactionRow;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TableService {

    /**
     * date: value if format date not valid steel work but the result not persist
     *
     * @param params
     * @return
     * @throws ParseException
     * @throws NumberFormatException
     */
    public List<TransactionRow> jsonUnwrapper(List<Map<String, Object>> params) throws
            ParseException, NumberFormatException {
        List<TransactionRow> json = new ArrayList<>();
        for (Map<String, Object> param : params) {
            String column = param.get("columnName").toString();
            DataType type = DataType.valueOf(param.get("dataType").toString());
            TransactionRow row = new TransactionRow();
            row.setColumnName(column);
            row.setDataType(type);
            boolean value = StringUtils.isNotEmpty(param.get("value").toString());
            if (value) {
                switch (row.getDataType()) {
                    case INT:
                        row.setIntValue(Integer.valueOf(param.get("value").toString()));
                        break;
                    case DATE:
                        java.util.Date dateUtil = DateUtils.parseDate(
                                param.get("value").toString(),
                                "yyyy-MM-dd",
                                "dd/MM/yyyy"
                        );
                        row.setDateValue(new Date(dateUtil.getTime()));
                        break;
                    case DATE_TIME:
                        java.util.Date dateTimeUtil = DateUtils.parseDate(param.get("value").toString(),
                                "yyyy-MM-dd HH:mm:ss",
                                "yyyy-MM-dd'T'HH:mm:ss");
                        row.setDatetimeValue(new Timestamp(dateTimeUtil.getTime()));
                        break;
                    case TEXT:
                        row.setTextValue(param.get("value").toString());
                        break;
                    case BOOLEAN:
                        row.setBooleanValue(Boolean.valueOf(param.get("value").toString()));
                        break;
                    case MONEY:
                        row.setMoneyValue(new BigDecimal(param.get("value").toString()));
                        break;
                    default:
                        row.setStringValue(param.get("value").toString());
                }
            }
            json.add(row);
        }
        return json;
    }

    public List<Map<String, Object>> jsonWrapper(List<TransactionRow> rows) {
        List<Map<String, Object>> json = new ArrayList<>();
        for (TransactionRow row : rows) {
            Map<String, Object> obj = new HashMap<>();
            obj.put("id", row.getId());
            obj.put("dataType", row.getDataType());
            obj.put("columnName", row.getColumnName());
            switch (row.getDataType()) {
                case INT:
                    obj.put("value", row.getIntValue());
                    break;
                case DATE:
                    obj.put("value", row.getDateValue().getTime());
                    break;
                case DATE_TIME:
                    obj.put("value", row.getDatetimeValue().getTime());
                    break;
                case TEXT:
                    obj.put("value", row.getTextValue());
                    break;
                case BOOLEAN:
                    obj.put("value", row.isBooleanValue());
                    break;
                case MONEY:
                    obj.put("value", row.getMoneyValue());
                    break;
                default:
                    obj.put("value", row.getStringValue());
            }
            json.add(obj);
        }
        return json;
    }

    public List<Map<String, Object>> rowJsonWrapper(List<TableRow> rows) {
        List<Map<String, Object>> json = new ArrayList<>();
        for (TableRow row : rows) {
            Map<String, Object> obj = new HashMap<>();
            obj.put("id", "");
            obj.put("columnName", row.getColumnName());
            obj.put("dataType", row.getDataType());
            obj.put("labelName", row.getLabelName());
            obj.put("value", "");
            json.add(obj);
        }
        return json;
    }
}
