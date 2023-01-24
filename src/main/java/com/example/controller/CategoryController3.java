package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Category;
import com.example.domain.Items;
import com.example.domain.Original2;
import com.example.repository.CategoryRepository;
import com.example.repository.ItemsRepository;
import com.example.repository.OriginalRepository;

@Controller
@RequestMapping("/category3")
public class CategoryController3 {

	@Autowired
	private OriginalRepository originalRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ItemsRepository itemsRepository;

	// 大カテゴリーを格納するmap
	Map<String, Integer> largeCategoryMap = new HashMap<>();
	// 中カテゴリーを格納するmap
	Map<String, Integer> mediumCategoryMap = new HashMap<>();
	// 小カテゴリーを格納するmap
	Map<String, Integer> smallCategoryMap = new HashMap<>();

	@GetMapping("")
	public String insertCategory() {
		// オリジナルテーブルから全件取得
		List<Original2> originalList = originalRepository.findAll();
		List<String> categorylList = new ArrayList<>();
		Integer smallCategoryId = null;
		for (Original2 originalInfo : originalList) {
			// category_nameのみ取得
			categorylList.add(originalInfo.getCategory_name());
		}

		// Stream APIで重複チェックをして重複がないcatagory_nameをListに入れる
		List<String> distinctCategoryList = categorylList.stream().distinct().collect(Collectors.toList());

		// 空白の一行を削除
		distinctCategoryList.removeAll(Arrays.asList("", null));
		
		String categoryName = null;
		Category category = new Category();
		Items items = new Items();
		// 重複削除のcategory_nameから1つずつ配列に格納
		for (int i = 0; i < distinctCategoryList.size(); i++) {
			categoryName = distinctCategoryList.get(i);
			// catgoryNameを分割
			String[] categoryInfo = categoryName.split("/", 0);
			// 大カテゴリーのみを代入
			String largeCategoryName = categoryInfo[0];
			// 中カテゴリーを代入
			String mediumCategoryName = categoryInfo[0] + "/" + categoryInfo[1];
			// 小カテゴリーを代入
			String smallCategoryName = categoryInfo[0] + "/" + categoryInfo[1] + "/" + categoryInfo[2];

			// largeCategoryNameがMapに存在しなかったらインサートを実行
			if (largeCategoryMap.get(largeCategoryName) == null) {
				category.setParent(null);
				category.setName(categoryInfo[0]);
				category.setNameAll(null);
				// 大カテゴリーのインサートを実行
				categoryRepository.insert(category);

				// 大カテゴリーのidを取得
				Integer id = categoryRepository.findByLargeCategoryId(categoryInfo[0]);
				// Mapに
				largeCategoryMap.put(largeCategoryName, id);
			}

			// mediumCategoryNameがMapに存在しなかったらインサートを実行
			if (mediumCategoryMap.get(mediumCategoryName) == null) {
				// 大カテゴリーのidをset
				category.setParent(largeCategoryMap.get(categoryInfo[0]));
				category.setName(categoryInfo[1]);
				category.setNameAll(null);
				// 中カテゴリーのインサート実行
				categoryRepository.insert(category);

				Integer parentId = categoryRepository.findByMediumCategoryId(categoryInfo[1],
						largeCategoryMap.get(categoryInfo[0]));
				// HashMapにカテゴリー名、中カテゴリーのidを追加
				mediumCategoryMap.put(mediumCategoryName, parentId);
			}

			// smallCategoryNameがMapに存在しなかったらインサートを実行
			if (smallCategoryMap.get(smallCategoryName) == null) {
				Integer count = 1;
				// 小カテゴリーに紐づいた中カテゴリーのID
				category.setParent(mediumCategoryMap.get(mediumCategoryName));
				category.setName(categoryInfo[2]);
				category.setNameAll(smallCategoryName);
				// 小カテゴリーのインサート実行
				categoryRepository.insert(category);
				smallCategoryMap.put(smallCategoryName, count);

			}
		}
//			// 小カテゴリーのidを取得
//			smallCategoryId = categoryRepository.findBySmallCategoryId(categoryInfo[2],
//					mediumCategoryMap.get(mediumCategoryName));

			

			for (Original2 original2 : originalList) {
				Category abc = categoryRepository.findBySmallItemCategoryId(original2.getCategory_name());
				// 空白のcategoryName
				if(original2.getCategory_name().equals("")) {
					continue;
				}
				
				// IDがnullのデータをtry〜catchで例外処理実施
				try {
					items.setCategory(abc.getId());
				} catch(Exception e) {
					items.setCategory(null);
				}
				//itemsテーブルに各データをセットして追加
				items.setName(original2.getName());
				items.setCondition(original2.getCondition_id());
				items.setBrand(original2.getBrand());
				items.setPrice(original2.getPrice());
				items.setShipping(original2.getShipping());
				items.setDescription(original2.getDescription());
				itemsRepository.insert(items);

			}
		
		return "index";
	}

}
