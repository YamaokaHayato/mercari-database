package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Original;
import com.example.service.OriginalService;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * tsvファイルを取り込みoriginalテーブルへ挿入するコントローラー.
 * 
 * @author yamaokahayato
 *
 */
@Controller
@RequestMapping("/")
public class OriginalController {
	
	@Autowired
	private OriginalService originalService;
	
	/**
	 *  tsvファイルを取り込みoriginalテーブルへ挿入する.
	 * 
	 * @return index.html
	 * @throws IOException
	 */
	@GetMapping("")
	public String readAll() throws IOException {
		
		CsvMapper mapper = new CsvMapper();
		// ヘッダあり、タブ区切り
		CsvSchema schema = mapper.schemaFor(Original.class).withHeader().withColumnSeparator('\t');

		Path path = Paths.get("/Users/yamaokahayato/Documents/train.tsv"); //tsvファイルへのパス
		try (BufferedReader br = Files.newBufferedReader(path)) {

			MappingIterator<Original> it = mapper.readerFor(Original.class).with(schema).readValues(br);

			//tsvファイルを全行まとめて読み込む場合
			//originalListに140万件全て入っている
			List<Original> originalList = it.readAll();
			//originalListから一個ずつoriginalに取り出す
			for(Original original : originalList) {
				originalService.insert(original);
				if(original.getTrain_id() % 10000  == 0) {
					System.out.println("10000件インサートされました");
				}
			}
		}
		return "index";
	}
	
	
}
