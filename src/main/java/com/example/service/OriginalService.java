package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Original;
import com.example.domain.Original2;
import com.example.repository.OriginalRepository;

/**
 * originalテーブルを操作するサービス.
 * 
 * @author yamaokahayato
 *
 */
@Service
@Transactional
public class OriginalService {
	
	@Autowired
	private OriginalRepository originalRepository;
	
	public void insert(Original original) {
		 originalRepository.insert(original);
	}
	
	/**
	 * originalテーブルから全件を検索する.
	 * 
	 * @return originalList
	 */
	public List<Original2> findByCategoryName() {
		List<Original2> originalList = originalRepository.findAll();
		return originalList;
	}

}
