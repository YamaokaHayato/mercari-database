package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Items;

/**
 * itemsテーブルを操作するリポジトリ.
 * 
 * @author yamaokahayato
 *
 */
@Repository
public class ItemsRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * item情報を追加する.
	 * 
	 * @param items
	 */
	public void insert(Items items) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(items);
		String sql = "Insert into items(name, condition, category, brand, price, shipping, description) values(:name, :condition, :category, :brand, :price, :shipping, :description);";
		template.update(sql, param);

	}
}
