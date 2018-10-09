package com.maryanto.dimas.example.service;

import com.maryanto.dimas.example.entity.TableRow;
import com.maryanto.dimas.example.entity.TransactionRow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TableService {

    public List<Map<String, Object>> jsonWrapperWithValue(List<TransactionRow> rows) {
        List<Map<String, Object>> json = new ArrayList<>();
        for (TransactionRow row : rows) {
            Map<String, Object> obj = new HashMap<>();
            obj.put("dataType", row.getDataType());
            obj.put("columnName", row.getColumnName());
            switch (row.getDataType()) {
                case INT:
                    obj.put("value", row.getIntValue());
                    break;
                case DATE:
                    obj.put("value", row.getDateValue());
                    break;
                case DATE_TIME:
                    obj.put("value", row.getDatetimeValue());
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

    public List<Map<String, Object>> jsonWrapper(List<TableRow> rows) {
        List<Map<String, Object>> json = new ArrayList<>();
        for (TableRow row : rows) {
            Map<String, Object> obj = new HashMap<>();
            obj.put("columnName", row.getColumnName());
            obj.put("dataType", row.getDataType());
            obj.put("labelName", row.getLabelName());
            json.add(obj);
        }
        return json;
    }
}
