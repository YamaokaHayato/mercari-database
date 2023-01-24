package com.example.domain;

/**
 * original情報を表すドメイン.
 * 
 * @author yamaokahayato
 *
 */
public class Original2 {

	private Integer id;
	private String name;
	private Integer condition_id;
	private String category_name;;
	private String brand;;
	private Double price;
	private Integer shipping;
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCondition_id() {
		return condition_id;
	}

	public void setCondition_id(Integer condition_id) {
		this.condition_id = condition_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Original2 [id=" + id + ", name=" + name + ", condition_id=" + condition_id + ", category_name="
				+ category_name + ", brand=" + brand + ", price=" + price + ", shipping=" + shipping + ", description="
				+ description + "]";
	}

}
