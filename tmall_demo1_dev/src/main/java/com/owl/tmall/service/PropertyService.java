package com.owl.tmall.service;

import com.owl.tmall.dao.CategoryDao;
import com.owl.tmall.dao.PropertyDao;
import com.owl.tmall.pojo.Category;
import com.owl.tmall.pojo.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import util.Page4Navigator;

@Service
public class PropertyService {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PropertyDao propertyDAO;
    public void add(Property bean) {
        propertyDAO.save(bean);
    }

    public void delete(int id) {
        propertyDAO.delete(id);
    }

    public Property get(int id) {
        return propertyDAO.findOne(id);
    }

    public void update(Property bean) {
        propertyDAO.save(bean);
    }
    public Page4Navigator<Property> findByCategory(int cid,int start ,int size,int navigatePages){
        Category one = categoryService.get(cid);
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable pageable=new PageRequest(start,size,sort);
        Page<Property> byCategory = propertyDAO.findByCategory(one, pageable);
        return new Page4Navigator<>(byCategory,navigatePages);

    }

}