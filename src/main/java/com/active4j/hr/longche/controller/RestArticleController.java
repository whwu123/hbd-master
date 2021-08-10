package com.active4j.hr.longche.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.active4j.hr.core.model.AjaxJson;
import com.active4j.hr.longche.entity.ArticleEntity;
import com.active4j.hr.longche.service.ArticleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/rest/article")
public class RestArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson list() {
		AjaxJson j = new AjaxJson();
		QueryWrapper<ArticleEntity> queryWrapper = new QueryWrapper<ArticleEntity>();
		queryWrapper.eq("state", 1);
		List<ArticleEntity> list = articleService.list(queryWrapper);
		j.setObj(list);
		return j;
	}

	
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson getById(String id) {
		AjaxJson j = new AjaxJson();
		if(id!=null && !id.isEmpty()) {
			ArticleEntity m = articleService.getById(id);
			j.setObj(m);
		}
		return j;
	}
}
