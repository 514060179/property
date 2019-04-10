package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.VisitorMapper;
import com.simon.backstage.domain.model.Visitor;
import com.simon.backstage.service.VisitorService;
import com.simon.dal.constant.Type;
import com.simon.dal.dao.ImageMapper;
import com.simon.dal.model.Images;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 访客
 * @author fengtianying
 * @date 2018/12/14 17:26
 */
@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorMapper visitorMapper;

	@Autowired
	private ImageMapper imageMapper;
    @Override
    public PageInfo<Visitor> list(BaseClaims baseClaims) {
        PageHelper.startPage(baseClaims.getPageNo(),baseClaims.getPageSize());
        return new PageInfo<>(visitorMapper.selectByCondition(baseClaims));
    }
    
	@Override
	@Transactional
	public Visitor add(Visitor visitor) {
		visitor.setVisitorId(UUIDUtil.uidString());
		if(!visitor.getVisitorImage().isEmpty() && visitor.getVisitorImage().get(0)!=null){
			List<Images> list = new ArrayList<>();
			visitor.getVisitorImage().forEach(images -> {
				Images image = new Images();
				image.setImageId(UUIDUtil.uidString());
				image.setImageUrl(images.getImageUrl());
				image.setImageThumbnail(images.getImageThumbnail());
				image.setImageType(Type.IMAGE_TYPE_VISITOR);
				image.setObjectId(visitor.getVisitorId());
				list.add(image);
			});
			imageMapper.insertBatch(list);
			visitor.setVisitorImage(list);
		}
		int result = visitorMapper.insertSelective(visitor);
		if(result > 0){
			return visitor;
		}
		return null;
	}
}
