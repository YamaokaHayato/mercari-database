package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Category;

/**
 * categoryテーブルを操作するリポジトリ.
 * 
 * @author yamaokahayato
 *
 */
@Repository
public class CategoryRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Category> CATEGORY_ROW_MAPPER = new BeanPropertyRowMapper<>(Category.class);

	/**
	 * category情報をインサートする.
	 * 
	 * @param category
	 */
	public void insert(Category category) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(category);
		String sql = "INSERT INTO category (parent, name, name_all) values(:parent, :name, :nameAll);";
		template.update(sql, param);
	}

	/**
	 * 大カテゴリーのIDを取得.
	 * 
	 * @param largeCategoryName 
	 * @return 
	 */
	public Integer findByLargeCategoryId(String largeCategoryName) {
		String sql = "SELECT id FROM category WHERE name =:name and parent is null and name_all is null";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", largeCategoryName);
		List<Category> category = template.query(sql, param, CATEGORY_ROW_MAPPER);
		return category.get(0).getId();
	}

	/**
	 * 中カテゴリーのIDを取得.
	 * 
	 * @param mediumCategory
	 * @param largeCategoryId
	 * @return
	 */
	public Integer findByMediumCategoryId(String mediumCategory, Integer largeCategoryId) {
		String sql = "SELECT id, parent, name, name_all FROM category WHERE name =:name AND parent =:parent AND name_all IS NULL";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", mediumCategory).addValue("parent",
				largeCategoryId);
		List<Category> category = template.query(sql, param, CATEGORY_ROW_MAPPER);
		return category.get(0).getId();
	}
	
	
	/**
	 * 小カテゴリーのIDを取得.
	 * 
	 * @param smallCategory
	 * @param mediumCategoryId
	 * @return
	 */
	public Integer findBySmallCategoryId(String smallCategory, Integer mediumCategoryId) {
		String sql = "SELECT id, parent, name, name_all FROM category WHERE name = :name AND parent = :parent;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", smallCategory).addValue("parent",
				mediumCategoryId);
		List<Category> category = template.query(sql, param, CATEGORY_ROW_MAPPER);
		return category.get(0).getId();
	}
	
	/**
	 * 小カテゴリーのIDをカテゴリー名から取得.
	 * 
	 * @param categoryName
	 * @return
	 */
	public Category findBySmallItemCategoryId(String categoryName) {
		String sql = "select id from category where name_all = :categoryName;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("categoryName", categoryName);
		try {
			Category category = template.queryForObject(sql,param, CATEGORY_ROW_MAPPER);
			return category;
		} catch(Exception e) {
			return null;
		}
		
	}
	


}
