package com.kosmo.mycalender.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosmo.mycalender.account.dto.AccountBookDTO;
import com.kosmo.mycalender.account.dto.AccountTotalDTO;
import com.kosmo.mycalender.account.service.AccountBookService;
import com.kosmo.mycalender.account.service.AccountTotalService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/accountbook")
public class AccountWebController {
	@Autowired
	private AccountBookService accountBookService;
	@Autowired
	private AccountTotalService accountTotalService;

	@GetMapping
	public String getAccountBook(HttpServletRequest request, Model m) {
		List<AccountBookDTO> accountBookDTOs;
		List<AccountTotalDTO> accountTotalDTOs;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		Long totalCount;
		
		if(userId == null || userId.isEmpty()) {
			return "redirect:/login";
		}
		
		try {
			accountBookDTOs = accountBookService.getAllRecords(userId);
			accountTotalDTOs = accountTotalService.getTotal(userId);
			totalCount = accountTotalDTOs.get(accountTotalDTOs.size() - 1).getCount(); // 합계 건수
			
			m.addAttribute("records", accountBookDTOs);
			m.addAttribute("totalRecords", accountTotalDTOs);
			m.addAttribute("totalCount", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/calender/calender_main";
	}

}
