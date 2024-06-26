package org.data.preparation.dao;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.springframework.stereotype.Component;

@Component
public class InsertScriptGenerator {

	public String generateInsertScript(String tableName, List<String> columns, List<Map<String, Object>> data) {
		StringJoiner columnNames = new StringJoiner(",");
		columns.forEach(columnNames::add);

		StringJoiner insertScripts = new StringJoiner(";\n");

		for (Map<String, Object> row : data) {
			StringJoiner values = new StringJoiner(",");

			columns.forEach(column -> {
				Object value = row.get(column);
				if (value instanceof String) {
					values.add("'" + value + "'");
				} else {
					values.add(String.valueOf(value));
				}
			});

			String insertScript = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columnNames, values);
			insertScripts.add(insertScript);
		}
		return insertScripts.toString();
	}
}
