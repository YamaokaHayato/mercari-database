package com.example.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * original情報を表すドメイン.
 * 
 * @author yamaokahayato
 *
 */
// tsvファイルの各項目を読み込む順序を設定する
@JsonPropertyOrder({ "train_id", "name", "item_condition_id", "category_name", "brand_name", "price", "shipping",
		"item_description" })
public class Original {

	private Integer train_id;
	private String name;
	private Integer item_condition_id;
	private String category_name;;
	private String brand_name;;
	private Double price;
	private Integer shipping;
	private String item_description;

	public Original(Integer train_id, String name, Integer item_condition_id, String category_name, String brand_name,
			Double price, Integer shipping, String item_description) {
		this.train_id = train_id;
		this.name = name;
		this.item_condition_id = item_condition_id;
		this.category_name = category_name;
		this.brand_name = brand_name;
		this.price = price;
		this.shipping = shipping;
		this.item_description = item_description;
	}
	
	// JacksonライブラリはCSVやTSVをJavaオブジェクトに変換するときにはデフォルトコンストラクタを定義する
	public Original() {

	}

	public Integer getTrain_id() {
		return train_id;
	}

	public void setTrain_id(Integer train_id) {
		this.train_id = train_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getItem_condition_id() {
		return item_condition_id;
	}

	public void setItem_condition_id(Integer item_condition_id) {
		this.item_condition_id = item_condition_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getShipping() {
		return shipping;
	}

	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	@Override
	public String toString() {
		return "Original [train_id=" + train_id + ", name=" + name + ", item_condition_id=" + item_condition_id
				+ ", category_name=" + category_name + ", brand_name=" + brand_name + ", price=" + price + ", shipping="
				+ shipping + ", item_description=" + item_description + "]";
	}

}
