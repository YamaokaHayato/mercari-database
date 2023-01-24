package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Original;
import com.example.domain.Original2;

/**
 * originalテーブルを操作するリポジトリ.
 * 
 * @author yamaokahayato
 *
 */
@Repository
public class OriginalRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Original2> ORIGINAL_ROW_MAPPER = new BeanPropertyRowMapper<>(Original2.class);
	
	/**
	 * original情報を追加する.
	 * 
	 * @param original
	 */
	public void insert(Original original) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(original);
		String sql = "INSERT INTO original(id, name, condition_id, category_name, brand, price, shipping, description) "
				+ "VALUES(:train_id, :name, :item_condition_id, :category_name, :brand_name, :price, :shipping, :item_description)";
		template.update(sql, param);
		}
	
	/**
	 * original情報を全件取得.
	 * 
	 * @return
	 */
	public List<Original2> findAll() {
		String sql = "SELECT id, name, condition_id, category_name, brand, price, shipping, description FROM original";
		List<Original2> categoryNameList = template.query(sql, ORIGINAL_ROW_MAPPER);
		return categoryNameList;
	}

}
